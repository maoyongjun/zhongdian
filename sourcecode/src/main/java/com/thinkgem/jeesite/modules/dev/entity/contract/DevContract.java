/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.contract;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备合同Entity
 * @author myj
 * @version 2020-07-06
 */
public class DevContract extends DataEntity<DevContract> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 合同名称
	private String purchaseItems;		// 采购物品
	private String purchaser;		// 采购人
	private String supplier;		// 供应商
	private Date purchaseDate;		// 采购日期
	private String purchasePrice;		// 采购价款
	private String purchaseNumber;		// 采购数量
	
	public DevContract() {
		super();
	}

	public DevContract(String id){
		super(id);
	}

	@Length(min=0, max=200, message="合同名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=300, message="采购物品长度必须介于 0 和 300 之间")
	public String getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(String purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
	
	@Length(min=0, max=300, message="采购人长度必须介于 0 和 300 之间")
	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	
	@Length(min=0, max=300, message="供应商长度必须介于 0 和 300 之间")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	@Length(min=0, max=10, message="采购数量长度必须介于 0 和 10 之间")
	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	
}