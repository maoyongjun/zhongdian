package com.thinkgem.jeesite.modules.pj.vo.eval;

import java.util.Date;

/**
 * @author VictorChang
 * @version 1.0
 * @date 2020/7/7 17:47
 */
public class SummaryVo {
    private String raterId;
    private String raterbyId;
    private String raterName;
    private String raterbyName;
    private String cateId;
    private double scores;
    private Date createDate;
    private double reterCoefficient;

    public String getRaterId() {
        return raterId;
    }

    public void setRaterId(String raterId) {
        this.raterId = raterId;
    }

    public String getRaterbyId() {
        return raterbyId;
    }

    public void setRaterbyId(String raterbyId) {
        this.raterbyId = raterbyId;
    }

    public String getRaterName() {
        return raterName;
    }

    public void setRaterName(String raterName) {
        this.raterName = raterName;
    }

    public String getRaterbyName() {
        return raterbyName;
    }

    public void setRaterbyName(String raterbyName) {
        this.raterbyName = raterbyName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getReterCoefficient() {
        return reterCoefficient;
    }

    public void setReterCoefficient(double reterCoefficient) {
        this.reterCoefficient = reterCoefficient;
    }
}
