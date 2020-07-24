package com.thinkgem.jeesite.modules.pj.service.eval;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.pj.dao.details.PjValueDetailsDao;
import com.thinkgem.jeesite.modules.pj.dao.eval.EvalDao;
import com.thinkgem.jeesite.modules.pj.dao.money.PjCommitmentMoneyDao;
import com.thinkgem.jeesite.modules.pj.dao.prodbase.PjProdBaseDao;
import com.thinkgem.jeesite.modules.pj.dao.prodchild.PjProdChildDao;
import com.thinkgem.jeesite.modules.pj.dao.prodparent.PjProdParentDao;
import com.thinkgem.jeesite.modules.pj.dao.summary.PjRaterbySummaryDao;
import com.thinkgem.jeesite.modules.pj.dao.summarytotal.PjSummaryTotalDao;
import com.thinkgem.jeesite.modules.pj.entity.details.PjValueDetails;
import com.thinkgem.jeesite.modules.pj.entity.money.PjCommitmentMoney;
import com.thinkgem.jeesite.modules.pj.entity.prodbase.PjProdBase;
import com.thinkgem.jeesite.modules.pj.entity.prodchild.PjProdChild;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.pj.entity.summary.PjRaterbySummary;
import com.thinkgem.jeesite.modules.pj.entity.summarytotal.PjSummaryTotal;
import com.thinkgem.jeesite.modules.pj.vo.eval.*;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Chang
 * @version 1.0
 * @date 2020/6/24 9:23
 */
@Service
@Transactional(readOnly = true)
public class EvalService {

    @Autowired
    private EvalDao evalDao;
    @Autowired
    private PjProdBaseDao pjProdBaseDao;
    @Autowired
    private PjProdParentDao pjProdParentDao;
    @Autowired
    private PjProdChildDao pjProdChildDao;
    @Autowired
    private PjValueDetailsDao pjValueDetailsDao;
    @Autowired
    private PjRaterbySummaryDao pjRaterbySummaryDao;
    @Autowired
    private PjSummaryTotalDao pjSummaryTotalDao;
    @Autowired
    private PjCommitmentMoneyDao pjCommitmentMoneyDao;

    /**
     * publishEval() 的子方法
     *
     * @param raterbyId
     * @param title
     * @param code
     * @return
     */
    private int insertPjProdBase(String raterbyId, String title, String code) {
        PjProdBase pjProdBase = new PjProdBase();
        pjProdBase.setId(IdGen.uuid());
        pjProdBase.setRaterbyId(raterbyId);
        pjProdBase.setTitle(title);
        pjProdBase.setCode(code);
        pjProdBase.setStatu("0");
        pjProdBase.setCreateDate(new Date());
        return pjProdBaseDao.insert(pjProdBase);
    }

    /**
     * publishEval() 的子方法
     *
     * @param raterId
     * @param raterbyId
     * @param code
     * @return
     */
    private String insertPjProdParent(String raterId, String raterbyId, String code) {
        PjProdParent pjProdParent = new PjProdParent();
        String id = IdGen.uuid();
        pjProdParent.setId(id);
        pjProdParent.setRaterId(raterId);
        pjProdParent.setRaterbyId(raterbyId);
        pjProdParent.setCode(code);
        pjProdParent.setStatu("0");
        pjProdParent.setCreateDate(new Date());
        pjProdParent.setCoefficient(UserUtils.get(raterId).getCoefficient());
        pjProdParentDao.insert(pjProdParent);
        return id;
    }

    /**
     * publishEval() 的子方法
     *
     * @param detailId
     * @param parentId
     * @param createDate
     * @return
     */
    private int insertPjProdChild(String detailId, String parentId, Date createDate) {
        PjProdChild pjProdChild = new PjProdChild();
        pjProdChild.setId(IdGen.uuid());
        pjProdChild.setValueDetailId(detailId);
        pjProdChild.setParentId(parentId);
        pjProdChild.setStatu("0");
        pjProdChild.setCreateDate(createDate);
        return pjProdChildDao.insert(pjProdChild);
    }


