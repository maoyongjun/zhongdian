/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.service.contractpic;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dev.entity.contractpic.DevContractPic;
import com.thinkgem.jeesite.modules.dev.dao.contractpic.DevContractPicDao;

/**
 * 合同图片Service
 * @author myj
 * @version 2020-07-13
 */
@Service
@Transactional(readOnly = true)
public class DevContractPicService extends CrudService<DevContractPicDao, DevContractPic> {

	public DevContractPic get(String id) {
		return super.get(id);
	}
	
	public List<DevContractPic> findList(DevContractPic devContractPic) {
		return super.findList(devContractPic);
	}
	
	public Page<DevContractPic> findPage(Page<DevContractPic> page, DevContractPic devContractPic) {
		return super.findPage(page, devContractPic);
	}
	
	@Transactional(readOnly = false)
	public void save(DevContractPic devContractPic) {
		super.save(devContractPic);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevContractPic devContractPic) {
		super.delete(devContractPic);
	}
	
}