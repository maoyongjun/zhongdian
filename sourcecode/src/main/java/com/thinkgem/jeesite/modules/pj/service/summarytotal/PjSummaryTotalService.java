/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.summarytotal;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.summarytotal.PjSummaryTotal;
import com.thinkgem.jeesite.modules.pj.dao.summarytotal.PjSummaryTotalDao;

/**
 * 评价总分汇总Service
 * @author chang
 * @version 2020-07-08
 */
@Service
@Transactional(readOnly = true)
public class PjSummaryTotalService extends CrudService<PjSummaryTotalDao, PjSummaryTotal> {

	public PjSummaryTotal get(String id) {
		return super.get(id);
	}
	
	public List<PjSummaryTotal> findList(PjSummaryTotal pjSummaryTotal) {
		return super.findList(pjSummaryTotal);
	}
	
	public Page<PjSummaryTotal> findPage(Page<PjSummaryTotal> page, PjSummaryTotal pjSummaryTotal) {
		return super.findPage(page, pjSummaryTotal);
	}
	
	@Transactional(readOnly = false)
	public void save(PjSummaryTotal pjSummaryTotal) {
		super.save(pjSummaryTotal);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjSummaryTotal pjSummaryTotal) {
		super.delete(pjSummaryTotal);
	}
	
}