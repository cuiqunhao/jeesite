package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;
import com.thinkgem.jeesite.modules.purifier.entity.Ware;
import com.thinkgem.jeesite.modules.purifier.service.WareService;
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
 * 仓库管理controller
 */
@Controller
@RequestMapping(value = "/ware")
public class WareController extends BaseController {
    @Autowired
    private WareService wareService;

    @ModelAttribute
    public Ware get(@RequestParam(value = "id",required = false) Long id){
        if (id != null){
            Ware ware = new Ware();
            ware.setId(id.toString());
            return wareService.get(ware);
        }else{
            return new Ware();
        }
    }

    @RequestMapping(value = "list")
    public String list(Ware ware, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Ware> page = wareService.findPage(new Page<Ware>(request, response), ware);
        model.addAttribute("page", page);
        return "modules/ware/wareList";
    }

    @RequestMapping(value = "form")
    public String form(Ware ware,Model model){
        model.addAttribute("ware",ware);
        return "modules/ware/wareForm";
    }

    @RequestMapping(value = "save")
    public String save(Ware ware, Model model,RedirectAttributes redirectAttributes){
        if (!beanValidator(model, ware)){
            return form(ware, model);
        }
        if( StringUtils.isBlank(ware.getId())){
            ware.setIsNewRecord(true);
        }else{
            ware.setIsNewRecord(false);
        }
        wareService.save(ware);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/ware/wareList";
    }


    @RequestMapping(value = "delete")
    public String delete(Ware ware,Model model,RedirectAttributes redirectAttributes){
        wareService.deleteWare(ware);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/ware/wareList";
    }

}
