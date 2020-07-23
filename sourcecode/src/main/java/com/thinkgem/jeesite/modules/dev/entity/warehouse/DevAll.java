package com.thinkgem.jeesite.modules.dev.entity.warehouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 设备统计
 */
public class DevAll {
    private String id;

    private String name;		// 车辆名称
    private Integer vechicleType;		// 车辆类型
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

    private String type;		// 设备类别
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
    private String contractId;		// 合同id
    private Integer devstatus;		// 状态

    private Integer fromIndex;
    private Integer pageSize;

    @Length(min=0, max=50, message="设备类别长度必须介于 0 和 50 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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


    @Length(min=0, max=64, message="合同id长度必须介于 0 和 64 之间")
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }


    public Integer getDevstatus() {
        return devstatus;
    }

    public void setDevstatus(Integer devstatus) {
        this.devstatus = devstatus;
    }



    public DevAll() {
        super();
    }


    @Length(min=0, max=300, message="车辆名称长度必须介于 0 和 300 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVechicleType() {
        return vechicleType;
    }

    public void setVechicleType(Integer vechicleType) {
        this.vechicleType = vechicleType;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(Integer fromIndex) {
        this.fromIndex = fromIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
