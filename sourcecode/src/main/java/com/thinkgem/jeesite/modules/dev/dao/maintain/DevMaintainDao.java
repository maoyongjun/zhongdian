/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.maintain;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.maintain.DevMaintain;

/**
 * 设备保养DAO接口
 * @author myj
 * @version 2020-07-06
 */
@MyBatisDao
public interface DevMaintainDao extends CrudDao<DevMaintain> {
	
}