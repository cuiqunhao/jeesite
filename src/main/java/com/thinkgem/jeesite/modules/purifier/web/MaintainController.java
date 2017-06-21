package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;
import com.thinkgem.jeesite.modules.purifier.entity.Maintain;
import com.thinkgem.jeesite.modules.purifier.entity.Receivables;
import com.thinkgem.jeesite.modules.purifier.service.ContractService;
import com.thinkgem.jeesite.modules.purifier.service.MaintainService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 合同维护单
 *
 * @author addison
 * @since 2017年03月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/maintain")
public class MaintainController extends BaseController{
    @Autowired
    private MaintainService maintainService;
    @Autowired
    private ContractService contractService;

    @ModelAttribute
    public Maintain get(Long id){
        Maintain maintain = new Maintain();
        if(id != null){
            maintain =  maintainService.get(id.toString());
        }
        User user = UserUtils.getUser();
        maintain.getSqlMap().put("dsf",contractService.dataScopeFilter(user, "o", "b"));
        return maintain;

    }

    @RequestMapping(value = "list")
    public String findPage(Maintain maintain, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Maintain> page = maintainService.findPage(new Page<Maintain>(request, response), maintain);
        model.addAttribute("page", page);
        return "modules/purifier/maintainList";
    }

    @RequestMapping(value = "form")
    public String form(Maintain maintain, Model model) {
        model.addAttribute("contract", maintain);
        return "modules/purifier/maintainForm";
    }

    @RequestMapping(value = "formMain")
    public String formRec(Maintain maintain, Model model) {
        Contract contract = new Contract();
        contract.setId(maintain.getContract().getId());
        contract = contractService.get(contract);
        maintain.setContract(contract);
        return "modules/purifier/maintainForm";
    }

    @RequestMapping(value = "save")
    public String save(Maintain maintain, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, maintain)){
            return form(maintain, model);
        }
        maintainService.save(maintain);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/maintain/list";
    }
    @RequestMapping(value = "update")
    public String update(Maintain maintain, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, maintain)){
            return form(maintain, model);
        }
        maintain.setIsNewRecord(false);
        maintainService.save(maintain);
        addMessage(redirectAttributes, "修改成功");

        return "redirect:" + adminPath + "/maintain/list";
    }

    @RequestMapping(value = "delete")
    public String delete(Maintain maintain, RedirectAttributes redirectAttributes) {
        maintainService.delete(maintain);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/maintain/list";
    }


}
