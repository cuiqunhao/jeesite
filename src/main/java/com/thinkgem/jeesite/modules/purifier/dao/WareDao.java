package com.thinkgem.jeesite.modules.purifier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purifier.entity.Ware;

/**
 * 仓库管理dao
 *
 * @author addison
 * @since 2017年03月02日
 */
@MyBatisDao
public interface WareDao extends CrudDao<Ware> {

    int deleteByWare(Ware ware);
}
