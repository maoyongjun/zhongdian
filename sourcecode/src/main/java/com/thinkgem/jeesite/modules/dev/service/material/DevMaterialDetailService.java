/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.material;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterialDetail;
import com.thinkgem.jeesite.modules.dev.dao.material.DevMaterialDetailDao;

/**
 * 工程物资Service
 * @author myj
 * @version 2020-07-03
 */
@Service
@Transactional(readOnly = true)
public class DevMaterialDetailService extends CrudService<DevMaterialDetailDao, DevMaterialDetail> {

	public DevMaterialDetail get(String id) {
		return super.get(id);
	}
	
	public List<DevMaterialDetail> findList(DevMaterialDetail devMaterialDetail) {
		return super.findList(devMaterialDetail);
	}
	
	public Page<DevMaterialDetail> findPage(Page<DevMaterialDetail> page, DevMaterialDetail devMaterialDetail) {
		return super.findPage(page, devMaterialDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DevMaterialDetail devMaterialDetail) {
		super.save(devMaterialDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevMaterialDetail devMaterialDetail) {
		super.delete(devMaterialDetail);
	}
	
}