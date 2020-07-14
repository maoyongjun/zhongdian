/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.contractpic;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.contractpic.DevContractPic;

/**
 * 合同图片DAO接口
 * @author myj
 * @version 2020-07-13
 */
@MyBatisDao
public interface DevContractPicDao extends CrudDao<DevContractPic> {
	
}