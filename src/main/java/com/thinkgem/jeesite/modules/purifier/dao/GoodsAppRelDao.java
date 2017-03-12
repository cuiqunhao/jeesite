package com.thinkgem.jeesite.modules.purifier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purifier.entity.Goods;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsAppRel;

/**
 * 货物申请dao
 *
 * @author addison
 * @since 2017年03月02日
 */
@MyBatisDao
public interface GoodsAppRelDao extends CrudDao<GoodsAppRel> {

}
