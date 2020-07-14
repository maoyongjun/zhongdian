/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.maintain;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备保养Entity
 * @author myj
 * @version 2020-07-06
 */
public class DevMaintain extends DataEntity<DevMaintain> {
	
	private static final long serialVersionUID = 1L;
	private String devId;		// 设备id
	private Date date;		// 保养时间
	private String maintainBy;		// 保养人
	private String devName;		// 设备名称
	
	public DevMaintain() {
		super();
	}

	public DevMaintain(String id){
		super(id);
	}

	@Length(min=0, max=64, message="设备id长度必须介于 0 和 64 之间")
	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Length(min=0, max=255, message="保养人长度必须介于 0 和 255 之间")
	public String getMaintainBy() {
		return maintainBy;
	}

	public void setMaintainBy(String maintainBy) {
		this.maintainBy = maintainBy;
	}
	
	@Length(min=0, max=255, message="设备名称长度必须介于 0 和 255 之间")
	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}
	
}