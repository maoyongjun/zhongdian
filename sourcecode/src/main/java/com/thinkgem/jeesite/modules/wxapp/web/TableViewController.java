package com.thinkgem.jeesite.modules.wxapp.web;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pj.entity.details.PjValueDetails;
import com.thinkgem.jeesite.modules.pj.entity.prodbase.PjProdBase;
import com.thinkgem.jeesite.modules.pj.entity.summarytotal.PjSummaryTotal;
import com.thinkgem.jeesite.modules.pj.service.category.PjValueCategoryService;
import com.thinkgem.jeesite.modules.pj.service.details.PjValueDetailsService;
import com.thinkgem.jeesite.modules.pj.service.eval.EvalService;
import com.thinkgem.jeesite.modules.pj.service.prodbase.PjProdBaseService;
import com.thinkgem.jeesite.modules.pj.service.prodchild.PjProdChildService;
import com.thinkgem.jeesite.modules.pj.service.prodparent.PjProdParentService;
import com.thinkgem.jeesite.modules.pj.service.summarytotal.PjSummaryTotalService;
import com.thinkgem.jeesite.modules.pj.vo.eval.SummaryTotalVo;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author VictorChang
 * @version 1.0
 * @date 2020/6/22 11:57
 */
@Controller
@RequestMapping(value = "${frontPath}/app")
public class TableViewController extends BaseController {
    @Autowired
    private PjProdBaseService pjProdBaseService;
    @Autowired
    private EvalService evalService;
    @Autowired
    private PjSummaryTotalService pjSummaryTotalService;


    @RequestMapping(value = {"raterbyEvalDetailsTable"})
    public String raterbyEvalDetailsTable(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/tableview/raterbyEvalDetailsTable";
    }


    /**
     * 被评价人详情报表
     *
     * @param jsonObject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"showRaterbyEvalDetailsTable"})
    public Map<String, Object> showRaterbyEvalDetailsTable(@RequestBody JSONObject jsonObject) {
        String raterbyId = jsonObject.getString("raterbyId");
        Date beginDate = jsonObject.getDate("beginInDate");
        Date endDate = jsonObject.getDate("endInDate");
//        List<EvalDetails> evalDetailsList =  evalService.getEvalDetailsListByRaterbyId(raterbyId,year,month);
        Map<String, Object> map = evalService.showRaterbyEvalDetailsMap(raterbyId, beginDate, endDate);

        return map;
    }


    @RequestMapping(value = {"raterEvalDetailsTable"})
    public String raterEvalDetailsTable(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/tableview/raterEvalDetailsTable";
    }

    /**
     * 评价人详情报表
     *
     * @param jsonObject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"showRaterEvalDetailsTable"})
    public Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> showRaterEvalDetailsTable(@RequestBody JSONObject jsonObject) {
        String raterId = jsonObject.getString("raterId");
        Date beginInDate = jsonObject.getDate("beginInDate");
        Date endInDate = jsonObject.getDate("endInDate");

        Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>>
                map = evalService.showRaterEvalDetailsTable(raterId, beginInDate, endInDate);

        return map;
    }



    /**
     * 价值评价修正List
     *
     * @return
     */
    @RequestMapping(value = {"raterbyEvalCorrectList"})
    public String raterbyEvalCorrectList(PjProdBase pjProdBase, HttpServletRequest request, HttpServletResponse response, Model model) {
        pjProdBase.setStatu("1");
        Page<PjProdBase> page = pjProdBaseService.findPage(new Page<PjProdBase>(request, response), pjProdBase);
        model.addAttribute("page", page);
        return "modules/tableview/raterbyEvalCorrectList";
    }

    /**
     * 修正汇总详情页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"raterbyEvalCorrect"})
    public String raterbyEvalCorrect(PjProdBase pjProdBase, Model model) {
        String raterbyId = pjProdBase.getRaterbyId();
        String note4 = pjProdBase.getNote4();
        Date createDate = new Date(note4);
        model.addAttribute("raterbyName", pjProdBase.getNote3());
        model.addAttribute("raterbyId", raterbyId);
        model.addAttribute("createDate", createDate);

        return "modules/tableview/raterbyEvalCorrect";
    }

    /**
     * 修正汇总
     *
     * @param object
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"getRaterbyEvalCorrect"})
    public Map<String, Object> getRaterbyEvalCorrect(@RequestBody JSONObject object) {
        String raterbyId = object.getString("raterbyId");
        Date createDate = object.getDate("createDate");
        Map<String, Object> map = evalService.showDeparture(raterbyId, createDate);
        return map;
    }

    /**
     * 总经理修正
     *
     * @param object
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"updateThirdScore"})
    public Map<String, Object> updateThirdScore(@RequestBody JSONObject object) {
        String id = object.getString("id");
        double thirdScore = object.getDouble("thirdScore");
        PjSummaryTotal pjSummaryTotal = pjSummaryTotalService.get(id);
        pjSummaryTotal.setThirdScore(thirdScore);
        pjSummaryTotalService.save(pjSummaryTotal);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "ok");
        return map;
    }


    /**
     * 价值评价汇总表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = {"evalTotalTable"})
    public String evalTotalTable(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/tableview/evalTotalTable";
    }

    /**
     * 获取总汇总表
     *
     * @param object
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"getTotalTable"})
    public Map<String, Object> getTotalTable(@RequestBody JSONObject object) {
        Date beginDate = object.getDate("beginDate");
        Date endDate = object.getDate("endDate");
        List<SummaryTotalVo> summaryTotalVoList = evalService.getSummaryTotalByCreateDate(beginDate, endDate);
        Map<String, Object> map = new HashMap<>();
        map.put("summaryTotalVoList", summaryTotalVoList);
        return map;
    }

    /**
     * 担当金价值评价汇总表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = {"moneySummaryTable"})
    public String moneySummaryTable(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/tableview/moneySummaryTable";
    }


    /**
     * 担当金汇总
     *
     * @param object
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"getMoneySummaryTable"})
    public Map<String, Object> getMoneySummaryTable(@RequestBody JSONObject object) {
        Date beginDate = object.getDate("beginDate");
        Date endDate = object.getDate("endDate");

        Map<String, Object> map = evalService.getMoneySummaryTable(beginDate, endDate);
        return map;
    }


}
