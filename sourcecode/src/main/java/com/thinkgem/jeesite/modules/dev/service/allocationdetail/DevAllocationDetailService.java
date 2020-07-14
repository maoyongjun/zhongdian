/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.allocationdetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dev.entity.allocationdetail.DevAllocationDetail;
import com.thinkgem.jeesite.modules.dev.dao.allocationdetail.DevAllocationDetailDao;

/**
 * 调拨细项Service
 * @author myj
 * @version 2020-07-06
 */
@Service
@Transactional(readOnly = true)
public class DevAllocationDetailService extends CrudService<DevAllocationDetailDao, DevAllocationDetail> {

	
	public DevAllocationDetail get(String id) {
		DevAllocationDetail devAllocationDetail = super.get(id);
		return devAllocationDetail;
	}
	
	public List<DevAllocationDetail> findList(DevAllocationDetail devAllocationDetail) {
		return super.findList(devAllocationDetail);
	}
	
	public Page<DevAllocationDetail> findPage(Page<DevAllocationDetail> page, DevAllocationDetail devAllocationDetail) {
		return super.findPage(page, devAllocationDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DevAllocationDetail devAllocationDetail) {
		super.save(devAllocationDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevAllocationDetail devAllocationDetail) {
		super.delete(devAllocationDetail);
	}
	
}