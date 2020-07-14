/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.details;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.details.PjValueDetails;
import com.thinkgem.jeesite.modules.pj.dao.details.PjValueDetailsDao;

/**
 * 评定细则Service
 * @author chang
 * @version 2020-06-19
 */
@Service
@Transactional(readOnly = true)
public class PjValueDetailsService extends CrudService<PjValueDetailsDao, PjValueDetails> {

	public PjValueDetails get(String id) {
		return super.get(id);
	}
	
	public List<PjValueDetails> findList(PjValueDetails pjValueDetails) {
		return super.findList(pjValueDetails);
	}
	
	public Page<PjValueDetails> findPage(Page<PjValueDetails> page, PjValueDetails pjValueDetails) {
		page.setOrderBy("cate_id");
		return super.findPage(page, pjValueDetails);
	}
	
	@Transactional(readOnly = false)
	public void save(PjValueDetails pjValueDetails) {
		super.save(pjValueDetails);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjValueDetails pjValueDetails) {
		super.delete(pjValueDetails);
	}
	
}