    /**
     * 发布评价
     *
     * @param object
     * @return
     */
    @Transactional(readOnly = false)
    public boolean publishEval(JSONObject object) {

        String code = IdGen.uuid();
        //base
        String raterbyId = (String) object.get("raterbyId");
        User user = UserUtils.get(raterbyId);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.add(Calendar.MONTH, 1);
        Date secondMonthDate = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date thirdMonthDate = cal.getTime();
        String title = user.getName() + "-" + year + "年" + month + "月" + "价值考评";

        int inBaseRes = insertPjProdBase(raterbyId, title, code);

        //parent
        ArrayList eveals = (ArrayList) object.get("evals");
        for (int i = 0; i < eveals.size(); i++) {
            Map<Object, Object> objectMap = (LinkedHashMap<Object, Object>) eveals.get(i);
            String raterId = (String) objectMap.get("raterId");
            ArrayList details = (ArrayList) objectMap.get("details");
            String cateStr = (String) objectMap.get("bigCate"); //获取选择的大项
            if ("".equals(raterId) || details.size() == 0) {
                continue;
            }
            String parentId = insertPjProdParent(raterId, raterbyId, code);
            //child 插入当月评价细则
            for (int j = 0; j < details.size(); j++) {
                String detailId = (String) details.get(j);
                insertPjProdChild(detailId, parentId, new Date());
            }
            String[] cateArray = cateStr.split(",");    //大项数组
            String[] detailsArray = (String[]) details.toArray(new String[0]);  //细则
            List<PjValueDetails> notSelectList = pjValueDetailsDao.getNotInList(cateArray, detailsArray);

            int no = 0;//基偶数
            for (PjValueDetails valueDetails : notSelectList) {
                String detailId = valueDetails.getId();
                Date createDate = (no++ % 2 == 0) ? secondMonthDate : thirdMonthDate;
                insertPjProdChild(detailId, parentId, createDate);
            }
        }
        return true;
    }

    /**
     * getEvalListById的子方法
     *
     * @param createDate
     * @return eg. 2020年6月-2020年8月
     */
    private String getStartEndDate(Date createDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(createDate);
        String year = cal.get(Calendar.YEAR) + "";
        String month = cal.get(Calendar.MONTH) + 1 + "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(Calendar.MONTH, 2);
        String nextYear = calendar.get(Calendar.YEAR) + "";
        String nextMonth = calendar.get(Calendar.MONTH) + 1 + "";

        return year + "年" + month + "月" + "-" + nextYear + "年" + nextMonth + "月";
    }

    /**
     * 返回微信需要的父级列表
     *
     * @param raterId
     * @return
     */
    public List<WXParentVo> getEvalListById(String raterId, String title) {
        List<WXParentVo> wxParentVoList = pjProdParentDao.getWXParentVoByRaterIdOrTitle(raterId, title);
        for (WXParentVo parentVo : wxParentVoList) {
            Date createDate = parentVo.getCreateDate();
            String startEndDate = getStartEndDate(createDate);
            parentVo.setStartEndDate(startEndDate);
        }
        return wxParentVoList;
    }

    /**
     * 返回微信需要的打分列表
     *
     * @param parentId
     * @return
     */
    public List<WXChildVo> getWXChildListByParentId(String parentId) {
        return pjProdChildDao.getWXChildListByParentId(parentId);
    }

