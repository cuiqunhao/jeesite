package com.thinkgem.jeesite.modules.purifier.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purifier.dao.MaintainDao;
import com.thinkgem.jeesite.modules.purifier.entity.Maintain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月14日
 */
@Service
@Transactional(readOnly = true)
public class MaintainService extends CrudService<MaintainDao,Maintain> {
}
