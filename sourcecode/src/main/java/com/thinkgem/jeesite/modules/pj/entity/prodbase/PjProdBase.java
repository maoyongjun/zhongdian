/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.entity.prodbase;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 生成评价基本信息Entity
 * @author chang
 * @version 2020-06-20
 */
public class PjProdBase extends DataEntity<PjProdBase> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 评价标识
	private String title;		// 标题
	private String raterId;		// 评价者
	private String raterbyId;		// 被评价者
	private String statu;		// 状态
	private String note1;		// note1
	private String note2;		// note2
	private String note3;		// note3
	private String note4;		// note4

	private User rater;			//评价人
	private User raterby;		//被评价人
	
	public PjProdBase() {
		super();
	}

	public PjProdBase(String id){
		super(id);
	}


	public User getRater() {
		if(this.rater==null){
			this.rater = new User(this.raterId);
		}
		return rater;
	}

	public void setRater(User rater) {
		this.rater = rater;
	}

	public User getRaterby() {
		if(this.raterby==null){
			this.raterby = new User(this.raterbyId);
		}
		return raterby;
	}

	public void setRaterby(User raterby) {
		this.raterby = raterby;
	}

	@Length(min=1, max=64, message="评价标识长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=255, message="标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=64, message="评价者长度必须介于 1 和 64 之间")
	public String getRaterId() {
		return raterId;
	}

	public void setRaterId(String raterId) {
		this.raterId = raterId;
	}
	
	@Length(min=1, max=64, message="被评价者长度必须介于 1 和 64 之间")
	public String getRaterbyId() {
		return raterbyId;
	}

	public void setRaterbyId(String raterbyId) {
		this.raterbyId = raterbyId;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
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
	
	@Length(min=0, max=255, message="note3长度必须介于 0 和 255 之间")
	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}
	
	@Length(min=0, max=255, message="note4长度必须介于 0 和 255 之间")
	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}
	
}