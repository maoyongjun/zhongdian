/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.contract.DevContract;
import com.thinkgem.jeesite.modules.dev.dao.contract.DevContractDao;

/**
 * 设备合同Service
 * @author myj
 * @version 2020-07-06
 */
@Service
@Transactional(readOnly = true)
public class DevContractService extends CrudService<DevContractDao, DevContract> {

	public DevContract get(String id) {
		return super.get(id);
	}
	
	public List<DevContract> findList(DevContract devContract) {
		return super.findList(devContract);
	}
	
	public Page<DevContract> findPage(Page<DevContract> page, DevContract devContract) {
		return super.findPage(page, devContract);
	}
	
	@Transactional(readOnly = false)
	public void save(DevContract devContract) {
		super.save(devContract);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevContract devContract) {
		super.delete(devContract);
	}
	
}