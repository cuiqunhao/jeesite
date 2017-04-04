package com.thinkgem.jeesite.modules.purifier.web;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Goods;
import com.thinkgem.jeesite.modules.purifier.service.GoodsService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月13日
 */
@Controller
@RequestMapping(value = "${adminPath}/goods")
public class GoodsController extends BaseController{
    @Autowired
    private GoodsService goodsService;

    @ModelAttribute
    public Goods get(Long id){
        if(id != null){
            return goodsService.get(id.toString());
        }else{
            return new Goods();
        }
    }

    @RequestMapping(value = "template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "商品数据导入模板.xlsx";
            List<Goods> list = Lists.newArrayList();
            Goods good = goodsService.get("1");
            list.add(good);
            new ExportExcel("商品数据", Goods.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/goods/list";
    }


    @RequestMapping(value = "import", method= RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if(Global.isDemoMode()){
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/goods/list?repage";
        }
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Goods> list = ei.getDataList(Goods.class);
            for (Goods goods : list){
                try{
                    List<Goods> goodsList = goodsService.findList(goods);

                    if (goodsList.size() == 0){
                        goods.setPhoto("/jeesite/userfiles/1/images/photo/2017/04/u%3D716437503%2C4269861921%26fm%3D23%26gp%3D0.jpg");
                        goodsService.save(goods);
                        successNum++;
                    }else{
                        failureMsg.append("<br/>商品名-商品型号: "+goods.getGoodName()+"-"+goods.getType()+" 已存在; ");
                        failureNum++;
                    }
                }catch(ConstraintViolationException ex){
                    failureMsg.append("商品名-商品型号: "+goods.getGoodName()+"-"+goods.getType()+" 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList){
                        failureMsg.append(message+"; ");
                        failureNum++;
                    }
                }catch (Exception ex) {
                    failureMsg.append("商品名-商品型号: "+goods.getGoodName()+"-"+goods.getType()+" 导入失败："+ex.getMessage());
                }
            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条商品，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 "+successNum+" 条商品"+failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/goods/list?repage";
    }


    @RequestMapping(value = "list")
    public String pageList(Goods goods, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Goods> page = goodsService.findPage(new Page<Goods>(request,response),goods);
        model.addAttribute("page",page);
        return "modules/purifier/goodsList";
    }


    @RequestMapping(value = "goodsSelectList")
    public String goodsSelectList(Goods goods, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Goods> page = goodsService.findPage(new Page<Goods>(request,response),goods);
        model.addAttribute("page",page);
        return "modules/purifier/goodsSelectList";
    }

    @RequestMapping(value = "form")
    public String form(Goods goods,Model model){
        model.addAttribute("goods",goods);
        return "modules/purifier/goodsForm";
    }

    @RequestMapping(value = "save")
    public String save(Goods goods, Model model, RedirectAttributes redirectAttributes){
        if(!beanValidator(model,goods)){
            return form(goods,model);
        }
        if(StringUtils.isBlank(goods.getId())){
            goods.setIsNewRecord(true);
        }else{
            goods.setIsNewRecord(false);
        }
        goodsService.save(goods);
        addMessage(model,"保存成功");
        return "redirect:" + adminPath + "/goods/list";
    }

    @RequestMapping(value = "delete")
    public String delete(Goods goods,Model model,RedirectAttributes redirectAttributes){
        goodsService.delete(goods);
        addMessage(model,"删除成功");
        return "redirect:" + adminPath + "/goods/list";
    }



}
