package com.thinkgem.jeesite.modules.pj.vo.eval;

import java.util.Date;

/**
 * @author VictorChang
 * @version 1.0
 * @date 2020/7/8 14:14
 */
public class SummaryTotalVo {
    private String id;
    private String raterbyId;
    private String raterbyName;
    private double baseScore;
    private double secondScore;
    private double thirdScore;
    private double fakeScore;
    private double bearScore;
    private double bearRate;
    private Date createDate;

    public SummaryTotalVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaterbyId() {
        return raterbyId;
    }

    public void setRaterbyId(String raterbyId) {
        this.raterbyId = raterbyId;
    }

    public String getRaterbyName() {
        return raterbyName;
    }

    public void setRaterbyName(String raterbyName) {
        this.raterbyName = raterbyName;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }

    public double getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(double secondScore) {
        this.secondScore = secondScore;
    }

    public double getThirdScore() {
        return thirdScore;
    }

    public void setThirdScore(double thirdScore) {
        this.thirdScore = thirdScore;
    }

    public double getFakeScore() {
        return fakeScore;
    }

    public void setFakeScore(double fakeScore) {
        this.fakeScore = fakeScore;
    }

    public double getBearScore() {
        return bearScore;
    }

    public void setBearScore(double bearScore) {
        this.bearScore = bearScore;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getBearRate() {
        return bearRate;
    }

    public void setBearRate(double bearRate) {
        this.bearRate = bearRate;
    }
}
