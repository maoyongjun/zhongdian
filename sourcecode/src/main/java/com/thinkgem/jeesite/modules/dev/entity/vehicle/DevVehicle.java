/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.vehicle;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆管理Entity
 * @author myj
 * @version 2020-07-07
 */
public class DevVehicle extends DataEntity<DevVehicle> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 车辆名称
	private Integer type;		// 车辆类型
	private String location;		// 车辆所在地
	private String specifications;		// 设备规格
	private String model;		// 设备型号
	private String useBy;		// 车辆使用人
	private Date insuranceDate;		// 保险日期
	private Date reviewDate;		// 审车日期
	private Integer status;		// 状态
	private String projectId;		// 所属项目id
	private String projectName;		// 所属项目名称
	private String warehouseReceiptId;		// 入库单id
	
	public DevVehicle() {
		super();
	}

	public DevVehicle(String id){
		super(id);
	}

	@Length(min=0, max=300, message="车辆名称长度必须介于 0 和 300 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=500, message="车辆所在地长度必须介于 0 和 500 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=200, message="设备规格长度必须介于 0 和 200 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=0, max=200, message="设备型号长度必须介于 0 和 200 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=255, message="车辆使用人长度必须介于 0 和 255 之间")
	public String getUseBy() {
		return useBy;
	}

	public void setUseBy(String useBy) {
		this.useBy = useBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=200, message="所属项目id长度必须介于 0 和 200 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=0, max=64, message="所属项目名称长度必须介于 0 和 64 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=64, message="入库单id长度必须介于 0 和 64 之间")
	public String getWarehouseReceiptId() {
		return warehouseReceiptId;
	}

	public void setWarehouseReceiptId(String warehouseReceiptId) {
		this.warehouseReceiptId = warehouseReceiptId;
	}
	
}