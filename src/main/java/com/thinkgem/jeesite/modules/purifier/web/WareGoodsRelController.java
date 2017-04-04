package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Goods;
import com.thinkgem.jeesite.modules.purifier.entity.Ware;
import com.thinkgem.jeesite.modules.purifier.entity.WareGoodsRel;
import com.thinkgem.jeesite.modules.purifier.service.WareGoodsRelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value = "${adminPath}/wareGoodsRel")
public class WareGoodsRelController extends BaseController{

    @Autowired
    private WareGoodsRelService wareGoodsRelService;


    @ModelAttribute
    public WareGoodsRel get(@RequestParam(value = "wareId",required = false) Long wareId,@RequestParam(value = "goodId",required = false) Long goodId){
        if(wareId != null && goodId != null){
            WareGoodsRel wareGoodsRel = new WareGoodsRel();
            Ware ware = new Ware();
            ware.setId(wareId.toString());
            wareGoodsRel.setWare(ware);
            Goods goods = new Goods();
            goods.setId(goodId.toString());
            wareGoodsRel.setGood(goods);
            return wareGoodsRelService.get(wareGoodsRel);
        }else {
            return new WareGoodsRel();
        }
    }

    @RequestMapping(value = "form")
    public String form(WareGoodsRel wareGoodsRel,Model model){
        model.addAttribute("wareGoodsRel",wareGoodsRel);
        return "modules/purifier/wareGoodsRelForm";
    }

    @RequestMapping(value = "list")
    public String list(WareGoodsRel wareGoodsRel, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<WareGoodsRel> page = wareGoodsRelService.findPage(new Page<WareGoodsRel>(request,response),wareGoodsRel);
        model.addAttribute("page", page);
        return "modules/purifier/wareLGoodsRelList";
    }

    @RequestMapping(value = "save")
    public String save(WareGoodsRel wareGoodsRel, Model model, RedirectAttributes redirectAttributes){
        if(!beanValidator(model,wareGoodsRel)){
            return form(wareGoodsRel,model);
        }
        wareGoodsRelService.update(wareGoodsRel);
        addMessage(model,"保存成功");
        return "redirect:" + adminPath + "/wareGoodsRel/list";
    }

    @RequestMapping(value = "delete")
    public String delete(WareGoodsRel wareGoodsRel,Model model,RedirectAttributes redirectAttributes){
        wareGoodsRelService.delete(wareGoodsRel);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/wareGoodsRel/list";
    }


}
