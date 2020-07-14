/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.category;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.category.PjValueCategory;
import com.thinkgem.jeesite.modules.pj.dao.category.PjValueCategoryDao;

/**
 * 类目维护Service
 * @author liu
 * @version 2020-06-19
 */
@Service
@Transactional(readOnly = true)
public class PjValueCategoryService extends CrudService<PjValueCategoryDao, PjValueCategory> {

	public PjValueCategory get(String id) {
		return super.get(id);
	}
	
	public List<PjValueCategory> findList(PjValueCategory pjValueCategory) {
		return super.findList(pjValueCategory);
	}
	
	public Page<PjValueCategory> findPage(Page<PjValueCategory> page, PjValueCategory pjValueCategory) {
		return super.findPage(page, pjValueCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(PjValueCategory pjValueCategory) {
		super.save(pjValueCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjValueCategory pjValueCategory) {
		super.delete(pjValueCategory);
	}
	
}