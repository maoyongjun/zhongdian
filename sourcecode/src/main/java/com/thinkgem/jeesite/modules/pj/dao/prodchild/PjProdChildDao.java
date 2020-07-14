/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.prodchild;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.prodchild.PjProdChild;
import com.thinkgem.jeesite.modules.pj.vo.eval.WXChildVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生成评价子表信息DAO接口
 * @author victor.chang
 * @version 2020-06-20
 */
@MyBatisDao
public interface PjProdChildDao extends CrudDao<PjProdChild> {

    List<WXChildVo> getWXChildListByParentId(String parentId);

    int updateRealScoreById(@Param("realScore")String realScore, @Param("id")String id);
}