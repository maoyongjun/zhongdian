/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.vehicledetail;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.vehicledetail.DevVehicleDetail;
import com.thinkgem.jeesite.modules.dev.dao.vehicledetail.DevVehicleDetailDao;

/**
 * 车辆相关记录Service
 * @author myj
 * @version 2020-07-07
 */
@Service
@Transactional(readOnly = true)
public class DevVehicleDetailService extends CrudService<DevVehicleDetailDao, DevVehicleDetail> {

	public DevVehicleDetail get(String id) {
		return super.get(id);
	}
	
	public List<DevVehicleDetail> findList(DevVehicleDetail devVehicleDetail) {
		return super.findList(devVehicleDetail);
	}
	
	public Page<DevVehicleDetail> findPage(Page<DevVehicleDetail> page, DevVehicleDetail devVehicleDetail) {
		return super.findPage(page, devVehicleDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DevVehicleDetail devVehicleDetail) {
		super.save(devVehicleDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevVehicleDetail devVehicleDetail) {
		super.delete(devVehicleDetail);
	}
	
}