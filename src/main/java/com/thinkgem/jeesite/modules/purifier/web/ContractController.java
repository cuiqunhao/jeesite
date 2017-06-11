package com.thinkgem.jeesite.modules.purifier.web;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.*;
import com.thinkgem.jeesite.modules.purifier.service.ContractService;
import com.thinkgem.jeesite.modules.purifier.service.MaintainService;
import com.thinkgem.jeesite.modules.purifier.service.ReceivablesService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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
import java.util.Date;
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
        Contract contract = new Contract();
        if(id != null){
            contract =  contractService.getByGoodsAppId(id);
        }
        User user = UserUtils.getUser();
        contract.getSqlMap().put("dsf",contractService.dataScopeFilter(user, "o", "b"));
        return contract;
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


    /**
     * 下载导入订单数据模板
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "订单数据导入模板.xlsx";
            List<Contract> list = Lists.newArrayList();

            Contract contract = new Contract();
            contract.setCollCycle(30);
            contract.setCompleteTime(new Date());
            contract.setContacts("张三");
            contract.setCompleteTime(new Date());
            contract.setContactsAddress("北京地址");
            contract.setContactsPhone("15620679609");
            contract.setContractAmount(11d);
            contract.setContractBenginTime(new Date());
            contract.setContractName("订单名称");
            contract.setContractNo("x234445d");
            contract.setContractEndTime(new Date());
            contract.setContractTime(new Date());
            contract.setContractType("1");
            contract.setInstaller(UserUtils.getUser());
            contract.setItem("项目");
            contract.setMianCycle(30);
            contract.setSalesman(UserUtils.getUser());
            contract.setContractTimeBegin(new Date());
            contract.setContractTimeEnd(new Date());

            List<ContractGoodsRel> goodList = Lists.newArrayList();
            ContractGoodsRel contractGoodsRel = new ContractGoodsRel();
            contractGoodsRel.setAppNum(10L);
            Goods good = new Goods();
            good.setGoodName("海之眼");good.setType("1x");
            contractGoodsRel.setGood(good);
            contractGoodsRel.setUsefor("其他用途");
//            goodList.add(contractGoodsRel);
            goodList.add(contractGoodsRel);
//            goodList.add(contractGoodsRel);
            contract.setGoodList(goodList);

            list.add(contract);
//            new ExportExcel("订单数据", Contract.class, 2).setDataList(list).write(response, fileName).dispose();

            List<Contract> exList = Lists.newArrayList();
            List<Receivables> exRecList = Lists.newArrayList();
            List<Maintain> exMainList = Lists.newArrayList();

            for(Contract contract1:list){
                exList.add(contract1);

                //合同收款信息
                List<Receivables> receivablesList= Lists.newArrayList();
                Receivables receivables = new Receivables();
                receivables.setAmount(100D);
                receivables.setContract(contract1);
                receivables.setInvoice("发票信息");
                receivables.setIsInvoice("1");
                receivables.setNextRecTime(new Date());
                receivables.setRecTime(new Date());
                receivables.setUserId(UserUtils.getUser());
                receivablesList.add(receivables);

                exRecList.addAll(receivablesList);
                //合同维护信息
                List<Maintain> maintainList = Lists.newArrayList();
                Maintain maintain = new Maintain();
                maintain.setContract(contract1);
                maintain.setMainContent("维护内容");
                maintain.setUserId(UserUtils.getUser());
                maintain.setMainTime(new Date());
                maintain.setNextMainTime(new Date());
                maintainList.add(maintain);
                exMainList.addAll(maintainList);

            }

            ExportExcel exportExcel = new ExportExcel("订单数据", Contract.class,1);
            exportExcel.setDataList(list);
            exportExcel.addSheet("收款信息",exportExcel.genHeaderList(Receivables.class,1,null));
            exportExcel.setDataList(exRecList);
            exportExcel.addSheet("维护信息",exportExcel.genHeaderList(Maintain.class,1,null));
            exportExcel.setDataList(exMainList);
            exportExcel.write(response, fileName).dispose();


            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/contract/list?repage";
    }

    /**
     * 导出订单数据
     * @param contract
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(Contract contract, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "订单数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Contract> page = contractService.findPage(new Page<Contract>(request, response, -1), contract);
            List<Contract> exList = Lists.newArrayList();
            List<Receivables> exRecList = Lists.newArrayList();
            List<Maintain> exMainList = Lists.newArrayList();
            for(Contract contract1:page.getList()){
                contract1 = contractService.getByGoodsAppId(Long.valueOf(contract1.getId()));
                exList.add(contract1);

                //合同收款信息
                Receivables receivables = new Receivables();
                receivables.setContract(contract1);
                List<Receivables> receivablesList= receivablesService.findList(receivables);
                exRecList.addAll(receivablesList);
                //合同维护信息
                Maintain maintain = new Maintain();
                maintain.setContract(contract1);
                List<Maintain> maintainList = maintainService.findList(maintain);
                exMainList.addAll(maintainList);

            }


            ExportExcel exportExcel = new ExportExcel("订单数据", Contract.class,1);
            exportExcel.setDataList(exList);
            exportExcel.addSheet("收款信息",exportExcel.genHeaderList(Receivables.class,1,null));
            exportExcel.setDataList(exRecList);
            exportExcel.addSheet("维护信息",exportExcel.genHeaderList(Maintain.class,1,null));
            exportExcel.setDataList(exMainList);
            exportExcel.write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出订单失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/contract/list?repage";
    }


    /**
     * 导入订单数据
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if(Global.isDemoMode()){
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/contract/list?repage";
        }
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Contract> list = ei.getDataList(Contract.class);
            for (Contract contract : list){
                try{
                    Contract contractHased = contractService.getByContractNo(contract);
                    if (contractHased == null){
                        BeanValidators.validateWithException(validator, contract);
                        contractService.insterContract(contract);
                        successNum++;
                    }else{
                        contract.setId(contractHased.getId());
                        contractService.insterContract(contract);
                        successNum++;
                    }
                }catch(ConstraintViolationException ex){
                    failureMsg.append("<br/>订单 "+contract.getContractNo()+" 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList){
                        failureMsg.append(message+"; ");
                        failureNum++;
                    }
                }catch (Exception ex) {
                    logger.error("导入失败",ex);
                    failureMsg.append("<br/>订单 "+contract.getContractNo()+" 导入失败："+ex.getMessage());
                }
            }
            ImportExcel ei2 = new ImportExcel(file, 1, 1);
            List<Receivables> list2 = ei2.getDataList(Receivables.class);
            ImportExcel ei3 = new ImportExcel(file, 1, 2);
            List<Maintain> list3 = ei3.getDataList(Maintain.class);

            for (Contract contract : list){
                for (Receivables receivables : list2){
                    try{
                        if(contract.getContractNo().equals(receivables.getContract().getContractNo())){
                            receivables.setContract(contract);
                            BeanValidators.validateWithException(validator, receivables);
                            receivablesService.saveRec(receivables);
                            successNum++;
                        }else{
                            continue;
                        }
                    }catch(ConstraintViolationException ex){
                        failureMsg.append("<br/>订单收款 "+receivables.getContract().getContractNo()+" 导入失败：");
                        List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                        for (String message : messageList){
                            failureMsg.append(message+"; ");
                            failureNum++;
                        }
                    }catch (Exception ex) {
                        logger.error("导入失败",ex);
                        failureMsg.append("<br/>订单收款 "+receivables.getContract().getContractNo()+" 导入失败："+ex.getMessage());
                    }
                }

                for (Maintain maintain : list3){
                    try{
                        if(contract.getContractNo().equals(maintain.getContract().getContractNo())){
                            maintain.setContract(contract);
                            BeanValidators.validateWithException(validator, maintain);
                            maintainService.save(maintain);
                            successNum++;
                        }else{
                            continue;
                        }

                    }catch(ConstraintViolationException ex){
                        failureMsg.append("<br/>订单het "+maintain.getContract().getContractNo()+" 导入失败：");
                        List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                        for (String message : messageList){
                            failureMsg.append(message+"; ");
                            failureNum++;
                        }
                    }catch (Exception ex) {
                        logger.error("导入失败",ex);
                        failureMsg.append("<br/>订单het "+maintain.getContract().getContractNo()+" 导入失败："+ex.getMessage());
                    }
                }

            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条订单，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 "+successNum+" 条订单"+failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入订单失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/contract/list?repage";
    }

//    /**
//     *
//     * @param contractNo
//     * @return
//     */
//    private String checkContractNo(String contractNo) {
//        Contract contract = new Contract();
//        contract.setContractNo(contractNo);
//        if (contractNo !=null && contractService.getByContractNo(contract) == null) {
//            return "true";
//        }
//        return "false";
//    }


}
