/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.material;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterial;
import com.thinkgem.jeesite.modules.dev.dao.material.DevMaterialDao;

/**
 * 工程物资管理Service
 * @author myj
 * @version 2020-07-02
 */
@Service
@Transactional(readOnly = true)
public class DevMaterialService extends TreeService<DevMaterialDao, DevMaterial> {

	public DevMaterial get(String id) {
		return super.get(id);
	}
	
	public List<DevMaterial> findList(DevMaterial devMaterial) {
		if (StringUtils.isNotBlank(devMaterial.getParentIds())){
			devMaterial.setParentIds(","+devMaterial.getParentIds()+",");
		}
		return super.findList(devMaterial);
	}
	
	@Transactional(readOnly = false)
	public void save(DevMaterial devMaterial) {
		super.save(devMaterial);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevMaterial devMaterial) {
		super.delete(devMaterial);
	}
	
}