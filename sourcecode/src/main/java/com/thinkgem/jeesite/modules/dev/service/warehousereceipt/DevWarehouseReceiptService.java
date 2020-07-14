/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.warehousereceipt;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.warehousereceipt.DevWarehouseReceipt;
import com.thinkgem.jeesite.modules.dev.dao.warehousereceipt.DevWarehouseReceiptDao;

/**
 * 入库单Service
 * @author myj
 * @version 2020-07-07
 */
@Service
@Transactional(readOnly = true)
public class DevWarehouseReceiptService extends CrudService<DevWarehouseReceiptDao, DevWarehouseReceipt> {

	public DevWarehouseReceipt get(String id) {
		return super.get(id);
	}
	
	public List<DevWarehouseReceipt> findList(DevWarehouseReceipt devWarehouseReceipt) {
		return super.findList(devWarehouseReceipt);
	}
	
	public Page<DevWarehouseReceipt> findPage(Page<DevWarehouseReceipt> page, DevWarehouseReceipt devWarehouseReceipt) {
		return super.findPage(page, devWarehouseReceipt);
	}
	
	@Transactional(readOnly = false)
	public void save(DevWarehouseReceipt devWarehouseReceipt) {
		super.save(devWarehouseReceipt);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevWarehouseReceipt devWarehouseReceipt) {
		super.delete(devWarehouseReceipt);
	}
	
}