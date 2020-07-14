/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.prodparent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.pj.dao.prodparent.PjProdParentDao;

/**
 * 生成评价父表Service
 * @author victor.chang
 * @version 2020-06-20
 */
@Service
@Transactional(readOnly = true)
public class PjProdParentService extends CrudService<PjProdParentDao, PjProdParent> {

	@Autowired
	private PjProdParentDao pjProdParentDao;

	public PjProdParent get(String id) {
		return super.get(id);
	}
	
	public List<PjProdParent> findList(PjProdParent pjProdParent) {
		return super.findList(pjProdParent);
	}
	
	public Page<PjProdParent> findPage(Page<PjProdParent> page, PjProdParent pjProdParent) {
		page.setOrderBy("code");
		return super.findPage(page, pjProdParent);
	}
	
	@Transactional(readOnly = false)
	public void save(PjProdParent pjProdParent) {
		super.save(pjProdParent);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjProdParent pjProdParent) {
		super.delete(pjProdParent);
	}

	public List<PjProdParent> getPjProdParentByRaterId(String raterId){
		return pjProdParentDao.getPjProdParentByRaterId(raterId);
	}
	
}