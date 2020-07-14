/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.writeoffdetail;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.writeoffdetail.DevWriteOffDetail;

/**
 * 核销单明细DAO接口
 * @author myj
 * @version 2020-07-09
 */
@MyBatisDao
public interface DevWriteOffDetailDao extends CrudDao<DevWriteOffDetail> {
	
}