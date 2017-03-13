package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.GoodsDao;
import com.thinkgem.jeesite.modules.purifier.entity.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品业务
 *
 * @author addison
 * @since 2017年03月13日
 */
@Service
@Transactional(readOnly = true)
public class GoodsService extends CrudService<GoodsDao,Goods> {
}