    /**
     * 微信提交分数
     *
     * @param commitType
     * @param wxChildVoList
     * @return
     */
    @Transactional(readOnly = false)
    public int commitRealScore(String commitType, List<Map<String, Object>> wxChildVoList) {
        int count = 0;
        for (Map map : wxChildVoList) {     //保存
            Object realScoreObj = map.get("realScore");
            String realScore = realScoreObj.toString().trim();
            realScore = "".equals(realScore) ? "0" : realScore;
            String id = (String) map.get("id");
            count += pjProdChildDao.updateRealScoreById(realScore, id);
        }

        if ("2".equals(commitType) && wxChildVoList.size() > 0) {   //提交
            String childId = (String) wxChildVoList.get(0).get("id");
            PjProdChild child = pjProdChildDao.get(childId);
            if (child != null) {
                PjProdParent parent = pjProdParentDao.get(child.getParentId());
                Date createDate = parent.getCreateDate();
                int months = new Date().getMonth() - createDate.getMonth();

                months = 2; // 测试 。。。。。。。。。。。。。。。。

                parent.setUpdateDate(new Date());
                count += pjProdParentDao.update(parent);
                if (months >= 2) {
                    parent.setStatu("1");   //第三个月，修改parent的状态为已评价
                    count += pjProdParentDao.update(parent);

                    //添加至统计表 ... 每个评价第三个月提交后生成类目汇总数据
                    String parentId = parent.getId();
                    List<PjRaterbySummary> summaryList = evalDao.getsummaryList(parentId);

                    for (PjRaterbySummary summary : summaryList) {
                        summary.setId(IdGen.uuid());
                        pjRaterbySummaryDao.insert(summary);
                    }
                }

                PjProdParent query = new PjProdParent();
                query.setCode(parent.getCode());
                List<PjProdParent> parentList = pjProdParentDao.findList(query);
                boolean flag = true;
                for (PjProdParent p : parentList) {
                    if (!"1".equals(p.getStatu())) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {   //修改base状态为已提交 所有人都已打分
                    PjProdBase baseQuery = new PjProdBase();
                    baseQuery.setCode(parent.getCode());
                    PjProdBase base = pjProdBaseDao.findList(baseQuery).get(0);
                    if (base != null) {
                        base.setStatu("1");
                        pjProdBaseDao.update(base);

                        //进行总分汇总
                        if (addToPjSummaryTotal(base) != 0) {   //汇总失败
                            count = -1;
                        }
                    }
                }
            }
        }

        return count;
    }


    /**
     * 一个季度评价完后，进行总分汇总   添加数据到 pj_summary_total
     *
     * @param base
     * @return
     */
    private int addToPjSummaryTotal(PjProdBase base) {

        String raterbyId = base.getRaterbyId();
        Date createDate = base.getCreateDate();

        List<SummaryVo> summaryVoList = evalDao.getSummaryVoList(raterbyId, createDate, "");

        List<SummaryVo> cate2List = evalDao.getSummaryVoList(raterbyId, createDate, "2");//行为特征
        List<SummaryVo> cate5List = evalDao.getSummaryVoList(raterbyId, createDate, "5");//担当特征
        List<SummaryVo> cate6List = evalDao.getSummaryVoList(raterbyId, createDate, "6");//担当成效
        List<SummaryVo> cate3List = evalDao.getSummaryVoList(raterbyId, createDate, "3");//伪奋斗修正

        Map<String, Double> xwSecondMap = new HashMap<>();
        Map<String, Double> ddtzSecondMap = new HashMap<>();
        crrentScoreByCate(cate2List, new HashMap<>(), xwSecondMap);
        crrentScoreByCate(cate5List, new HashMap<>(), ddtzSecondMap);

        double firstTotal = qzAvgScore(summaryVoList);
        double xwAvg = xwSecondMap.get("AvgScore");
        double xwSecondScore = xwSecondMap.get("secondScore");
        double ddtzAvg = ddtzSecondMap.get("AvgScore");
        double ddtzSecondScore = ddtzSecondMap.get("secondScore");

        double ddcxAvg = qzAvgScore(cate6List);
        double wfdAvg = qzAvgScore(cate3List);
        double secondTotal = firstTotal - xwAvg - ddtzAvg + xwSecondScore + ddtzSecondScore;
        double dandangzhi = ddtzAvg + ddcxAvg;

        PjSummaryTotal pjSummaryTotal = new PjSummaryTotal();
        pjSummaryTotal.setId(IdGen.uuid());
        pjSummaryTotal.setRaterbyId(raterbyId);
        pjSummaryTotal.setBaseScore(firstTotal);
        pjSummaryTotal.setSecondScore(secondTotal);
        pjSummaryTotal.setThirdScore(secondTotal);
        pjSummaryTotal.setBearScore(dandangzhi);
        pjSummaryTotal.setFakeScore(wfdAvg);
        Map<String, Double> cateScoreMap = getCateSumScore();
        double bearRate = Math.pow(dandangzhi / (cateScoreMap.get("5") + cateScoreMap.get("6")), 2);
        pjSummaryTotal.setBearRate(bearRate);
        pjSummaryTotal.setCreateDate(createDate);

        pjSummaryTotalDao.insert(pjSummaryTotal);

        return 0;
    }


    /**
     * 评价人/被评价人报表详情Map初始化
     *
     * @param parents
     * @param flag
     * @return
     */
    public Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> initEvalDetailsMap(List<PjProdParent> parents, String flag) {
        Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> map = new LinkedHashMap<>();
        PjValueDetails pjValueDetail = new PjValueDetails();
        List<PjValueDetails> pjValueDetails = pjValueDetailsDao.findAllList(pjValueDetail);

        for (PjValueDetails details : pjValueDetails) {
            if (details.getPjValueCategory() == null || details.getPjValueCategory().getStatu() == null || !details.getPjValueCategory().getStatu().equals("0")) {
                continue;
            }
            String cateName = details.getPjValueCategory().getName();

            if (!map.containsKey(cateName)) {
                LinkedHashMap<String, LinkedHashMap<String, Double>> newCateMap = new LinkedHashMap<>();
                map.put(cateName, newCateMap);
            }
            LinkedHashMap<String, LinkedHashMap<String, Double>> cateMap = map.get(cateName);
            String detailsName = details.getName();

            if (!cateMap.containsKey(detailsName)) {
                LinkedHashMap<String, Double> newScoreMap = new LinkedHashMap<>();
                newScoreMap.put("分值", details.getScore());
                cateMap.put(detailsName, newScoreMap);
            }
            LinkedHashMap<String, Double> scoreMap = cateMap.get(detailsName);
            for (PjProdParent parent : parents) {
                if ("1".equals(flag)) {
                    scoreMap.put(parent.getRater().getName(), 999.0);
                } else if ("2".equals(flag)) {
                    scoreMap.put(parent.getRaterby().getName(), 999.0);
                }
            }
            if ("1".equals(flag)) {
                scoreMap.put("权重均值", 0.0);
            } else if ("2".equals(flag)) {
                scoreMap.put("平均值", 0.0);
            }
        }

        return map;
    }


    // 删除ArrayList中重复元素，保持顺序，返回系数和
    private double removeDupWithOrderReturnCoeffSum(List<PjProdParent> list, String flag) {
        double sum = 0.0;
        Set set = new HashSet();
        List newList = new ArrayList();
        for (PjProdParent element : list) {
            if (flag.equals("1")) {
                if (set.add(element.getRaterId())) {
                    newList.add(element);
                    double raterCoef = element.getCoefficient();
                    sum += raterCoef;
                }
            } else if (flag.equals("2")) {
                if (set.add(element.getRaterbyId())) {
                    newList.add(element);
                    double raterCoef = element.getCoefficient();
                    sum += raterCoef;
                }
            }
        }
        list.clear();
        list.addAll(newList);
        return sum;
    }

    /**
     * 获取类目分数和，<cateId, scoreSum>
     *
     * @return Map ==》 key:cateId  Double:分数和
     */
    private Map<String, Double> getCateSumScore() {
        Map<String, Double> map = new HashMap<>();
        List<PjValueDetails> valueDetails = pjValueDetailsDao.findList(new PjValueDetails());
        for (PjValueDetails value : valueDetails) {
            double score = value.getScore() == null ? 0.0 : value.getScore();
            double preScore = map.get(value.getCateId()) == null ? 0.0 : map.get(value.getCateId());
            map.put(value.getCateId(), score + preScore);
        }

        return map;
    }

    /**
     * 获取橙色预警的被评价者
     *
     * @param raterSumMap
     * @return
     */
    private Map<String, Boolean> getOrangeMap(Map<String, Map<String, Double>> raterSumMap) {
        Map<String, Double> cateSumMap = getCateSumScore();
        Map<String, Boolean> orangeMap = new HashMap<>();

        double striveSum = cateSumMap.get("1") + cateSumMap.get("2");
        double bearSum = cateSumMap.get("5") + cateSumMap.get("6");

        for (Map.Entry<String, Map<String, Double>> entry : raterSumMap.entrySet()) {
            String raterName = entry.getKey();
            Map<String, Double> raterScoreMap = entry.getValue();
            Double cateScore1 = raterScoreMap.get("1");
            Double cateScore2 = raterScoreMap.get("2");
            Double cateScore5 = raterScoreMap.get("5");
            Double cateScore6 = raterScoreMap.get("6");
            if (cateScore1 != null && cateScore2 != null && cateScore5 != null && cateScore6 != null) {
                double r = (cateScore1 + cateScore2) / striveSum
                        - (cateScore5 + cateScore6) / bearSum;
                System.out.println("RRRRRRRR-" + raterName + ":" + r);
                orangeMap.put(raterName, r > 0.35); //实际使用
            }
        }

        return orangeMap;
    }

    /**
     * 获取十级以上的系数之和
     *
     * @param parents
     * @return
     */
    private double getCoef10Sum(List<PjProdParent> parents) {
        double sum = 0.0;
        for (PjProdParent p : parents) {
            if (p.getCoefficient() >= 1.0) {
                sum += p.getCoefficient();
            }
        }
        return sum;
    }

    /**
     * 获取导师的系数之和
     *
     * @param parents
     * @return
     */
    private double getCoefTeacherSum(List<PjProdParent> parents) {
        double sum = 0.0;
        for (PjProdParent p : parents) {
            if (p.getCoefficient() >= 1.5) {
                sum += p.getCoefficient();
            }
        }
        return sum;
    }


    /**
     * 获取细则系数和Map
     *
     * @param evalDetailsList
     * @param tableMap
     * @return
     */
    private Map<String, Double> getCateCoeffSumMap(List<EvalDetails> evalDetailsList, Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> tableMap) {
        Map<String, Double> map = new HashMap<>();
        Map<String, Double> cateMap = new HashMap<>();
        for (EvalDetails details : evalDetailsList) {
            String raterName = details.getRaterName();
            String cateName = details.getCateName();
            String detailsName = details.getDetailsName();
            double coeff = details.getCoefficient();

            if ("在与奋斗者团队合作时正直诚信的奋斗态度。".equals(detailsName)) {
                System.out.println(raterName + ": " + coeff);
            }
            if (!map.containsKey(detailsName)) {
                map.put(detailsName, 0.0);
            }
            double sumCoeff = map.get(detailsName) + coeff;
            map.put(detailsName, sumCoeff);
            cateMap.put(cateName, sumCoeff);
        }

        return cateMap;
    }

    /**
     * 被评价人报表Map生成
     *
     * @param raterbyId
     * @param beginDate
     * @param endDate
     * @return
     */
    public Map<String, Object> showRaterbyEvalDetailsMap(String raterbyId, Date beginDate, Date endDate) {
        Map<String, Object> map = new HashMap<>();
        List<EvalDetails> evalDetailsList = evalDao.getEvalDetailsListByRaterbyId(raterbyId, beginDate, endDate);
        List<PjProdParent> parents = pjProdParentDao.getParentListByRaterbyIdStarEndDate(raterbyId, beginDate, endDate);

        Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> tableMap = initEvalDetailsMap(parents, "1");
        Map<String, Map<String, Double>> raterSumMap = new LinkedHashMap<>();
        Map<String, Double> cateCoeffSumMap = getCateCoeffSumMap(evalDetailsList, tableMap);

        for (EvalDetails details : evalDetailsList) {
            String cateId = details.getCateId();
            String raterName = details.getRaterName();
            double realScore = details.getRealScore();
            String cateName = details.getCateName();
            String detailsName = details.getDetailsName();

            try {
                //table
                LinkedHashMap<String, Double> scoreMap = tableMap.get(cateName).get(detailsName);
                scoreMap.put(raterName, realScore);
                double coef = details.getCoefficient();
                double coefTemp = cateCoeffSumMap.get(cateName);

                double avg = scoreMap.get("权重均值") + realScore * (coef / coefTemp);
                avg = keepNDecimal(avg, 7);
                scoreMap.put("权重均值", avg);

                //sum
                if (!raterSumMap.containsKey(raterName)) {
                    raterSumMap.put(raterName, new LinkedHashMap<>());
                }
                Map<String, Double> scoreSumMap = raterSumMap.get(raterName);
                Double preScore = scoreSumMap.get(cateId);
                double scoreSum = preScore == null ? 0.0 : preScore + realScore;
                scoreSumMap.put(cateId, scoreSum);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, Boolean> orangeMap = getOrangeMap(raterSumMap);

        map.put("tableMap", tableMap);
        map.put("orangeMap", orangeMap);

        return map;
    }


    /**
     * 评价人报表Map生成
     *
     * @param raterId
     * @param beginInDate
     * @param endInDate
     * @return
     */
    public Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> showRaterEvalDetailsTable(String raterId, Date beginInDate, Date endInDate) {
        List<EvalDetailsRater> evalDetailsList = evalDao.getEvalDetailsRaterListByRaterId(raterId, beginInDate, endInDate);

        List<PjProdParent> parents = pjProdParentDao.getParentListByRaterIdStarEndDate(raterId, beginInDate, endInDate);
        Map<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> map = initEvalDetailsMap(parents, "2");

        int raterbyNum = parents.size();
        for (EvalDetailsRater details : evalDetailsList) {
            String cateName = details.getCateName();
            String detailsName = details.getDetailsName();
            try {
                LinkedHashMap<String, Double> scoreMap = map.get(cateName).get(detailsName);
                String raterbyName = details.getRaterbyName();
                double realScore = details.getRealScore();
                scoreMap.put(raterbyName, realScore);
                double avg = scoreMap.get("平均值") + realScore / raterbyNum;
                avg = keepNDecimal(avg, 7);
                scoreMap.put("平均值", avg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }


    /**
     * 保留num位小数
     *
     * @param d
     * @return
     */
    private double keepNDecimal(double d, int num) {
        double n = Math.pow(10, num);
        double dou = Math.round(d * n) / n;
        return dou;
    }


    private double getCoeffSum(List<SummaryVo> list) {
        double sum = 0.0;
        Set set = new HashSet();
        for (SummaryVo summary : list) {
            if (set.add(summary.getRaterId())) {
                sum += summary.getReterCoefficient();
            }
        }
        return sum;
    }

    /**
     * 通过pj_raterby_summary表内的数据计算 权重均值
     *
     * @param list
     * @return
     */
    private double qzAvgScore(List<SummaryVo> list) {
        double avgScore = 0.0;
        Map<String, List<SummaryVo>> cateMap = new HashMap<>();
        for (SummaryVo vo : list) {
            String cateId = vo.getCateId();
            if (!cateMap.containsKey(cateId)) {
                cateMap.put(cateId, new ArrayList<>());
            }
            cateMap.get(cateId).add(vo);
        }

        for (Map.Entry<String, List<SummaryVo>> entry : cateMap.entrySet()) {
            List<SummaryVo> cateList = entry.getValue();
            double coefSum = getCoeffSum(cateList);
            for (SummaryVo sumVo : cateList) {
                avgScore += (sumVo.getScores() * (sumVo.getReterCoefficient() / coefSum));
            }
        }

        return avgScore;
    }

    /**
     * 计算不同类目的权重，偏离率
     *
     * @param list        入参数据
     * @param pMap        存放偏离率
     * @param secondScore 存放修正后的数据
     */
    private void crrentScoreByCate(List<SummaryVo> list, Map<String, Double> pMap, Map<String, Double> secondScore) {
        double coefSum = 0.0;
        int raterCount = list.size();

        //计算系数之和，初始化偏离率map
        for (SummaryVo sumVo : list) {
            coefSum += sumVo.getReterCoefficient();
            pMap.put(sumVo.getRaterName(), 0.0);
        }

        //计算权重均分
        double avgScore = qzAvgScore(list);

        double max = -100.0, min = 100.0, zSum = 0.0, fSum = 0.0;
        int xwZCount = 0, xwFCount = 0;
        //计算偏离率，put到map
        for (SummaryVo xw : list) {
            double r = keepNDecimal((xw.getScores() - avgScore) / avgScore, 7);
            pMap.put(xw.getRaterName(), r);
            max = Math.max(max, r);
            min = Math.min(min, r);
            if (r >= 0) {
                zSum += r;
                xwZCount++;
            } else {
                fSum += r;
                xwFCount++;
            }
        }

        xwFCount = xwFCount == 0 ? 1 : xwFCount;
        xwZCount = xwZCount == 0 ? 1 : xwZCount;
        raterCount = raterCount == 0 ? 1 : raterCount;

        double xwZR = (max + fSum / xwFCount) / raterCount;
        double xwFR = (min + zSum / xwZCount) / raterCount;
        double secondXwScore = avgScore - avgScore * xwZR - avgScore * xwFR;

        secondScore.put("AvgScore", keepNDecimal(avgScore, 7));
        secondScore.put("secondScore", keepNDecimal(secondXwScore, 7));

    }

    /**
     * 事业行为、担当特征 修正
     *
     * @param raterbyId
     * @param createDate
     * @return
     */
    public Map<String, Object> showDeparture(String raterbyId, Date createDate) {
        Map<String, Object> map = new HashMap<>();

        List<SummaryVo> xingweiList = evalDao.getSummaryVoList(raterbyId, createDate, "2");//cateId="2"，返回行为
        List<SummaryVo> dandangList = evalDao.getSummaryVoList(raterbyId, createDate, "5");//cateId="5"，返回担当

        Map<String, Double> xwMap = new LinkedHashMap<>();
        Map<String, Double> ddMap = new LinkedHashMap<>();
        Map<String, Double> xwSecondScore = new LinkedHashMap<>();
        Map<String, Double> ddSecondScore = new LinkedHashMap<>();

        crrentScoreByCate(xingweiList, xwMap, xwSecondScore);//修正偏离率计算
        crrentScoreByCate(dandangList, ddMap, ddSecondScore);//修正偏离率计算

        List<SummaryTotalVo> summaryTotalVoList = pjSummaryTotalDao.getSummaryTotalByRaterIdCreateDate(raterbyId, createDate);

        if (summaryTotalVoList.size() > 1) {
            map.put("code", "error");
            map.put("msg", "该评价者此月数据重复，请通过后台管理检查");
            return map;
        }
        SummaryTotalVo summaryTotalVo = null;
        if (summaryTotalVoList.size() == 1) {
            summaryTotalVo = summaryTotalVoList.get(0);
        }

        User user = UserUtils.getUser();
        map.put("userType",user.getUserType());

        map.put("code", "ok");
        map.put("xwRMap", xwMap);
        map.put("ddRMap", ddMap);
        map.put("wxSecondScore", xwSecondScore);
        map.put("ddSecondScore", ddSecondScore);
        map.put("summaryTotalVo", summaryTotalVo);



        return map;
    }


    /**
     * 获取价值评价汇总表
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<SummaryTotalVo> getSummaryTotalByCreateDate(Date beginDate, Date endDate) {
        return pjSummaryTotalDao.getSummaryTotalByCreateDate(beginDate, endDate);
    }


    /**
     * 根据偏离率计算惩罚金
     *
     * @param pll
     * @return
     */
    private double getCFJByPLL(double pll) {
        double cfj = 0.0;
        pll = Math.abs(pll);
        List<PjCommitmentMoney> commitmentMoneyList = pjCommitmentMoneyDao.findList(new PjCommitmentMoney());
        for (PjCommitmentMoney p : commitmentMoneyList) {
            if (p.getLeftNum() < pll && pll <= p.getRightNum()) {
                cfj = pll * p.getBase();
            }
        }
        return cfj;
    }

//    /**
//     * 担当金汇总
//     *
//     * @param beginDate
//     * @param endDate
//     * @return
//     */
//    public Map<String, Object> getMoneySummaryTable(Date beginDate, Date endDate) {
//        Map<String, Object> map = new HashMap<>();
//        List<SummaryVo> summaryRaterList = evalDao.getRaterMoneySummaryVoList(beginDate, endDate);//获取所有评价人
//        List<SummaryVo> summaryRaterbyList = evalDao.getRaterByMoneySummaryVoList(beginDate, endDate);//获取所有被评价人
//
//        Map<String, RaterByMoneyInfo> raterbyMap = new LinkedHashMap<>();
//
//        for (SummaryVo raterby : summaryRaterbyList) {  //数据计算
//            RaterByMoneyInfo raterByMoneyInfo = new RaterByMoneyInfo();     //一条被评价人的信息
//            raterbyMap.put(raterby.getRaterbyName(), raterByMoneyInfo);
//            Map<String, RaterMoneyInfo> raterMoneyInfoMap = raterByMoneyInfo.getRaterMoneyInfoMap();    //一个被评价人的所有评价人的偏离率，惩罚金等信息
//            List<SummaryVo> raterScoresList =
//                    evalDao.getRaterScoreMoneySummaryVoList(raterby.getRaterbyId(), beginDate, endDate);//该评价人的评价人和担当值
//            double coeffSum = 0.0, avgScores = 0.0;
//
//            for (SummaryVo summaryVo : raterScoresList) {   //计算系数和
//                coeffSum += summaryVo.getReterCoefficient();
//            }
//            coeffSum = coeffSum == 0.0 ? 1 : coeffSum;
//            for (SummaryVo summaryVo : raterScoresList) {   //计算权重均分
//                avgScores += summaryVo.getScores() * (summaryVo.getReterCoefficient() / coeffSum);
//            }
//
//            raterByMoneyInfo.setRaterbyId(raterby.getRaterbyId());
//            raterByMoneyInfo.setRaterbyName(raterby.getRaterbyName());
//            raterByMoneyInfo.setAvgScore(avgScores);
//            for (SummaryVo summaryVo : raterScoresList) {   //计算偏离率，惩罚金等
//                String raterName = summaryVo.getRaterName();
//                double pll = (summaryVo.getScores() - avgScores) / avgScores;
//                double cfj = getCFJByPLL(pll);
//                RaterMoneyInfo newRaterMoneyInfo = new RaterMoneyInfo();
//                newRaterMoneyInfo.setPll(pll);
//                newRaterMoneyInfo.setPunishMoney(cfj);
//                raterMoneyInfoMap.put(raterName, newRaterMoneyInfo);
//            }
//        }
//
//        Map<String, JSONObject> raterMoneyTotalMap = new LinkedHashMap<>();
//        for (SummaryVo summaryVo : summaryRaterList) {      //raterMoneyTotalMap初始化
//            JSONObject obj = new JSONObject();
//            obj.put("raterName", summaryVo.getRaterName());
//            obj.put("maxTimes", 0);
//            obj.put("cfjSum", 0.0);
//            obj.put("maxCfj", 0.0);
//            raterMoneyTotalMap.put(summaryVo.getRaterName(), obj);
//        }
//
//        for (SummaryVo summaryVo : summaryRaterbyList) {    //计算偏离率是否是最大偏离率
//            RaterByMoneyInfo raterByMoneyInfo = raterbyMap.get(summaryVo.getRaterbyName());
//            Map<String, RaterMoneyInfo> raterMoneyInfoMap = raterByMoneyInfo.getRaterMoneyInfoMap();
//            String raterName = "";
//            double maxPll = 0.0;
//            for (Map.Entry<String, RaterMoneyInfo> entry : raterMoneyInfoMap.entrySet()) {
//                if (entry.getValue().getPunishMoney() != 0.0 && maxPll < Math.abs(entry.getValue().getPll())) {
//                    maxPll = entry.getValue().getPll();
//                    raterName = entry.getKey();
//                }
//            }
//            if (!"".equals(raterName)) {
//                raterMoneyInfoMap.get(raterName).setMaxPl(true);
//            }
//        }
//
//        for (SummaryVo rater : summaryRaterList) {  //raterMoneyTotalMap压值，评价人最大偏离次数，惩罚金合计，最大惩罚金
//            String raterName = rater.getRaterName();
//            int maxTimes = 0;
//            double cfjSum = 0.0, maxCfj = 0.0;
//            for (SummaryVo raterby : summaryRaterbyList) {
//                RaterMoneyInfo raterMoneyInfo = raterbyMap.get(raterby.getRaterbyName()).getRaterMoneyInfoMap().get(raterName);
//                if (raterMoneyInfo == null) {
//                    continue;
//                }
//                double cfj = raterMoneyInfo.getPunishMoney();
//                if (raterMoneyInfo.isMaxPl()) {
//                    maxTimes++;
//                    maxCfj = Math.max(maxCfj, cfj);
//                }
//                cfjSum += cfj;
//            }
//            JSONObject obj = raterMoneyTotalMap.get(raterName);
//            obj.put("maxTimes", maxTimes);
//            obj.put("cfjSum", cfjSum);
//            obj.put("maxCfj", maxCfj == 0.0 ? "" : maxCfj);
//        }
//        map.put("raterMoneyTotalMap", raterMoneyTotalMap);
//        map.put("raterbyMap", raterbyMap);
//
//        return map;
//    }



    private void getMoneySummaryTableHadle(Date beginDate, Date endDate, Map<String, JSONObject> raterMoneyTotalMap,Map<String, RaterByMoneyInfo> raterbyMap,String cateId){
        List<SummaryVo> summaryRaterList = evalDao.getRaterMoneySummaryVoList(beginDate, endDate,cateId);//获取所有评价人
        List<SummaryVo> summaryRaterbyList = evalDao.getRaterByMoneySummaryVoList(beginDate, endDate,cateId);//获取所有被评价人


        for (SummaryVo raterby : summaryRaterbyList) {  //数据计算
            RaterByMoneyInfo raterByMoneyInfo = new RaterByMoneyInfo();     //一条被评价人的信息
            raterbyMap.put(raterby.getRaterbyName(), raterByMoneyInfo);
            Map<String, RaterMoneyInfo> raterMoneyInfoMap = raterByMoneyInfo.getRaterMoneyInfoMap();    //一个被评价人的所有评价人的偏离率，惩罚金等信息
            List<SummaryVo> raterScoresList =
                    evalDao.getRaterScoreMoneySummaryVoList(raterby.getRaterbyId(), beginDate, endDate,cateId);//该评价人的评价人和担当值
            double coeffSum = 0.0, avgScores = 0.0;

            for (SummaryVo summaryVo : raterScoresList) {   //计算系数和
                coeffSum += summaryVo.getReterCoefficient();
            }
            coeffSum = coeffSum == 0.0 ? 1 : coeffSum;
            for (SummaryVo summaryVo : raterScoresList) {   //计算权重均分
                avgScores += summaryVo.getScores() * (summaryVo.getReterCoefficient() / coeffSum);
            }

            raterByMoneyInfo.setRaterbyId(raterby.getRaterbyId());
            raterByMoneyInfo.setRaterbyName(raterby.getRaterbyName());
            raterByMoneyInfo.setAvgScore(avgScores);
            for (SummaryVo summaryVo : raterScoresList) {   //计算偏离率，惩罚金等
                String raterName = summaryVo.getRaterName();
                double pll = (summaryVo.getScores() - avgScores) / avgScores;
                double cfj = getCFJByPLL(pll);
                RaterMoneyInfo newRaterMoneyInfo = new RaterMoneyInfo();
                newRaterMoneyInfo.setPll(pll);
                newRaterMoneyInfo.setPunishMoney(cfj);
                raterMoneyInfoMap.put(raterName, newRaterMoneyInfo);
            }
        }


        for (SummaryVo summaryVo : summaryRaterList) {      //raterMoneyTotalMap初始化
            JSONObject obj = new JSONObject();
            obj.put("raterName", summaryVo.getRaterName());
            obj.put("maxTimes", 0);
            obj.put("cfjSum", 0.0);
            obj.put("maxCfj", 0.0);
            raterMoneyTotalMap.put(summaryVo.getRaterName(), obj);
        }

        for (SummaryVo summaryVo : summaryRaterbyList) {    //计算偏离率是否是最大偏离率
            RaterByMoneyInfo raterByMoneyInfo = raterbyMap.get(summaryVo.getRaterbyName());
            Map<String, RaterMoneyInfo> raterMoneyInfoMap = raterByMoneyInfo.getRaterMoneyInfoMap();
            String raterName = "";
            double maxPll = 0.0;
            for (Map.Entry<String, RaterMoneyInfo> entry : raterMoneyInfoMap.entrySet()) {
                if (entry.getValue().getPunishMoney() != 0.0 && maxPll < Math.abs(entry.getValue().getPll())) {
                    maxPll = entry.getValue().getPll();
                    raterName = entry.getKey();
                }
            }
            if (!"".equals(raterName)) {
                raterMoneyInfoMap.get(raterName).setMaxPl(true);
            }
        }

        for (SummaryVo rater : summaryRaterList) {  //raterMoneyTotalMap压值，评价人最大偏离次数，惩罚金合计，最大惩罚金
            String raterName = rater.getRaterName();
            int maxTimes = 0;
            double cfjSum = 0.0, maxCfj = 0.0;
            for (SummaryVo raterby : summaryRaterbyList) {
                RaterMoneyInfo raterMoneyInfo = raterbyMap.get(raterby.getRaterbyName()).getRaterMoneyInfoMap().get(raterName);
                if (raterMoneyInfo == null) {
                    continue;
                }
                double cfj = raterMoneyInfo.getPunishMoney();
                if (raterMoneyInfo.isMaxPl()) {
                    maxTimes++;
                    maxCfj = Math.max(maxCfj, cfj);
                }
                cfjSum += cfj;
            }
            JSONObject obj = raterMoneyTotalMap.get(raterName);
            obj.put("maxTimes", maxTimes);
            obj.put("cfjSum", cfjSum);
            obj.put("maxCfj", maxCfj == 0.0 ? "" : maxCfj);
        }
    }

    /**
     * 担当金汇总
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public Map<String, Object> getMoneySummaryTable(Date beginDate, Date endDate) {
        Map<String, Object> map = new HashMap<>();
        Map<String, JSONObject> raterMoneyTotalMap2 = new LinkedHashMap<>();
        Map<String, RaterByMoneyInfo> raterbyMap2 = new LinkedHashMap<>();
        getMoneySummaryTableHadle(beginDate, endDate, raterMoneyTotalMap2, raterbyMap2, "2");


        Map<String, JSONObject> raterMoneyTotalMap5 = new LinkedHashMap<>();
        Map<String, RaterByMoneyInfo> raterbyMap5 = new LinkedHashMap<>();
        getMoneySummaryTableHadle(beginDate, endDate, raterMoneyTotalMap5, raterbyMap5, "5");

        map.put("raterMoneyTotalMap2", raterMoneyTotalMap2);
        map.put("raterbyMap2", raterbyMap2);

        map.put("raterMoneyTotalMap5", raterMoneyTotalMap5);
        map.put("raterbyMap5", raterbyMap5);

        return map;
    }
}
