/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.entity.devall;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备Entity
 * @author myj
 * @version 2020-07-23
 */
public class DevAll extends DataEntity<DevAll> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 类型
	private String name;		// 名称
	private String specifications;		// 规格
	private String model;		// 型号
	private Long count;		// 数量
	private String cost;		// 费用
	private String accountnumber;		// 财务编号
	private String purchaser;		// 采购人
	private String projectid;		// 项目id
	private String projectname;		// 项目名称
	private String manufacturer;		// 供应商
	private Date createdate;		// 创建时间
	private String acceptanceresults;		// 验收结果
	private String charger;		// 负责人
	private String location;		// 所在地
	private String contractid;		// 合同id
	private String warehousereceiptid;		// 入库单id
	private String devstatus;		// 设备状态
	private String vechicletype;		// 卡车类型
	private String useby;		// 使用人
	private String insurancedate;		// 保险日期
	private String reviewdate;		// 审车日期
	
	public DevAll() {
		super();
	}

	public DevAll(String id){
		super(id);
	}

	@Length(min=0, max=50, message="类型长度必须介于 0 和 50 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=300, message="名称长度必须介于 0 和 300 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="规格长度必须介于 0 和 200 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=0, max=200, message="型号长度必须介于 0 和 200 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	@Length(min=0, max=10, message="费用长度必须介于 0 和 10 之间")
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Length(min=0, max=200, message="财务编号长度必须介于 0 和 200 之间")
	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	
	@Length(min=0, max=200, message="采购人长度必须介于 0 和 200 之间")
	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	
	@Length(min=0, max=200, message="项目id长度必须介于 0 和 200 之间")
	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	
	@Length(min=0, max=200, message="项目名称长度必须介于 0 和 200 之间")
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	@Length(min=0, max=200, message="供应商长度必须介于 0 和 200 之间")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	@Length(min=0, max=300, message="验收结果长度必须介于 0 和 300 之间")
	public String getAcceptanceresults() {
		return acceptanceresults;
	}

	public void setAcceptanceresults(String acceptanceresults) {
		this.acceptanceresults = acceptanceresults;
	}
	
	@Length(min=0, max=100, message="负责人长度必须介于 0 和 100 之间")
	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}
	
	@Length(min=0, max=500, message="所在地长度必须介于 0 和 500 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=64, message="合同id长度必须介于 0 和 64 之间")
	public String getContractid() {
		return contractid;
	}

	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	
	@Length(min=0, max=64, message="入库单id长度必须介于 0 和 64 之间")
	public String getWarehousereceiptid() {
		return warehousereceiptid;
	}

	public void setWarehousereceiptid(String warehousereceiptid) {
		this.warehousereceiptid = warehousereceiptid;
	}
	
	@Length(min=0, max=11, message="设备状态长度必须介于 0 和 11 之间")
	public String getDevstatus() {
		return devstatus;
	}

	public void setDevstatus(String devstatus) {
		this.devstatus = devstatus;
	}
	
	@Length(min=0, max=11, message="卡车类型长度必须介于 0 和 11 之间")
	public String getVechicletype() {
		return vechicletype;
	}

	public void setVechicletype(String vechicletype) {
		this.vechicletype = vechicletype;
	}
	
	@Length(min=0, max=255, message="使用人长度必须介于 0 和 255 之间")
	public String getUseby() {
		return useby;
	}

	public void setUseby(String useby) {
		this.useby = useby;
	}
	
	@Length(min=1, max=10, message="保险日期长度必须介于 1 和 10 之间")
	public String getInsurancedate() {
		return insurancedate;
	}

	public void setInsurancedate(String insurancedate) {
		this.insurancedate = insurancedate;
	}
	
	@Length(min=1, max=10, message="审车日期长度必须介于 1 和 10 之间")
	public String getReviewdate() {
		return reviewdate;
	}

	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;
	}
	
}