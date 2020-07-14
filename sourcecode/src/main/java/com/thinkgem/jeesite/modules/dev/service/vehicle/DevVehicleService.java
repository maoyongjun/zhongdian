/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.vehicle;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.vehicle.DevVehicle;
import com.thinkgem.jeesite.modules.dev.dao.vehicle.DevVehicleDao;

/**
 * 车辆管理Service
 * @author myj
 * @version 2020-07-07
 */
@Service
@Transactional(readOnly = true)
public class DevVehicleService extends CrudService<DevVehicleDao, DevVehicle> {

	public DevVehicle get(String id) {
		return super.get(id);
	}
	
	public List<DevVehicle> findList(DevVehicle devVehicle) {
		return super.findList(devVehicle);
	}
	
	public Page<DevVehicle> findPage(Page<DevVehicle> page, DevVehicle devVehicle) {
		return super.findPage(page, devVehicle);
	}
	
	@Transactional(readOnly = false)
	public void save(DevVehicle devVehicle) {
		super.save(devVehicle);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevVehicle devVehicle) {
		super.delete(devVehicle);
	}
	
}