package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Maintain;
import com.thinkgem.jeesite.modules.purifier.entity.Receivables;
import com.thinkgem.jeesite.modules.purifier.service.MaintainService;
import com.thinkgem.jeesite.modules.purifier.service.ReceivablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 合同收款单
 *
 * @author addison
 * @since 2017年03月14日
 */
@Controller
@RequestMapping(value = "/reveivables")
public class ReceivablesController extends BaseController{
    @Autowired
    private ReceivablesService receivablesService;

    @ModelAttribute
    public Receivables get(Long id){
        if(id != null){
            return receivablesService.get(id.toString());
        }else{
            return new Receivables();
        }
    }

    @RequestMapping(value = "list")
    public String findPage(Receivables receivables, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Receivables> page = receivablesService.findPage(new Page<Receivables>(request, response), receivables);
        model.addAttribute("page", page);
        return "modules/receivables/receivablesList";
    }

    @RequestMapping(value = "form")
    public String form(Receivables receivables, Model model) {
        model.addAttribute("receivables", receivables);
        return "modules/receivables/receivablesForm";
    }

    @RequestMapping(value = "save")
    public String save(Receivables receivables, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, receivables)){
            return form(receivables, model);
        }
        receivables.setIsNewRecord(true);
        receivablesService.save(receivables);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/receivables/receivablesList";
    }
    @RequestMapping(value = "update")
    public String update(Receivables receivables, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, receivables)){
            return form(receivables, model);
        }
        receivables.setIsNewRecord(false);
        receivablesService.save(receivables);
        addMessage(redirectAttributes, "修改成功");

        return "redirect:" + adminPath + "/receivables/receivablesList";
    }

    @RequestMapping(value = "delete")
    public String delete(Receivables receivables, RedirectAttributes redirectAttributes) {
        receivablesService.delete(receivables);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/receivables/receivablesList";
    }


}
