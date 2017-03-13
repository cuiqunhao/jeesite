package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Goods;
import com.thinkgem.jeesite.modules.purifier.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月13日
 */
@Controller
@RequestMapping(value = "/goods")
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

    @RequestMapping(value = "list")
    public String pageList(Goods goods, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Goods> page = goodsService.findPage(new Page<Goods>(request,response),goods);
        model.addAttribute("page",page);
        return "modules/goods/goodsList";
    }

    @RequestMapping(value = "form")
    public String form(Goods goods,Model model){
        model.addAttribute("goods",goods);
        return "modules/goodsApp/goodsAppForm";
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
        return "redirect:" + adminPath + "/goods/goodsList";
    }

    @RequestMapping(value = "delete")
    public String delete(Goods goods,Model model,RedirectAttributes redirectAttributes){
        goodsService.delete(goods);
        addMessage(model,"删除成功");
        return "redirect:" + adminPath + "/goods/goodsList";
    }



}
