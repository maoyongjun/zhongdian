/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.allocation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.allocation.DevAllocation;
import com.thinkgem.jeesite.modules.dev.dao.allocation.DevAllocationDao;

/**
 * 设备调拨Service
 * @author myj
 * @version 2020-07-07
 */
@Service
@Transactional(readOnly = true)
public class DevAllocationService extends CrudService<DevAllocationDao, DevAllocation> {

	public DevAllocation get(String id) {
		return super.get(id);
	}
	
	public List<DevAllocation> findList(DevAllocation devAllocation) {
		return super.findList(devAllocation);
	}
	
	public Page<DevAllocation> findPage(Page<DevAllocation> page, DevAllocation devAllocation) {
		return super.findPage(page, devAllocation);
	}
	
	@Transactional(readOnly = false)
	public void save(DevAllocation devAllocation) {
		super.save(devAllocation);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevAllocation devAllocation) {
		super.delete(devAllocation);
	}
	
}