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
        List<Contract> contractList = contractDao.findAllList(new Contract());
        for (Contract e : contractList){
            if (StringUtils.trimToEmpty(val).equals(e.getContractNo())){
                return e;
            }
        }
        Contract contract = new Contract();
        contract.setContractNo(StringUtils.trimToEmpty(val));
        return contract;
    }
}
