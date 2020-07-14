/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.warehousereceipt;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 入库单Entity
 * @author myj
 * @version 2020-07-14
 */
public class DevWarehouseReceipt extends DataEntity<DevWarehouseReceipt> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 入库单名称
	private Date date;		// 入库单时间
	private String purchaser;		// 采购人
	private String warehouseManagement;		// 库管
	private String approver;		// 审批人
	private String type;		// 入库单类型
	
	public DevWarehouseReceipt() {
		super();
	}

	public DevWarehouseReceipt(String id){
		super(id);
	}

	@Length(min=0, max=200, message="入库单名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Length(min=0, max=200, message="采购人长度必须介于 0 和 200 之间")
	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	
	@Length(min=0, max=200, message="库管长度必须介于 0 和 200 之间")
	public String getWarehouseManagement() {
		return warehouseManagement;
	}

	public void setWarehouseManagement(String warehouseManagement) {
		this.warehouseManagement = warehouseManagement;
	}
	
	@Length(min=0, max=200, message="审批人长度必须介于 0 和 200 之间")
	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}
	
	@Length(min=0, max=200, message="入库单类型长度必须介于 0 和 200 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}