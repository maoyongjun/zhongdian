/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.dao.prodparent;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.pj.vo.eval.WXParentVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 生成评价父表DAO接口
 *
 * @author victor.chang
 * @version 2020-06-20
 */
@MyBatisDao
public interface PjProdParentDao extends CrudDao<PjProdParent> {
    List<PjProdParent> getPjProdParentByRaterId(String raterId);

    List<WXParentVo> getWXParentVoByRaterId(String raterId);

    List<PjProdParent> getParentListByRaterbyIdStarEndDate(@Param(value = "raterbyId")String raterbyId, @Param(value = "startDate")Date startDate, @Param(value = "endDate")Date endDate);

    List<PjProdParent> getParentListByRaterIdStarEndDate(@Param(value = "raterId")String raterId, @Param(value = "startDate")Date startDate, @Param(value = "endDate")Date endDate);

}