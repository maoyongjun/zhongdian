/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.money;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.money.PjCommitmentMoney;

/**
 * 担当金基数维护DAO接口
 * @author liu
 * @version 2020-06-23
 */
@MyBatisDao
public interface PjCommitmentMoneyDao extends CrudDao<PjCommitmentMoney> {
	
}