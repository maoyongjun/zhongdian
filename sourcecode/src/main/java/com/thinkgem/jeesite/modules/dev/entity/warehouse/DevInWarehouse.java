/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.warehouse;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 在库设备Entity
 * @author myj
 * @version 2020-07-16
 */
public class DevInWarehouse extends DataEntity<DevInWarehouse> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 设备类别
	private String name;		// 设备名称
	private String specifications;		// 设备规格
	private String model;		// 设备型号
	private String count;		// 设备数量
	private String cost;		// 采购费用
	private String accountNumber;		// 财务记账票号
	private String purchaser;		// 采购人
	private String purchaseProject;		// 采购项目
	private String purchaseProjectId;		// 采购项目id
	private String manufacturer;		// 生产厂家
	private Date inDate;		// 进场日期
	private String acceptanceResults;		// 验收结果
	private String charger;		// 负责人
	private String location;		// 设备所在地
	private String contractId;		// 合同id
	private String warehouseReceiptId;		// 入库单id
	
	public DevInWarehouse() {
		super();
	}

	public DevInWarehouse(String id){
		super(id);
	}

	@Length(min=0, max=50, message="设备类别长度必须介于 0 和 50 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="设备名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Length(min=0, max=10, message="设备数量长度必须介于 0 和 10 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Length(min=0, max=200, message="财务记账票号长度必须介于 0 和 200 之间")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Length(min=0, max=200, message="采购人长度必须介于 0 和 200 之间")
	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	
	@Length(min=0, max=200, message="采购项目长度必须介于 0 和 200 之间")
	public String getPurchaseProject() {
		return purchaseProject;
	}

	public void setPurchaseProject(String purchaseProject) {
		this.purchaseProject = purchaseProject;
	}
	
	@Length(min=0, max=64, message="采购项目id长度必须介于 0 和 64 之间")
	public String getPurchaseProjectId() {
		return purchaseProjectId;
	}

	public void setPurchaseProjectId(String purchaseProjectId) {
		this.purchaseProjectId = purchaseProjectId;
	}
	
	@Length(min=0, max=200, message="生产厂家长度必须介于 0 和 200 之间")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	@Length(min=0, max=300, message="验收结果长度必须介于 0 和 300 之间")
	public String getAcceptanceResults() {
		return acceptanceResults;
	}

	public void setAcceptanceResults(String acceptanceResults) {
		this.acceptanceResults = acceptanceResults;
	}
	
	@Length(min=0, max=100, message="负责人长度必须介于 0 和 100 之间")
	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}
	
	@Length(min=0, max=500, message="设备所在地长度必须介于 0 和 500 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=64, message="合同id长度必须介于 0 和 64 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Length(min=0, max=64, message="入库单id长度必须介于 0 和 64 之间")
	public String getWarehouseReceiptId() {
		return warehouseReceiptId;
	}

	public void setWarehouseReceiptId(String warehouseReceiptId) {
		this.warehouseReceiptId = warehouseReceiptId;
	}
	
}