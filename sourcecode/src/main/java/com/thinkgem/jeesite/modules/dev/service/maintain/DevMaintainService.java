/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.maintain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.maintain.DevMaintain;
import com.thinkgem.jeesite.modules.dev.dao.maintain.DevMaintainDao;

/**
 * 设备保养Service
 * @author myj
 * @version 2020-07-06
 */
@Service
@Transactional(readOnly = true)
public class DevMaintainService extends CrudService<DevMaintainDao, DevMaintain> {

	public DevMaintain get(String id) {
		return super.get(id);
	}
	
	public List<DevMaintain> findList(DevMaintain devMaintain) {
		return super.findList(devMaintain);
	}
	
	public Page<DevMaintain> findPage(Page<DevMaintain> page, DevMaintain devMaintain) {
		return super.findPage(page, devMaintain);
	}
	
	@Transactional(readOnly = false)
	public void save(DevMaintain devMaintain) {
		super.save(devMaintain);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevMaintain devMaintain) {
		super.delete(devMaintain);
	}
	
}