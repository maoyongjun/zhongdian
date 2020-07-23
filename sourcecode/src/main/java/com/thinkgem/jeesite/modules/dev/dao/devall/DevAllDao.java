/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.devall;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.devall.DevAll;

/**
 * 设备DAO接口
 * @author myj
 * @version 2020-07-23
 */
@MyBatisDao
public interface DevAllDao extends CrudDao<DevAll> {
	
}