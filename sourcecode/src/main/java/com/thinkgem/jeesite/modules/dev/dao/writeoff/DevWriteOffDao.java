/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.writeoff;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.writeoff.DevWriteOff;

/**
 * 核销单DAO接口
 * @author myj
 * @version 2020-07-17
 */
@MyBatisDao
public interface DevWriteOffDao extends CrudDao<DevWriteOff> {
	
}