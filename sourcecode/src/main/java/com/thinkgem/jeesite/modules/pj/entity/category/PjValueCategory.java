/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.entity.category;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 类目维护Entity
 * @author liu
 * @version 2020-06-19
 */
public class PjValueCategory extends DataEntity<PjValueCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String statu;		// 状态
	private String isMerge;		// 是否合并
	private String mergeItem;		// 合并类目
	private String note1;		// 备注1
	private String note2;		// 备注2
	private String note3;		// 备注3
	private String note4;		// 备注4
	
	public PjValueCategory() {
		super();
	}

	public PjValueCategory(String id){
		super(id);
	}

	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	@Length(min=0, max=1, message="是否合并长度必须介于 0 和 1 之间")
	public String getIsMerge() {
		return isMerge;
	}

	public void setIsMerge(String isMerge) {
		this.isMerge = isMerge;
	}
	
	public String getMergeItem() {
		return mergeItem;
	}

	public void setMergeItem(String mergeItem) {
		this.mergeItem = mergeItem;
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