package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.GoodsAppDao;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;
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



}
