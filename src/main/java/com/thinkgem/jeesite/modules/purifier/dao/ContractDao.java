package com.thinkgem.jeesite.modules.purifier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月02日
 */
@MyBatisDao
public interface ContractDao extends CrudDao<Contract>{

    List<Contract> findNotInstallList(Contract contract);

    List<Contract> findNotMainList(Contract contract);

    List<Contract> findNotReceivablesList(Contract contract);

    List<Contract> findContractNotMainList(Contract contract);
}
