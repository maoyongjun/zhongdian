package com.thinkgem.jeesite.modules.pj.vo.eval;

import java.util.Date;

/**
 * @author VictorChang
 * @version 1.0
 * @date 2020/6/28 16:17
 */
public class WXChildVo {
    //a.id,a.statu,a.real_score,a.create_date, d.`name`,d.score
    private String id;
    private String statu;
    private double realScore;
    private Date createDate;
    private String name;//细则名称
    private double score;

    public WXChildVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public double getRealScore() {
        return realScore;
    }

    public void setRealScore(double realScore) {
        this.realScore = realScore;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
