/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.entity.summarytotal;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评价总分汇总Entity
 * @author chang
 * @version 2020-07-08
 */
public class PjSummaryTotal extends DataEntity<PjSummaryTotal> {
	
	private static final long serialVersionUID = 1L;
	private String raterbyId;		// 被评价人id
	private Double baseScore;		// 原始分数
	private Double secondScore;		// 二次修正分数
	private Double thirdScore;		// 总经理修正分数
	private Double fakeScore;		// 伪奋斗者修正值
	private Double bearScore;		// 担当值
	private Double bearRate;		// 担当贡献率
	private String note1;		// note1
	private String note2;		// note2
	private User raterby;	//被评价人
	
	public PjSummaryTotal() {
		super();
	}

	public PjSummaryTotal(String id){
		super(id);
	}

	@Length(min=0, max=64, message="被评价人id长度必须介于 0 和 64 之间")
	public String getRaterbyId() {
		return raterbyId;
	}

	public void setRaterbyId(String raterbyId) {
		this.raterbyId = raterbyId;
	}
	
	public Double getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(Double baseScore) {
		this.baseScore = baseScore;
	}
	
	public Double getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(Double secondScore) {
		this.secondScore = secondScore;
	}
	
	public Double getThirdScore() {
		return thirdScore;
	}

	public void setThirdScore(Double thirdScore) {
		this.thirdScore = thirdScore;
	}
	
	public Double getFakeScore() {
		return fakeScore;
	}

	public void setFakeScore(Double fakeScore) {
		this.fakeScore = fakeScore;
	}
	
	public Double getBearScore() {
		return bearScore;
	}

	public void setBearScore(Double bearScore) {
		this.bearScore = bearScore;
	}
	
	public Double getBearRate() {
		return bearRate;
	}

	public void setBearRate(Double bearRate) {
		this.bearRate = bearRate;
	}
	
	@Length(min=0, max=255, message="note1长度必须介于 0 和 255 之间")
	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}
	
	@Length(min=0, max=255, message="note2长度必须介于 0 和 255 之间")
	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public User getRaterby() {
		return raterby;
	}

	public void setRaterby(User raterby) {
		this.raterby = raterby;
	}
}