/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.allocation;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备调拨Entity
 * @author myj
 * @version 2020-07-11
 */
public class DevAllocation extends DataEntity<DevAllocation> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 调拨单名称
	private String projectCheckout;		// 出库的项目
	private String projectCheckoutId;		// 出库的项目id
	private String projectCheckin;		// 入库的项目
	private Date allocationDate;		// 调拨时间
	private String projectCheckinId;		// 入库的项目id
	private Integer status;		// 状态
	
	public DevAllocation() {
		super();
	}

	public DevAllocation(String id){
		super(id);
	}

	@Length(min=0, max=300, message="调拨单名称长度必须介于 0 和 300 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=300, message="出库的项目长度必须介于 0 和 300 之间")
	public String getProjectCheckout() {
		return projectCheckout;
	}

	public void setProjectCheckout(String projectCheckout) {
		this.projectCheckout = projectCheckout;
	}
	
	@Length(min=0, max=64, message="出库的项目id长度必须介于 0 和 64 之间")
	public String getProjectCheckoutId() {
		return projectCheckoutId;
	}

	public void setProjectCheckoutId(String projectCheckoutId) {
		this.projectCheckoutId = projectCheckoutId;
	}
	
	@Length(min=0, max=300, message="入库的项目长度必须介于 0 和 300 之间")
	public String getProjectCheckin() {
		return projectCheckin;
	}

	public void setProjectCheckin(String projectCheckin) {
		this.projectCheckin = projectCheckin;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}
	
	@Length(min=0, max=64, message="入库的项目id长度必须介于 0 和 64 之间")
	public String getProjectCheckinId() {
		return projectCheckinId;
	}

	public void setProjectCheckinId(String projectCheckinId) {
		this.projectCheckinId = projectCheckinId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}