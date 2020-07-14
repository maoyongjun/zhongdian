/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.dao.warehousereceipt;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dev.entity.warehousereceipt.DevWarehouseReceipt;

/**
 * 入库单DAO接口
 * @author myj
 * @version 2020-07-07
 */
@MyBatisDao
public interface DevWarehouseReceiptDao extends CrudDao<DevWarehouseReceipt> {
	
}