package com.thinkgem.jeesite.modules.purifier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purifier.entity.Ware;
import com.thinkgem.jeesite.modules.purifier.entity.WareGoodsRel;

/**
 * 仓库库存管理dao
 *
 * @author addison
 * @since 2017年03月02日
 */
@MyBatisDao
public interface WareRelDao extends CrudDao<WareGoodsRel> {
}
