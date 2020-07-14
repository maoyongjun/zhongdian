/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.entity.summary;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 被评价人汇总Entity
 *
 * @author liu
 * @version 2020-07-03
 */
public class PjRaterbySummary extends DataEntity<PjRaterbySummary> {

    private static final long serialVersionUID = 1L;
    private String raterbyId;        // 被评价人
    private String raterId;        // 评价人
    private Double reterCoefficient;        // 评价人系数
    private String cateId;        // 类目
    private Double scores;        // 分值
    private Double firstCorrectionScores;        // 权重占比分值
    private Double secondCorrectionScores;        // 第二次修正分值
    private Date createDate;//创建时间

    public PjRaterbySummary() {
        super();
    }

    public PjRaterbySummary(String id) {
        super(id);
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Length(min = 0, max = 64, message = "被评价人长度必须介于 0 和 64 之间")
    public String getRaterbyId() {
        return raterbyId;
    }

    public void setRaterbyId(String raterbyId) {
        this.raterbyId = raterbyId;
    }

    @Length(min = 0, max = 64, message = "评价人长度必须介于 0 和 64 之间")
    public String getRaterId() {
        return raterId;
    }

    public void setRaterId(String raterId) {
        this.raterId = raterId;
    }

    public Double getReterCoefficient() {
        return reterCoefficient;
    }

    public void setReterCoefficient(Double reterCoefficient) {
        this.reterCoefficient = reterCoefficient;
    }

    @Length(min = 0, max = 64, message = "类目长度必须介于 0 和 64 之间")
    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public Double getScores() {
        return scores;
    }

    public void setScores(Double scores) {
        this.scores = scores;
    }

    public Double getFirstCorrectionScores() {
        return firstCorrectionScores;
    }

    public void setFirstCorrectionScores(Double firstCorrectionScores) {
        this.firstCorrectionScores = firstCorrectionScores;
    }

    public Double getSecondCorrectionScores() {
        return secondCorrectionScores;
    }

    public void setSecondCorrectionScores(Double secondCorrectionScores) {
        this.secondCorrectionScores = secondCorrectionScores;
    }

}