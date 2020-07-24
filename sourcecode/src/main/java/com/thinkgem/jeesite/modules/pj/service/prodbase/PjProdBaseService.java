/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.prodbase;

import java.util.List;

import com.thinkgem.jeesite.modules.pj.dao.prodchild.PjProdChildDao;
import com.thinkgem.jeesite.modules.pj.dao.prodparent.PjProdParentDao;
import com.thinkgem.jeesite.modules.pj.entity.prodchild.PjProdChild;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.prodbase.PjProdBase;
import com.thinkgem.jeesite.modules.pj.dao.prodbase.PjProdBaseDao;

/**
 * 生成评价基本信息Service
 * @author chang
 * @version 2020-06-20
 */
@Service
@Transactional(readOnly = true)
public class PjProdBaseService extends CrudService<PjProdBaseDao, PjProdBase> {

	//评价
	@Autowired
	private PjProdBaseDao pjProdBaseDao;
	@Autowired
	private PjProdParentDao pjProdParentDao;
	@Autowired
	private PjProdChildDao pjProdChildDao;

	public PjProdBase get(String id) {
		return super.get(id);
	}
	
	public List<PjProdBase> findList(PjProdBase pjProdBase) {
		return super.findList(pjProdBase);
	}
	
	public Page<PjProdBase> findPage(Page<PjProdBase> page, PjProdBase pjProdBase) {
		return super.findPage(page, pjProdBase);
	}
	
	@Transactional(readOnly = false)
	public void save(PjProdBase pjProdBase) {
		super.save(pjProdBase);
	}
	
	@Transactional(readOnly = false)
	public void delete(PjProdBase pjProdBase) {
		super.delete(pjProdBase);
	}

	@Transactional(readOnly = false)
	public void deleteAllEvalData(PjProdBase base){

		List<PjProdBase> baseList = pjProdBaseDao.findList(base);

		for(PjProdBase b : baseList){
			String code = b.getCode();
			PjProdParent parent = new PjProdParent();
			parent.setCode(code);
			List<PjProdParent> parentList = pjProdParentDao.findList(parent);
			for(PjProdParent p : parentList){
				PjProdChild child = new PjProdChild();
				child.setParentId(p.getId());
				List<PjProdChild> childList = pjProdChildDao.findList(child);
				for(PjProdChild c : childList){
					pjProdChildDao.delete(c);
				}
				pjProdParentDao.delete(p);
			}
			pjProdBaseDao.delete(b);
		}
	}

	/**
	 * 该被评价者当月是否已有数据
	 * @param raterbyId
	 * @return
	 */
	public boolean hasDataCurMonth(String raterbyId){
		if(pjProdBaseDao.hasDataCurMonth(raterbyId)>0){
			return true;
		}
		return false;
	}
	
}