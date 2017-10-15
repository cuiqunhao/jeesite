package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.purifier.dao.*;
import com.thinkgem.jeesite.modules.purifier.entity.*;
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
    @Autowired
    private WareGoodsRelDao wareGoodsRelDao;
    @Autowired
    private WareDao wareDao;
    @Autowired
    private GoodsDao goodsDao;

    /**
     * 根据申请单ID获取申请单详情
     * @param id
     * @return
     */
    public GoodsApp getByGoodsAppId(Long id){
        GoodsApp goodsApp =  dao.get(String.valueOf(id));
        GoodsAppRel goodsAppRel = new GoodsAppRel();
        goodsAppRel.setGoodsAppId(id);
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
        if(StringUtils.isNotEmpty(goodsApp.getId())){
            goodsApp.preUpdate();
            appId = dao.updateGoodsApp(goodsApp);
            GoodsAppRel goodsAppRel = new GoodsAppRel();
            goodsAppRel.setGoodsAppId(Long.valueOf(goodsApp.getId()));
            goodsAppRelDao.delete(goodsAppRel);
            dao.insterGoodsAppRel(goodsApp);

            //减少库存
            if("1".equals(goodsApp.getConsigneeStatus())){
                for(GoodsAppRel goodsAppRel1:goodsApp.getGoodList()){
                    WareGoodsRel wareGoodsRel = new WareGoodsRel();
                    Ware ware = wareDao.get(goodsApp.getWare().getId());
                    wareGoodsRel.setWare(ware);
                    Goods goods = goodsDao.get(goodsAppRel1.getGood().getId());
                    wareGoodsRel.setGood(goods);
                    wareGoodsRel = wareGoodsRelDao.get(wareGoodsRel);
                    if(wareGoodsRel==null){
                        throw new ServiceException("请维护商品：("+goods.getGoodName()+")与仓库:("+ware.getWareName()+")的库存");
                    }
                    wareGoodsRel.setNum(wareGoodsRel.getNum()-goodsAppRel1.getAppNum());
                    wareGoodsRelDao.delete(wareGoodsRel);
                    wareGoodsRelDao.insert(wareGoodsRel);
                }
            }

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
        app.preUpdate();
        int res = dao.deleteGoodsApp(app);
        dao.deleteGoodsAppRel(app);
        return res;
    }


    public Page<GoodsApp> needFirstExaList(Page<GoodsApp> page, GoodsApp entity) {
        entity.setPage(page);
        page.setList(dao.needFirstExaList(entity));
        return page;
    }

    public Page<GoodsApp> needSecExaList(Page<GoodsApp> page, GoodsApp entity) {
        entity.setPage(page);
        page.setList(dao.needSecExaList(entity));
        return page;
    }

    public Page<GoodsApp> needShipList(Page<GoodsApp> page, GoodsApp entity) {
        entity.setPage(page);
        page.setList(dao.needShipList(entity));
        return page;
    }

    public Page<GoodsApp> needConsigneeList(Page<GoodsApp> page, GoodsApp entity) {
        entity.setPage(page);
        page.setList(dao.needConsigneeList(entity));
        return page;
    }
}
