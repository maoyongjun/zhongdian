/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.warehouse;

import java.util.List;

import com.thinkgem.jeesite.modules.dev.entity.warehouse.DevAll;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.warehouse.DevInWarehouse;
import com.thinkgem.jeesite.modules.dev.dao.warehouse.DevInWarehouseDao;

/**
 * 在库设备Service
 * @author myj
 * @version 2020-07-16
 */
@Service
@Transactional(readOnly = true)
public class DevInWarehouseService extends CrudService<DevInWarehouseDao, DevInWarehouse> {

	public DevInWarehouse get(String id) {
		return super.get(id);
	}
	
	public List<DevInWarehouse> findList(DevInWarehouse devInWarehouse) {
		return super.findList(devInWarehouse);
	}
	
	public Page<DevInWarehouse> findPage(Page<DevInWarehouse> page, DevInWarehouse devInWarehouse) {
		return super.findPage(page, devInWarehouse);
	}
	
	@Transactional(readOnly = false)
	public void save(DevInWarehouse devInWarehouse) {
		super.save(devInWarehouse);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevInWarehouse devInWarehouse) {
		super.delete(devInWarehouse);
	}

	@Transactional(readOnly = false)
	public List<DevAll> findAllDev(Page<DevAll> page,DevAll devAllCondition){
		int fromIndex = page.getPageNo()>0? (page.getPageNo()-1)*page.getPageSize():0;
		devAllCondition.setFromIndex(fromIndex);
		devAllCondition.setPageSize(page.getPageSize());
		return super.dao.findAllDev(devAllCondition);
	}
	@Transactional(readOnly = false)
	public Integer findAllDevCount(DevAll devAllCondition){
		return super.dao.findAllDevCount(devAllCondition);
	}


	
}