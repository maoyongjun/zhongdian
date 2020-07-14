/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.category;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.category.PjValueCategory;

/**
 * 类目维护DAO接口
 * @author liu
 * @version 2020-06-19
 */
@MyBatisDao
public interface PjValueCategoryDao extends CrudDao<PjValueCategory> {
	
}