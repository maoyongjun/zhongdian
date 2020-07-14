/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.material;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 工程物资管理Entity
 * @author myj
 * @version 2020-07-02
 */
public class DevMaterial extends TreeEntity<DevMaterial> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String materialName;		// 物资名称
	private String count;		// 数量
	private DevMaterial parent;		// 父级编号
	private String parentIds;		// 所有父级编号

	
	public DevMaterial() {
		super();
	}

	public DevMaterial(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="物资名称长度必须介于 0 和 255 之间")
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Length(min=1, max=10, message="数量长度必须介于 1 和 10 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public DevMaterial getParent() {
		return parent;
	}

	public void setParent(DevMaterial parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
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