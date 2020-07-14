package com.thinkgem.jeesite.modules.pj.vo.eval;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 被评价人详情
 * @author VictorChang
 * @version 1.0
 * @date 2020/6/29 17:35
 */
public class EvalDetails {
    private String cateName;
    private String detailsName;
    private double score;
    private double realScore;
    private String raterName;
    private String cateId;
    private String valueDetailId;
    private String raterId;
    private Date createDate;
    private double coefficient;

    public EvalDetails() {
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDetailsName() {
        return detailsName;
    }

    public void setDetailsName(String detailsName) {
        this.detailsName = detailsName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getRealScore() {
        return realScore;
    }

    public void setRealScore(double realScore) {
        this.realScore = realScore;
    }

    public String getRaterName() {
        return raterName;
    }

    public void setRaterName(String raterName) {
        this.raterName = raterName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getValueDetailId() {
        return valueDetailId;
    }

    public void setValueDetailId(String valueDetailId) {
        this.valueDetailId = valueDetailId;
    }

    public String getRaterId() {
        return raterId;
    }

    public void setRaterId(String raterId) {
        this.raterId = raterId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
