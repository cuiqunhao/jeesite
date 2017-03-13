package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.WareGoodsRelDao;
import com.thinkgem.jeesite.modules.purifier.entity.WareGoodsRel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 2017/3/12.
 */
@Service
@Transactional(readOnly = true)
public class WareGoodsRelService extends CrudService<WareGoodsRelDao,WareGoodsRel> {

    @Transactional(readOnly = false)
    public int update(WareGoodsRel wareGoodsRel){
        return dao.update(wareGoodsRel);
    }
}
