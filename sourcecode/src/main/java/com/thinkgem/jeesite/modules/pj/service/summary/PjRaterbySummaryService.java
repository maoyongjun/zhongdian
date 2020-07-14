/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.summary;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.summary.PjRaterbySummary;
import com.thinkgem.jeesite.modules.pj.dao.summary.PjRaterbySummaryDao;

/**
 * 被评价人汇总Service
 * @author liu
 * @version 2020-07-03
 */
@Service
@Transactional(readOnly = true)
public class PjRaterbySummaryService extends CrudService<PjRaterbySummaryDao, PjRaterbySummary> {

	public PjRaterbySummary get(String id) {
		return super.get(id);
	}
	
	public List<PjRaterbySummary> findList(PjRaterbySummary pjRaterbySummary) {
		return super.findList(pjRaterbySummary);
	}
	
	public Page<PjRaterbySummary> findPage(Page<PjRaterbySummary> page, PjRaterbySummary pjRaterbySummary) {
		return super.findPage(page, pjRaterbySummary);
	}
	
	@Transactional(readOnly = false)
	public void save(PjRaterbySummary pjRaterbySummary) {
		super.save(pjRaterbySummary);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjRaterbySummary pjRaterbySummary) {
		super.delete(pjRaterbySummary);
	}
	
}