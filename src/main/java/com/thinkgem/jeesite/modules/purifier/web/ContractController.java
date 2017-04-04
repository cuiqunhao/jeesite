package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;
import com.thinkgem.jeesite.modules.purifier.entity.Maintain;
import com.thinkgem.jeesite.modules.purifier.entity.Receivables;
import com.thinkgem.jeesite.modules.purifier.service.ContractService;
import com.thinkgem.jeesite.modules.purifier.service.MaintainService;
import com.thinkgem.jeesite.modules.purifier.service.ReceivablesService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
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
@RequestMapping(value = "${adminPath}/contract")
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
            return contractService.getByGoodsAppId(id);
        }else{
            return new Contract();
        }
    }

    @RequestMapping(value = "list")
    public String findPage(Contract contract, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Contract> page = contractService.findPage(new Page<Contract>(request, response), contract);
        model.addAttribute("page", page);
        return "modules/purifier/contractList";
    }

    @RequestMapping(value = "notInstallList")
    public String findNotInstallList(Contract contract, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Contract> page = contractService.findNotInstallList(new Page<Contract>(request, response), contract);
        model.addAttribute("page", page);
        return "modules/purifier/notInstallList";
    }

    @RequestMapping(value = "notMainList")
    public String findNotMainList(Contract contract, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Contract> page = contractService.findNotMainList(new Page<Contract>(request, response), contract);
        model.addAttribute("page", page);
        return "modules/purifier/notMainList";
    }

    @RequestMapping(value = "contractNotRecList")
    public String findNotReceivablesList(Contract contract, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Contract> page = contractService.findNotReceivablesList(new Page<Contract>(request, response), contract);
        model.addAttribute("page", page);
        return "modules/purifier/contractNotRecList";
    }

    @RequestMapping(value = "contractNotMainList")
    public String findContractNotMainList(Contract contract, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Contract> page = contractService.findContractNotMainList(new Page<Contract>(request, response), contract);
        model.addAttribute("page", page);
        return "modules/purifier/contractNotMainList";
    }


    @RequestMapping(value = "contractSelectList")
    public String contractSelectList(Contract contract, HttpServletRequest request, HttpServletResponse response,Model model){
        Page<Contract> page = contractService.findPage(new Page<Contract>(request, response), contract);
        model.addAttribute("page", page);
        return "modules/purifier/contractSelectList";
    }

    @RequestMapping(value = "form")
    public String form(Contract contract, Model model) {
        model.addAttribute("contract", contract);
        if(contract.getId() != null){
            //合同收款信息
            Receivables receivables = new Receivables();
            Contract contract_tmp = new Contract();
            contract_tmp.setId(contract.getId());
            receivables.setContract(contract_tmp);
            List<Receivables> receivablesList= receivablesService.findList(receivables);
            model.addAttribute("receivablesList", receivablesList);
            //合同维护信息
            Maintain maintain = new Maintain();
            Contract contract1 = new Contract();
            contract1.setId(contract.getId());
            maintain.setContract(contract1);
            List<Maintain> maintainList = maintainService.findList(maintain);
            model.addAttribute("maintainList", maintainList);
        }
        return "modules/purifier/contractForm";
    }

    @RequestMapping(value = "notInstallForm")
    public String notInstallform(Contract contract, Model model) {
        model.addAttribute("contract", contract);
        return "modules/purifier/notInstallForm";
    }

    @RequestMapping(value = "notMainForm")
    public String notMainForm(Contract contract, Model model) {
        model.addAttribute("contract", contract);
        return "modules/purifier/notMainForm";
    }

//    @RequestMapping(value = "notReceivablesForm")
//    public String notReceivablesForm(Contract contract, Model model) {
//        model.addAttribute("contract", contract);
//        return "modules/purifier/notReceivablesForm";
//    }


    @RequestMapping(value = "save")
    public String save(Contract contract, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, contract)){
            return form(contract, model);
        }
        contractService.insterContract(contract);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/contract/list?repage";
    }

    @RequestMapping(value = "saveInstall")
    public String saveInstall(Contract contract, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, contract)){
            return form(contract, model);
        }
        contractService.save(contract);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/contract/notInstallList?repage";
    }

    @RequestMapping(value = "saveMain")
    public String saveMain(Contract contract, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, contract)){
            return form(contract, model);
        }
        contractService.save(contract);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/contract/notMainList?repage";
    }
//    @RequestMapping(value = "update")
//    public String update(Contract contract, Model model, RedirectAttributes redirectAttributes) {
//        if (!beanValidator(model, contract)){
//            return form(contract, model);
//        }
//        contract.setIsNewRecord(false);
//        contractService.save(contract);
//        addMessage(redirectAttributes, "修改成功");
//        return "redirect:" + adminPath + "/purifier/contractList";
//    }

    @RequestMapping(value = "delete")
    public String delete(Contract contract, RedirectAttributes redirectAttributes) {
        contractService.deleteContract(contract);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/contract/list?repage";
    }


}
