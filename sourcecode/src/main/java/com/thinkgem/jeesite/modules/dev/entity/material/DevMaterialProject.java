/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.material;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 工程项目Entity
 * @author myj
 * @version 2020-07-03
 */
public class DevMaterialProject extends TreeEntity<DevMaterialProject> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private DevMaterialProject parent;		// 父级编号
	private String parentIds;		// 所有父级编号

	
	public DevMaterialProject() {
		super();
	}

	public DevMaterialProject(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public DevMaterialProject getParent() {
		return parent;
	}

	public void setParent(DevMaterialProject parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	



	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}