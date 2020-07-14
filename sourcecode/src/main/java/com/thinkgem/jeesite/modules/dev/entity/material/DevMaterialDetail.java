/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.material;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 工程物资Entity
 * @author myj
 * @version 2020-07-03
 */
public class DevMaterialDetail extends DataEntity<DevMaterialDetail> {
	
	private static final long serialVersionUID = 1L;
	private String materialName;		// 物资名称
	private String count;		// 数量
	private String projectId;		// 项目id
	private DevMaterialProject project;    // 项目id

	public DevMaterialDetail() {
		super();
	}

	public DevMaterialDetail(String id){
		super(id);
	}

	@Length(min=0, max=255, message="物资名称长度必须介于 0 和 255 之间")
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Length(min=0, max=10, message="数量长度必须介于 0 和 10 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public DevMaterialProject getProject() {
		return project;
	}

	public void setProject(DevMaterialProject project) {
		this.project = project;
	}
	
}