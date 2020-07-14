/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.allocationdetail;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 调拨细项Entity
 * @author myj
 * @version 2020-07-06
 */
public class DevAllocationDetail extends DataEntity<DevAllocationDetail> {
	
	private static final long serialVersionUID = 1L;
	private String allocationId;		// 调拨单id
	private String itemNumber;		// 序号
	private String devId;		// 设备id
	private String devName;		// 设备名称
	
	public DevAllocationDetail() {
		super();
	}

	public DevAllocationDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="调拨单id长度必须介于 0 和 64 之间")
	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}
	
	@Length(min=0, max=10, message="序号长度必须介于 0 和 10 之间")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Length(min=0, max=64, message="设备id长度必须介于 0 和 64 之间")
	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}
	
	@Length(min=0, max=300, message="设备名称长度必须介于 0 和 300 之间")
	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}
	
}