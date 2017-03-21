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
    int insterGoodsApp(GoodsApp app);

    int insterGoodsAppRel(GoodsApp app);

    int updateGoodsApp(GoodsApp app);

    int deleteGoodsApp(GoodsApp app);

    int deleteGoodsAppRel(GoodsApp app);

    List<GoodsApp> needFirstExaList(GoodsApp entity);

    List<GoodsApp> needSecExaList(GoodsApp entity);

    List<GoodsApp> needShipList(GoodsApp entity);

    List<GoodsApp> needConsigneeList(GoodsApp entity);
}
