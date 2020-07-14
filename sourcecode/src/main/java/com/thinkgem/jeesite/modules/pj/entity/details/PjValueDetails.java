/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.entity.details;

import com.thinkgem.jeesite.modules.pj.entity.category.PjValueCategory;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评定细则Entity
 * @author chang
 * @version 2020-06-19
 */
public class PjValueDetails extends DataEntity<PjValueDetails> {
	
	private static final long serialVersionUID = 1L;
	private String cateId;		// 类目
	private String name;		// 细则
	private Double score;		// 分值
	private String note1;		// 备注
	private String note2;		// 备注
	private String note3;		// 备注
	private String note4;		// 备注

	private PjValueCategory pjValueCategory; 	//类目
	
	public PjValueDetails() {
		super();
	}

	public PjValueDetails(String id){
		super(id);
	}

	public PjValueCategory getPjValueCategory() {
		if(this.pjValueCategory==null){
			this.pjValueCategory = new PjValueCategory(this.cateId);
		}
		return pjValueCategory;
	}

	public void setPjValueCategory(PjValueCategory pjValueCategory) {
		this.pjValueCategory = pjValueCategory;
	}

	@Length(min=1, max=64, message="类目长度必须介于 1 和 64 之间")
	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}
	
}