/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.service.prodchild;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.entity.prodchild.PjProdChild;
import com.thinkgem.jeesite.modules.pj.dao.prodchild.PjProdChildDao;

/**
 * 生成评价子表信息Service
 *
 * @author victor.chang
 * @version 2020-06-20
 */
@Service
@Transactional(readOnly = true)
public class PjProdChildService extends CrudService<PjProdChildDao, PjProdChild> {

	private PjProdChildDao pjProdChildDao;

    public PjProdChild get(String id) {
        return super.get(id);
    }

    public List<PjProdChild> findList(PjProdChild pjProdChild) {
        return super.findList(pjProdChild);
    }

    public Page<PjProdChild> findPage(Page<PjProdChild> page, PjProdChild pjProdChild) {
        page.setOrderBy("value_detail_id");
        return super.findPage(page, pjProdChild);
    }

    @Transactional(readOnly = false)
    public void save(PjProdChild pjProdChild) {
        super.save(pjProdChild);
    }

    @Transactional(readOnly = false)
    public void delete(PjProdChild pjProdChild) {
        super.delete(pjProdChild);
    }

	/**
	 * 更新真实分数
	 * @param realScore
	 * @param id
	 * @return
	 */
    @Transactional(readOnly = false)
    int updateRealScoreById(String realScore, String id) {
        return pjProdChildDao.updateRealScoreById(realScore,id);
    }
}