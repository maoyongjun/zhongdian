/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.vehicledetail;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.vehicledetail.DevVehicleDetail;

/**
 * 车辆相关记录DAO接口
 * @author myj
 * @version 2020-07-07
 */
@MyBatisDao
public interface DevVehicleDetailDao extends CrudDao<DevVehicleDetail> {
	
}