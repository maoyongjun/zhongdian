/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.material;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterialDetail;

/**
 * 工程物资DAO接口
 * @author myj
 * @version 2020-07-03
 */
@MyBatisDao
public interface DevMaterialDetailDao extends CrudDao<DevMaterialDetail> {
	
}