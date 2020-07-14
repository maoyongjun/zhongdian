/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.material;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterialProject;
import com.thinkgem.jeesite.modules.dev.dao.material.DevMaterialProjectDao;

/**
 * 工程项目Service
 * @author myj
 * @version 2020-07-03
 */
@Service
@Transactional(readOnly = true)
public class DevMaterialProjectService extends TreeService<DevMaterialProjectDao, DevMaterialProject> {

	public DevMaterialProject get(String id) {
		return super.get(id);
	}
	
	public List<DevMaterialProject> findList(DevMaterialProject devMaterialProject) {
		if (StringUtils.isNotBlank(devMaterialProject.getParentIds())){
			devMaterialProject.setParentIds(","+devMaterialProject.getParentIds()+",");
		}
		return super.findList(devMaterialProject);
	}
	
	@Transactional(readOnly = false)
	public void save(DevMaterialProject devMaterialProject) {
		super.save(devMaterialProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevMaterialProject devMaterialProject) {
		super.delete(devMaterialProject);
	}
	
}