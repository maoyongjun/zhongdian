/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.maintaindetail;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 保养项次Entity
 * @author myj
 * @version 2020-07-06
 */
public class DevMaintainDetail extends DataEntity<DevMaintainDetail> {
	
	private static final long serialVersionUID = 1L;
	private String maintainId;		// 保养记录id
	private String itemNumber;		// 保养的项次
	private String itemName;		// 项次名称
	
	public DevMaintainDetail() {
		super();
	}

	public DevMaintainDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="保养记录id长度必须介于 0 和 64 之间")
	public String getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}
	
	@Length(min=0, max=11, message="保养的项次长度必须介于 0 和 11 之间")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Length(min=0, max=300, message="项次名称长度必须介于 0 和 300 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}