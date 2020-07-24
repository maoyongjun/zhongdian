/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.prodbase;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.prodbase.PjProdBase;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 生成评价基本信息DAO接口
 * @author chang
 * @version 2020-06-20
 */
@MyBatisDao
public interface PjProdBaseDao extends CrudDao<PjProdBase> {
	int hasDataCurMonth(@Param(value = "raterbyId") String raterbyId);
}