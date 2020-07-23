/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.devallwriteoff;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.devallwriteoff.DevAllWriteoff;
import com.thinkgem.jeesite.modules.dev.dao.devallwriteoff.DevAllWriteoffDao;

/**
 * 核销设备Service
 * @author myj
 * @version 2020-07-23
 */
@Service
@Transactional(readOnly = true)
public class DevAllWriteoffService extends CrudService<DevAllWriteoffDao, DevAllWriteoff> {

	public DevAllWriteoff get(String id) {
		return super.get(id);
	}
	
	public List<DevAllWriteoff> findList(DevAllWriteoff devAllWriteoff) {
		return super.findList(devAllWriteoff);
	}
	
	public Page<DevAllWriteoff> findPage(Page<DevAllWriteoff> page, DevAllWriteoff devAllWriteoff) {
		return super.findPage(page, devAllWriteoff);
	}
	
	@Transactional(readOnly = false)
	public void save(DevAllWriteoff devAllWriteoff) {
		super.save(devAllWriteoff);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevAllWriteoff devAllWriteoff) {
		super.delete(devAllWriteoff);
	}
	
}