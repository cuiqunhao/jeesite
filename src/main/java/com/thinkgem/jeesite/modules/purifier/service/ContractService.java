package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.purifier.dao.ContractDao;
import com.thinkgem.jeesite.modules.purifier.dao.ContractGoodsRelDao;
import com.thinkgem.jeesite.modules.purifier.dao.GoodsAppRelDao;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;
import com.thinkgem.jeesite.modules.purifier.entity.ContractGoodsRel;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsAppRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合同单业务逻辑
 *
 * @author addison
 * @since 2017年03月14日
 */
@Service
@Transactional(readOnly = true)
public class ContractService extends CrudService<ContractDao,Contract>{
    @Autowired
    private ContractGoodsRelDao contractGoodsRelDao;

    public Page<Contract> findNotInstallList(Page<Contract> page,Contract contract){
        contract.setPage(page);
        page.setList(dao.findNotInstallList(contract));
        return page;
    }

    public Page<Contract> findNotMainList(Page<Contract> page,Contract contract){
        contract.setPage(page);
        page.setList(dao.findNotMainList(contract));
        return page;
    }

    public Page<Contract> findNotReceivablesList(Page<Contract> page,Contract contract){
        contract.setPage(page);
        page.setList(dao.findNotReceivablesList(contract));
        return page;
    }

    public Page<Contract> findContractNotMainList(Page<Contract> page, Contract contract) {
        contract.setPage(page);
        page.setList(dao.findContractNotMainList(contract));
        return page;
    }
    /**
     * 新增申请单
     * @param contract
     * @return
     */
    @Transactional(readOnly = false)
    public int insterContract(Contract contract){
        int appId;
        if(StringUtils.isNotEmpty(contract.getId())){
            contract.preUpdate();
            appId = dao.update(contract);
            ContractGoodsRel contractGoodsRel = new ContractGoodsRel();
            contractGoodsRel.setContractId(Long.valueOf(contract.getId()));
            contractGoodsRelDao.delete(contractGoodsRel);
            dao.insterContractGoodsRel(contract);
        }else{
            contract.preInsert();
            appId = dao.insert(contract);
            contract.setId(contract.getId());
            dao.insterContractGoodsRel(contract);
        }
        return  appId;
    }

    @Transactional(readOnly = false)
    public int deleteContract(Contract contract){
        contract.preUpdate();
        int res = dao.delete(contract);
        dao.deleteContractGoodsRel(contract);
        return res;
    }

    public Contract getByGoodsAppId(Long id){
        Contract contract =  dao.get(String.valueOf(id));
        ContractGoodsRel contractGoodsRel = new ContractGoodsRel();
        contractGoodsRel.setContractId(id);
        contract.setGoodList(contractGoodsRelDao.findAllList(contractGoodsRel));
        return contract;
    }
}
