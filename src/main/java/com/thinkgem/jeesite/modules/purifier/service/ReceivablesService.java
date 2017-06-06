package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.ReceivablesDao;
import com.thinkgem.jeesite.modules.purifier.entity.Receivables;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 收款业务逻辑
 *
 * @author addison
 * @since 2017年03月14日
 */
@Service
@Transactional(readOnly = true)
public class ReceivablesService extends CrudService<ReceivablesDao,Receivables> {

    @Transactional(readOnly = false)
    public void saveRec(Receivables entity) {
        if (dao.get(entity) != null){
            dao.deleteOtherRec(entity);
            entity.preInsert();
            dao.insert(entity);
        }else{
            entity.preUpdate();
            dao.update(entity);
        }
    }
}
