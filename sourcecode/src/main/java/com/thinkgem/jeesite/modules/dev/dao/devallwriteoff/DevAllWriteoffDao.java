/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.devallwriteoff;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.devallwriteoff.DevAllWriteoff;

/**
 * 核销设备DAO接口
 * @author myj
 * @version 2020-07-23
 */
@MyBatisDao
public interface DevAllWriteoffDao extends CrudDao<DevAllWriteoff> {
	
}