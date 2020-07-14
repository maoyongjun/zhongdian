/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.prodbase;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.prodbase.PjProdBase;
import com.thinkgem.jeesite.modules.pj.dao.prodbase.PjProdBaseDao;

/**
 * 生成评价基本信息Service
 * @author chang
 * @version 2020-06-20
 */
@Service
@Transactional(readOnly = true)
public class PjProdBaseService extends CrudService<PjProdBaseDao, PjProdBase> {

	public PjProdBase get(String id) {
		return super.get(id);
	}
	
	public List<PjProdBase> findList(PjProdBase pjProdBase) {
		return super.findList(pjProdBase);
	}
	
	public Page<PjProdBase> findPage(Page<PjProdBase> page, PjProdBase pjProdBase) {
		return super.findPage(page, pjProdBase);
	}
	
	@Transactional(readOnly = false)
	public void save(PjProdBase pjProdBase) {
		super.save(pjProdBase);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjProdBase pjProdBase) {
		super.delete(pjProdBase);
	}
	
}