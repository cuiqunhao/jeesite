package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.WareDao;
import com.thinkgem.jeesite.modules.purifier.entity.Ware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 2017/3/12.
 */
@Service
@Transactional(readOnly = true)
public class WareService extends CrudService<WareDao,Ware>{

    @Transactional(readOnly = false)
    public int deleteWare(Ware ware){
        int res = dao.delete(ware);
        dao.deleteByWare(ware);
        return res;
    }

}
