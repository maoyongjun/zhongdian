package com.thinkgem.jeesite.modules.pj.dao.eval;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pj.entity.summary.PjRaterbySummary;
import com.thinkgem.jeesite.modules.pj.vo.eval.EvalDetails;
import com.thinkgem.jeesite.modules.pj.vo.eval.EvalDetailsRater;
import com.thinkgem.jeesite.modules.pj.vo.eval.SummaryVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@MyBatisDao
public interface EvalDao {
    List<EvalDetails> getEvalDetailsListByRaterbyId(@Param("raterbyId") String raterbyId, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    List<EvalDetailsRater> getEvalDetailsRaterListByRaterId(@Param("raterId") String raterId, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    List<PjRaterbySummary> getsummaryList(@Param("parentId") String parentId);

    List<SummaryVo> getSummaryVoList(@Param("raterbyId") String raterbyId, @Param("createDate") Date createDate, @Param("cateId") String cateId);

    List<SummaryVo> getRaterMoneySummaryVoList(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("cateId") String cateId);

    List<SummaryVo> getRaterByMoneySummaryVoList(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("cateId") String cateId);

    List<SummaryVo> getRaterScoreMoneySummaryVoList(@Param("raterbyId") String raterbyId, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("cateId") String cateId);

}