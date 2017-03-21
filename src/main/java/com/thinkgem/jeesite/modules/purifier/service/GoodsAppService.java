package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.GoodsAppDao;
import com.thinkgem.jeesite.modules.purifier.dao.GoodsAppRelDao;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsAppRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 货物申请业务类
 *
 * @author addison
 * @since 2017年03月08日
 */
@Service
@Transactional(readOnly = true)
public class GoodsAppService extends CrudService<GoodsAppDao,GoodsApp> {
    @Autowired
    private GoodsAppRelDao goodsAppRelDao;

    /**
     * 根据申请单ID获取申请单详情
     * @param id
     * @return
     */
    public GoodsApp getByGoodsAppId(Long id){
        GoodsApp goodsApp =  dao.get(String.valueOf(id));
        GoodsAppRel goodsAppRel = new GoodsAppRel();
        goodsAppRel.setId(goodsApp.getId());
        goodsApp.setGoodList(goodsAppRelDao.findAllList(goodsAppRel));
        return goodsApp;
    }
    @Transactional(readOnly = false)
    public int updateGoodsApp(GoodsApp goodsApp){
        goodsApp.setIsNewRecord(true);
        goodsApp.preInsert();
        int appId = dao.updateGoodsApp(goodsApp);
        GoodsAppRel goodsAppRel = new GoodsAppRel();
        goodsAppRel.setGoodsAppId(Long.valueOf(goodsApp.getId()));
        goodsAppRelDao.delete(goodsAppRel);
        dao.insterGoodsAppRel(goodsApp);
        return  appId;
    }
    /**
     * 新增申请单
     * @param goodsApp
     * @return
     */
    @Transactional(readOnly = false)
    public int insterGoodsApp(GoodsApp goodsApp){
        int appId;
        if(goodsApp.getId() != null){
            goodsApp.preUpdate();
            appId = dao.updateGoodsApp(goodsApp);
            GoodsAppRel goodsAppRel = new GoodsAppRel();
            goodsAppRel.setGoodsAppId(Long.valueOf(goodsApp.getId()));
            goodsAppRelDao.delete(goodsAppRel);
            dao.insterGoodsAppRel(goodsApp);
        }else{
            goodsApp.preInsert();
            appId = dao.insterGoodsApp(goodsApp);
            goodsApp.setId(goodsApp.getId());
            dao.insterGoodsAppRel(goodsApp);
        }
        return  appId;
    }

    @Transactional(readOnly = false)
    public int deleteGoodsApp(GoodsApp app){
        app.setIsNewRecord(true);
        app.preInsert();
        int res = dao.deleteGoodsApp(app);
        dao.deleteGoodsAppRel(app);
        return res;
    }
}
