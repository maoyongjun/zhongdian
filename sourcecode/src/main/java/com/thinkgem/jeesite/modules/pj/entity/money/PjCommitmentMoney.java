/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.entity.money;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 担当金基数维护Entity
 *
 * @author liu
 * @version 2020-06-23
 */
public class PjCommitmentMoney extends DataEntity<PjCommitmentMoney> {

    private static final long serialVersionUID = 1L;
    private String range;        // 偏离值范围
    private Double base;        // 担当金基数
    private Double leftNum;        // 范围值左
    private Double rightNum;        // 范围值右

    public PjCommitmentMoney() {
        super();
    }

    public PjCommitmentMoney(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "偏离值范围长度必须介于 0 和 64 之间")
    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Double leftNum) {
        this.leftNum = leftNum;
    }

    public Double getRightNum() {
        return rightNum;
    }

    public void setRightNum(Double rightNum) {
        this.rightNum = rightNum;
    }

}