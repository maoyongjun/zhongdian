/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.entity.prodchild;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.modules.pj.entity.details.PjValueDetails;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 生成评价子表信息Entity
 * @author victor.chang
 * @version 2020-06-20
 */
public class PjProdChild extends DataEntity<PjProdChild> {
	
	private static final long serialVersionUID = 1L;
	private String parentId;
	private String valueDetailId;		// 评价细则
	private String statu;				// 状态
	private Double realScore;			// 分值
	private String note1;				// 备注1
	private String note2;				// 备注2
	private String note3;				// 备注3
	private String note4;				// 备注4

	private PjValueDetails pjValueDetails;	//评价细则

	public PjProdChild() {
		super();
	}

	public PjProdChild(String id){
		super(id);
	}

	public PjValueDetails getPjValueDetails() {
		if(this.pjValueDetails==null){
			this.pjValueDetails = new PjValueDetails(this.valueDetailId);
		}
		return pjValueDetails;
	}

	public void setPjValueDetails(PjValueDetails pjValueDetails) {
		this.pjValueDetails = pjValueDetails;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Length(min=1, max=64, message="评价细则长度必须介于 1 和 64 之间")
	public String getValueDetailId() {
		return valueDetailId;
	}

	public void setValueDetailId(String valueDetailId) {
		this.valueDetailId = valueDetailId;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	public Double getRealScore() {
		return realScore;
	}

	public void setRealScore(Double realScore) {
		this.realScore = realScore;
	}
	
	@Length(min=0, max=255, message="备注1长度必须介于 0 和 255 之间")
	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}
	
	@Length(min=0, max=255, message="备注2长度必须介于 0 和 255 之间")
	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}
	
	@Length(min=0, max=255, message="备注3长度必须介于 0 和 255 之间")
	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}
	
	@Length(min=0, max=255, message="备注4长度必须介于 0 和 255 之间")
	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}
	
}