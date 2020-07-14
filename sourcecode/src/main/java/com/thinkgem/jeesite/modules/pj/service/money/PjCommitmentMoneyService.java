/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.money;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.money.PjCommitmentMoney;
import com.thinkgem.jeesite.modules.pj.dao.money.PjCommitmentMoneyDao;

/**
 * 担当金基数维护Service
 * @author liu
 * @version 2020-06-23
 */
@Service
@Transactional(readOnly = true)
public class PjCommitmentMoneyService extends CrudService<PjCommitmentMoneyDao, PjCommitmentMoney> {

	public PjCommitmentMoney get(String id) {
		return super.get(id);
	}
	
	public List<PjCommitmentMoney> findList(PjCommitmentMoney pjCommitmentMoney) {
		return super.findList(pjCommitmentMoney);
	}
	
	public Page<PjCommitmentMoney> findPage(Page<PjCommitmentMoney> page, PjCommitmentMoney pjCommitmentMoney) {
		return super.findPage(page, pjCommitmentMoney);
	}
	
	@Transactional(readOnly = false)
	public void save(PjCommitmentMoney pjCommitmentMoney) {
		super.save(pjCommitmentMoney);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjCommitmentMoney pjCommitmentMoney) {
		super.delete(pjCommitmentMoney);
	}
	
}