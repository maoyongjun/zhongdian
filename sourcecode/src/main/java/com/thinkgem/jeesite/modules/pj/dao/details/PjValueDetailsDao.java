/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.details;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.details.PjValueDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评定细则DAO接口
 * @author chang
 * @version 2020-06-19
 */
@MyBatisDao
public interface PjValueDetailsDao extends CrudDao<PjValueDetails> {
	List<PjValueDetails> getNotInList(@Param("cateArray")String[] cateArray, @Param("detailsArray")String[] detailsArray);
}