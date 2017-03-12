package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.GoodsAppRelDao;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsAppRel;
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
public class GoodsAppRelService extends CrudService<GoodsAppRelDao,GoodsAppRel> {

}
