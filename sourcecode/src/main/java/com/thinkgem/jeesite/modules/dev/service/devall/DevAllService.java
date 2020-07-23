/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.devall;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.devall.DevAll;
import com.thinkgem.jeesite.modules.dev.dao.devall.DevAllDao;

/**
 * 设备Service
 * @author myj
 * @version 2020-07-23
 */
@Service
@Transactional(readOnly = true)
public class DevAllService extends CrudService<DevAllDao, DevAll> {

	public DevAll get(String id) {
		return super.get(id);
	}
	
	public List<DevAll> findList(DevAll devAll) {
		return super.findList(devAll);
	}
	
	public Page<DevAll> findPage(Page<DevAll> page, DevAll devAll) {
		return super.findPage(page, devAll);
	}
	
	@Transactional(readOnly = false)
	public void save(DevAll devAll) {
		super.save(devAll);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevAll devAll) {
		super.delete(devAll);
	}
	
}