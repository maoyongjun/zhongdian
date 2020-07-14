package com.thinkgem.jeesite.modules.pj.vo.eval;

import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;

import java.util.Date;

/**
 * @author VictorChang
 * @version 1.0
 * @date 2020/6/28 10:57
 */

public class WXParentVo {
    private String id;
    private String title;
    private String statu;
    private Date createDate;
    private Date updateDate;
    private String startEndDate;
    private String isEvalCurMonth;
    public WXParentVo() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStartEndDate() {
        return startEndDate;
    }

    public void setStartEndDate(String startEndDate) {
        this.startEndDate = startEndDate;
    }


    public String getIsEvalCurMonth() {
        if(this.isEvalCurMonth==null||"".equals(this.isEvalCurMonth)){
            this.isEvalCurMonth="0";
        }
        return isEvalCurMonth;
    }

    public void setIsEvalCurMonth(String isEvalCurMonth) {
        this.isEvalCurMonth = isEvalCurMonth;
    }
}
