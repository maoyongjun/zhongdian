/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.vehicle;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.vehicle.DevVehicle;

/**
 * 车辆管理DAO接口
 * @author myj
 * @version 2020-07-07
 */
@MyBatisDao
public interface DevVehicleDao extends CrudDao<DevVehicle> {
	
}