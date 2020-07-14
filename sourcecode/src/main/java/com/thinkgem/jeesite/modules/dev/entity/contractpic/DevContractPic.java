/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.contractpic;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 合同图片Entity
 * @author myj
 * @version 2020-07-13
 */
public class DevContractPic extends DataEntity<DevContractPic> {
	
	private static final long serialVersionUID = 1L;
	private String picpath;		// 图片路径
	private String picname;		// 图片名称
	private String orgname;		// 原名称
	private String contractid;		// 合同id
	
	public DevContractPic() {
		super();
	}

	public DevContractPic(String id){
		super(id);
	}

	@Length(min=0, max=500, message="图片路径长度必须介于 0 和 500 之间")
	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	
	@Length(min=0, max=100, message="图片名称长度必须介于 0 和 100 之间")
	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}
	
	@Length(min=0, max=300, message="原名称长度必须介于 0 和 300 之间")
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	@Length(min=0, max=100, message="合同id长度必须介于 0 和 100 之间")
	public String getContractid() {
		return contractid;
	}

	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	
}