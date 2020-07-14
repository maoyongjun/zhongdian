/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.writeoffdetail;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.writeoffdetail.DevWriteOffDetail;
import com.thinkgem.jeesite.modules.dev.dao.writeoffdetail.DevWriteOffDetailDao;

/**
 * 核销单明细Service
 * @author myj
 * @version 2020-07-09
 */
@Service
@Transactional(readOnly = true)
public class DevWriteOffDetailService extends CrudService<DevWriteOffDetailDao, DevWriteOffDetail> {

	public DevWriteOffDetail get(String id) {
		return super.get(id);
	}
	
	public List<DevWriteOffDetail> findList(DevWriteOffDetail devWriteOffDetail) {
		return super.findList(devWriteOffDetail);
	}
	
	public Page<DevWriteOffDetail> findPage(Page<DevWriteOffDetail> page, DevWriteOffDetail devWriteOffDetail) {
		return super.findPage(page, devWriteOffDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DevWriteOffDetail devWriteOffDetail) {
		super.save(devWriteOffDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevWriteOffDetail devWriteOffDetail) {
		super.delete(devWriteOffDetail);
	}
	
}