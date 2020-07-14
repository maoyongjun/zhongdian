package com.thinkgem.jeesite.modules.pj.vo.eval;

import java.util.Date;

/**
 * @author VictorChang
 * @version 1.0
 * @date 2020/6/30 10:09
 */
public class EvalDetailsRater {
    private String cateName;
    private String detailsName;
    private String raterbyName;
    private double score;
    private double realScore;
    private String cateId;
    private String valueDetailId;
    private String raterbyId;
    private Date createDate;
    private double coefficient;

    public EvalDetailsRater() {
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

    public String getRaterbyName() {
        return raterbyName;
    }

    public void setRaterbyName(String raterbyName) {
        this.raterbyName = raterbyName;
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

    public String getRaterbyId() {
        return raterbyId;
    }

    public void setRaterbyId(String raterbyId) {
        this.raterbyId = raterbyId;
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
