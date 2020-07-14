/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.maintaindetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dev.entity.maintaindetail.DevMaintainDetail;
import com.thinkgem.jeesite.modules.dev.dao.maintaindetail.DevMaintainDetailDao;

/**
 * 保养项次Service
 * @author myj
 * @version 2020-07-06
 */
@Service
@Transactional(readOnly = true)
public class DevMaintainDetailService extends CrudService<DevMaintainDetailDao, DevMaintainDetail> {

	
	public DevMaintainDetail get(String id) {
		DevMaintainDetail devMaintainDetail = super.get(id);
		return devMaintainDetail;
	}
	
	public List<DevMaintainDetail> findList(DevMaintainDetail devMaintainDetail) {
		return super.findList(devMaintainDetail);
	}
	
	public Page<DevMaintainDetail> findPage(Page<DevMaintainDetail> page, DevMaintainDetail devMaintainDetail) {
		return super.findPage(page, devMaintainDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DevMaintainDetail devMaintainDetail) {
		super.save(devMaintainDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevMaintainDetail devMaintainDetail) {
		super.delete(devMaintainDetail);
	}
	
}