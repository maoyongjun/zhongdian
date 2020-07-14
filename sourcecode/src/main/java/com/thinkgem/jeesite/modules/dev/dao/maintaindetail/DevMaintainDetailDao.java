/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.maintaindetail;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.maintaindetail.DevMaintainDetail;

/**
 * 保养项次DAO接口
 * @author myj
 * @version 2020-07-06
 */
@MyBatisDao
public interface DevMaintainDetailDao extends CrudDao<DevMaintainDetail> {
	
}