package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.purifier.dao.ContractDao;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;

import java.util.List;

/**
 * Created by pintec on 17/4/27.
 */
public class ContractType {
    private static ContractDao contractDao = SpringContextHolder.getBean(ContractDao.class);
    /**
     * 导出
     * @param val
     * @return
     */
    public static String setValue(Object val){
        if (val != null && ((Contract)val).getContractNo() != null){
            return ((Contract)val).getContractNo();
        }
        return "";
    }

    /**
     * 导入
     * @param val
     * @return
     */
    public static Object getValue(String val){
        Contract contract = new Contract();
        contract.setContractNo(StringUtils.trimToEmpty(val));
        Contract contractTmp = contractDao.getByContractNo(contract);
        if(contractTmp != null){
            contract = contractTmp;
        }

        return contract;
    }
}
