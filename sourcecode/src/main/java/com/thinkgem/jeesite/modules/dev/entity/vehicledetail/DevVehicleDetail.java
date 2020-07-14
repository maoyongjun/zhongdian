/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.vehicledetail;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆相关记录Entity
 * @author myj
 * @version 2020-07-07
 */
public class DevVehicleDetail extends DataEntity<DevVehicleDetail> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer type;		// 类型
	private Date date;		// 日期
	private String vehicleId;		// 车辆id
	private String parts;		// 部件
	private String mileage;		// 里程数
	private String cost;		// 费用
	private String maintainer;		// 维护人
	
	public DevVehicleDetail() {
		super();
	}

	public DevVehicleDetail(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Length(min=0, max=64, message="车辆id长度必须介于 0 和 64 之间")
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@Length(min=0, max=200, message="部件长度必须介于 0 和 200 之间")
	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}
	
	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Length(min=0, max=200, message="维护人长度必须介于 0 和 200 之间")
	public String getMaintainer() {
		return maintainer;
	}

	public void setMaintainer(String maintainer) {
		this.maintainer = maintainer;
	}
	
}