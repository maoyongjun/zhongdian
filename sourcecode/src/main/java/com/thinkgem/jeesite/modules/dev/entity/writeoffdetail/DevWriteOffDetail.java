/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.writeoffdetail;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 核销单明细Entity
 * @author myj
 * @version 2020-07-09
 */
public class DevWriteOffDetail extends DataEntity<DevWriteOffDetail> {
	
	private static final long serialVersionUID = 1L;
	private String devid;		// 设备id
	private String devname;		// 设备名称
	private String writeoffId;		// 核销单id
	private String projectid;		// 项目id
	private String projectname;		// 项目名称
	
	public DevWriteOffDetail() {
		super();
	}

	public DevWriteOffDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="设备id长度必须介于 0 和 64 之间")
	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}
	
	@Length(min=0, max=300, message="设备名称长度必须介于 0 和 300 之间")
	public String getDevname() {
		return devname;
	}

	public void setDevname(String devname) {
		this.devname = devname;
	}
	
	@Length(min=0, max=64, message="核销单id长度必须介于 0 和 64 之间")
	public String getWriteoffId() {
		return writeoffId;
	}

	public void setWriteoffId(String writeoffId) {
		this.writeoffId = writeoffId;
	}
	
	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	
	@Length(min=0, max=300, message="项目名称长度必须介于 0 和 300 之间")
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
}