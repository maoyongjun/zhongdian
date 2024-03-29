package com.thinkgem.jeesite.modules.pj.web.eval;

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
@RequestMapping(value = "${adminPath}/pj/eval")
public class EvalController extends BaseController {
    @Autowired
    private PjValueCategoryService pjValueCategoryService;
    @Autowired
    private PjProdBaseService pjProdBaseService;
    @Autowired
    private PjProdChildService pjProdChildService;
    @Autowired
    private PjProdParentService pjProdParentService;
    @Autowired
    private PjValueDetailsService pjValueDetailsService;
    @Autowired
    private EvalService evalService;
    @Autowired
    private PjSummaryTotalService pjSummaryTotalService;

    @RequiresPermissions("pj:eval:view")
    @RequestMapping(value = {"evalSelect"})
    public String evalSelect(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/pj/eval/evalSelect";
    }

    @RequiresPermissions("pj:eval:view")
    @RequestMapping(value = {"raterbyEvalDetailsTable"})
    public String raterbyEvalDetailsTable(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/pj/table/raterbyEvalDetailsTable";
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

    @RequiresPermissions("pj:eval:view")
    @RequestMapping(value = {"raterEvalDetailsTable"})
    public String raterEvalDetailsTable(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/pj/table/raterEvalDetailsTable";
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

    //发布评价
    @ResponseBody
    @RequiresPermissions("pj:eval:edit")
    @RequestMapping(value = "publish")
    public Map<String, Object> publish(Model model, @RequestBody JSONObject object, RedirectAttributes redirectAttributes) {
        String raterbyId = (String) object.get("raterbyId");
        Map<String, Object> map = new HashMap<>();
        if (pjProdBaseService.hasDataCurMonth(raterbyId)) {
            map.put("code", "error");
            map.put("msg", "该评价者当月已有评价数据");
        } else {
            boolean flag = evalService.publishEval(object);
            if (flag) {
                map.put("code", "ok");
                map.put("msg", "发布成功");
            } else {
                map.put("code", "error");
                map.put("msg", "发布失败");
            }
        }

        return map;
    }


    private String[] ifRepeat(String[] arr) {
        //实例化一个set集合
        Set set = new HashSet();
        //遍历数组并存入集合,如果元素已存在则不会重复存入
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        //返回Set集合的数组形式
        Object[] objects = set.toArray();
        String[] strArr = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            strArr[i] = (String) objects[i];
        }
        return strArr;
    }

    //获取类目列表
    @ResponseBody
    @RequestMapping(value = {"getDetailsListByCate"})
    public Map<String, Object> getDetailsListByCate(@RequestBody JSONObject object) {

        String ids = object.getString("cateIds");
        ids = "".equals(ids) ? "2" : (ids + ",2");
//        String raterId = object.getString("raterId");
//        User user = UserUtils.get(raterId);
//        try {
//            if (user != null && user.getCoefficient() >= 1.0) {
//                ids += ",5";
//            }
//            if (user != null && user.getCoefficient() >= 1.5) {
//                ids += ",6";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String[] idarr = ids.split(",");
        idarr = ifRepeat(idarr);
        Arrays.sort(idarr);
        Map<String, Object> map = new HashMap<>();
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < idarr.length; i++) {
            String id = idarr[i];
            PjValueDetails pjValueDetails = new PjValueDetails();
            pjValueDetails.setCateId(id);
            List<PjValueDetails> pList = pjValueDetailsService.findList(pjValueDetails);
            String cateName = "";
            if (pList.size() > 0) {
                cateName = pList.get(0).getPjValueCategory().getName();
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("cateName", cateName);
            map1.put("inList", pList);
            objectList.add(map1);
        }
        String cateIds = StringUtils.join(idarr, ",");
        map.put("cateIds", cateIds);
        map.put("objectList", objectList);
        return map;
    }

    /**
     * 价值评价修正List
     *
     * @return
     */
    @RequiresPermissions("pj:eval:view")
    @RequestMapping(value = {"raterbyEvalCorrectList"})
    public String raterbyEvalCorrectList(PjProdBase pjProdBase, HttpServletRequest request, HttpServletResponse response, Model model) {
        pjProdBase.setStatu("1");
        Page<PjProdBase> page = pjProdBaseService.findPage(new Page<PjProdBase>(request, response), pjProdBase);
        model.addAttribute("page", page);
        return "modules/pj/table/raterbyEvalCorrectList";
    }

    /**
     * 修正汇总详情页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("pj:eval:view")
    @RequestMapping(value = {"raterbyEvalCorrect"})
    public String raterbyEvalCorrect(PjProdBase pjProdBase, Model model) {
        String raterbyId = pjProdBase.getRaterbyId();
        String note4 = pjProdBase.getNote4();
        Date createDate = new Date(note4);
        model.addAttribute("raterbyName",pjProdBase.getNote3());
        model.addAttribute("raterbyId",raterbyId);
        model.addAttribute("createDate",createDate);

        return "modules/pj/table/raterbyEvalCorrect";
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
    @RequiresPermissions("pj:eval:view")
    @RequestMapping(value = {"evalTotalTable"})
    public String evalTotalTable(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/pj/table/evalTotalTable";
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
     * 价值评价汇总表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("pj:eval:view")
    @RequestMapping(value = {"moneySummaryTable"})
    public String moneySummaryTable(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/pj/table/moneySummaryTable";
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
