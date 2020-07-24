/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.summarytotal;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.summarytotal.PjSummaryTotal;
import com.thinkgem.jeesite.modules.pj.vo.eval.SummaryTotalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 评价总分汇总DAO接口
 *
 * @author chang
 * @version 2020-07-08
 */
@MyBatisDao
public interface PjSummaryTotalDao extends CrudDao<PjSummaryTotal> {
    List<SummaryTotalVo> getSummaryTotalByRaterIdCreateDate(@Param(value = "raterbyId") String raterbyId, @Param(value = "createDate") Date createDate);

    List<SummaryTotalVo> getSummaryTotalByCreateDate(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate);

}