/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.allocation;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.allocation.DevAllocation;

/**
 * 设备调拨DAO接口
 * @author myj
 * @version 2020-07-16
 */
@MyBatisDao
public interface DevAllocationDao extends CrudDao<DevAllocation> {
	
}