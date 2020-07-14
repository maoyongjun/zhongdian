/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.writeoff;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.writeoff.DevWriteOff;
import com.thinkgem.jeesite.modules.dev.dao.writeoff.DevWriteOffDao;

/**
 * 核销单Service
 * @author myj
 * @version 2020-07-09
 */
@Service
@Transactional(readOnly = true)
public class DevWriteOffService extends CrudService<DevWriteOffDao, DevWriteOff> {

	public DevWriteOff get(String id) {
		return super.get(id);
	}
	
	public List<DevWriteOff> findList(DevWriteOff devWriteOff) {
		return super.findList(devWriteOff);
	}
	
	public Page<DevWriteOff> findPage(Page<DevWriteOff> page, DevWriteOff devWriteOff) {
		return super.findPage(page, devWriteOff);
	}
	
	@Transactional(readOnly = false)
	public void save(DevWriteOff devWriteOff) {
		super.save(devWriteOff);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevWriteOff devWriteOff) {
		super.delete(devWriteOff);
	}
	
}