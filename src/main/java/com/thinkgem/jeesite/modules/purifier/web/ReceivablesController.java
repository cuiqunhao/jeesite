package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;
import com.thinkgem.jeesite.modules.purifier.entity.Maintain;
import com.thinkgem.jeesite.modules.purifier.entity.Receivables;
import com.thinkgem.jeesite.modules.purifier.job.AnnotationTask;
import com.thinkgem.jeesite.modules.purifier.service.ContractService;
import com.thinkgem.jeesite.modules.purifier.service.MaintainService;
import com.thinkgem.jeesite.modules.purifier.service.ReceivablesService;
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
 * 合同收款单
 *
 * @author addison
 * @since 2017年03月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/reveivables")
public class ReceivablesController extends BaseController{
    @Autowired
    private ReceivablesService receivablesService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private AnnotationTask annotationTask;

    @ModelAttribute
    public Receivables get(Long id){
        Receivables receivables = new Receivables();
        if(id != null){
            receivables =  receivablesService.get(id.toString());
        }
        User user = UserUtils.getUser();
        receivables.getSqlMap().put("dsf",contractService.dataScopeFilter(user, "o", "b"));
        return receivables;

    }

    @RequestMapping(value = "list")
    public String findPage(Receivables receivables, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Receivables> page = receivablesService.findPage(new Page<Receivables>(request, response), receivables);
        model.addAttribute("page", page);
        return "modules/purifier/receivablesList";
    }

    @RequestMapping(value = "form")
    public String form(Receivables receivables, Model model) {
        model.addAttribute("receivables", receivables);
        return "modules/purifier/receivablesForm";
    }

    @RequestMapping(value = "formRec")
    public String formRec(Receivables receivables, Model model) {
        Contract contract = new Contract();
        contract.setId(receivables.getContract().getId());
        contract = contractService.get(contract);
        receivables.setContract(contract);
        return "modules/purifier/receivablesForm";
    }

    @RequestMapping(value = "save")
    public String save(Receivables receivables, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, receivables)){
            return form(receivables, model);
        }
        receivablesService.saveRec(receivables);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/reveivables/list?repage";
    }
    @RequestMapping(value = "update")
    public String update(Receivables receivables, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, receivables)){
            return form(receivables, model);
        }
        receivables.setIsNewRecord(false);
        receivablesService.save(receivables);
        addMessage(redirectAttributes, "修改成功");

        return "redirect:" + adminPath + "/reveivables/list?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(Receivables receivables, RedirectAttributes redirectAttributes) {
        receivablesService.delete(receivables);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/reveivables/list?repage";
    }


}
