/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.writeoff;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 核销单Entity
 * @author myj
 * @version 2020-07-17
 */
public class DevWriteOff extends DataEntity<DevWriteOff> {
	
	private static final long serialVersionUID = 1L;
	private String projectid;		// 项目id
	private String projectname;		// 项目名称
	private String name;		// 核销单名称
	private String applicant;		// 申请人
	private String reviewer;		// 审核人
	private Date applicantDate;		// 申请时间
	private Date reviewerDate;		// 审核时间
	private Integer status;		// 状态
	private String devtype;		// 设备类型
	
	public DevWriteOff() {
		super();
	}

	public DevWriteOff(String id){
		super(id);
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
	
	@Length(min=0, max=300, message="核销单名称长度必须介于 0 和 300 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=300, message="申请人长度必须介于 0 和 300 之间")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	
	@Length(min=0, max=300, message="审核人长度必须介于 0 和 300 之间")
	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReviewerDate() {
		return reviewerDate;
	}

	public void setReviewerDate(Date reviewerDate) {
		this.reviewerDate = reviewerDate;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=300, message="设备类型长度必须介于 0 和 300 之间")
	public String getDevtype() {
		return devtype;
	}

	public void setDevtype(String devtype) {
		this.devtype = devtype;
	}
	
}