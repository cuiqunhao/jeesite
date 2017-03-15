package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;
import com.thinkgem.jeesite.modules.purifier.entity.Maintain;
import com.thinkgem.jeesite.modules.purifier.entity.Receivables;
import com.thinkgem.jeesite.modules.purifier.service.ContractService;
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
import java.util.List;

/**
 * 合同单controller
 *
 * @author addison
 * @since 2017年03月14日
 */
@Controller
@RequestMapping(value = "/contract")
public class ContractController extends BaseController{

    @Autowired
    private ContractService contractService;
    @Autowired
    private MaintainService maintainService;
    @Autowired
    private ReceivablesService receivablesService;

    @ModelAttribute
    public Contract get(Long id){
        if(id != null){
            return contractService.get(id.toString());
        }else{
            return new Contract();
        }
    }

    @RequestMapping(value = "list")
    public String findPage(Contract contract, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Contract> page = contractService.findPage(new Page<Contract>(request, response), contract);
        model.addAttribute("page", page);
        return "modules/contract/contractList";
    }

    @RequestMapping(value = "form")
    public String form(Contract contract, Model model) {
        model.addAttribute("contract", contract);
        if(contract.getId() != null){
            //合同收款信息
            Receivables receivables = new Receivables();
            receivables.setContractId(Long.valueOf(contract.getId()));
            List<Receivables> receivablesList= receivablesService.findList(receivables);
            model.addAttribute("receivablesList", receivablesList);
            //合同维护信息
            Maintain maintain = new Maintain();
            maintain.setContractId(Long.valueOf(contract.getId()));
            List<Maintain> maintainList = maintainService.findList(maintain);
            model.addAttribute("maintainList", maintainList);
        }
        return "modules/contract/contractForm";
    }

    @RequestMapping(value = "save")
    public String save(Contract contract, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, contract)){
            return form(contract, model);
        }
        contract.setIsNewRecord(true);
        contractService.save(contract);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/goodsApp/goodsAppList";
    }
    @RequestMapping(value = "update")
    public String update(Contract contract, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, contract)){
            return form(contract, model);
        }
        contract.setIsNewRecord(false);
        contractService.save(contract);
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/goodsApp/goodsAppList";
    }

    @RequestMapping(value = "delete")
    public String delete(Contract contract, RedirectAttributes redirectAttributes) {
        contractService.delete(contract);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/goodsApp/goodsAppList";
    }


}
