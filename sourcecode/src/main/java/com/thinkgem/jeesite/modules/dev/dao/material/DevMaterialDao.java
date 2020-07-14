/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.material;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterial;

/**
 * 工程物资管理DAO接口
 * @author myj
 * @version 2020-07-02
 */
@MyBatisDao
public interface DevMaterialDao extends TreeDao<DevMaterial> {
	
}