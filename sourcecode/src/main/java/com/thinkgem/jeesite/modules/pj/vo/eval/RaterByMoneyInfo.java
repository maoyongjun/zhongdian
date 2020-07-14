package com.thinkgem.jeesite.modules.pj.vo.eval;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author VictorChang
 * @version 1.0
 * @date 2020/7/9 17:12
 */
public class RaterByMoneyInfo {
    private String raterbyId;
    private String raterbyName;
    private double avgScore;
    private Map<String, RaterMoneyInfo> raterMoneyInfoMap;


    public RaterByMoneyInfo() {
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

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public Map<String, RaterMoneyInfo> getRaterMoneyInfoMap() {
        if (this.raterMoneyInfoMap == null) {
            this.raterMoneyInfoMap = new LinkedHashMap<>();
        }
        return raterMoneyInfoMap;
    }

    public void setRaterMoneyInfoMap(Map<String, RaterMoneyInfo> raterMoneyInfoMap) {
        this.raterMoneyInfoMap = raterMoneyInfoMap;
    }

}

