package com.thinkgem.jeesite.modules.purifier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;

import java.util.List;

/**
 * 货物申请dap
 *
 * @author addison
 * @since 2017年03月02日
 */
@MyBatisDao
public interface GoodsAppDao extends CrudDao<GoodsApp> {

//    List<GoodsAppDao> findGoodsApp(GoodsAppDao app);

    Long findAllCount(GoodsApp app);

    int insterGoodsApp(GoodsAppDao app);

    int insterGoodsAppRel(GoodsAppDao app);

    int updateGoodsApp(GoodsAppDao app);

    int deleteGoodsApp(GoodsAppDao app);
}
