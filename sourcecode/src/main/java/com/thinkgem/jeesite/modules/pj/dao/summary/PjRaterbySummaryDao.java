/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.summary;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.summary.PjRaterbySummary;

/**
 * 被评价人汇总DAO接口
 * @author liu
 * @version 2020-07-03
 */
@MyBatisDao
public interface PjRaterbySummaryDao extends CrudDao<PjRaterbySummary> {
	
}