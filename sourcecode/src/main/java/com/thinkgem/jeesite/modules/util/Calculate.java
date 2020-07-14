package com.thinkgem.jeesite.modules.util;

import com.thinkgem.jeesite.modules.pj.entity.money.PjCommitmentMoney;
import com.thinkgem.jeesite.modules.pj.service.money.PjCommitmentMoneyService;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

/**
 * 数据分析工具类
 */
public class Calculate {
    /**
     * 权重占比
     * @param user
     * @param userList 评价人集合
     * @return
     */
    public Double Weightings (User user, List<User> userList){
        Double coefficientSum=null;
        for (User user1:userList) {
            coefficientSum+= user1.getCoefficient();
        }
        Double weightings=user.getCoefficient()/coefficientSum;
        return weightings;
    }

    /**
     * 担当金
     * @param deviationValue 最大偏离值
     * @return
     */
    public Double money(Double deviationValue){
        Double money=null;
        PjCommitmentMoneyService pjCommitmentMoneyService=new PjCommitmentMoneyService();
        PjCommitmentMoney pjCommitmentMoney=new PjCommitmentMoney();
        List<PjCommitmentMoney> mList=pjCommitmentMoneyService.findList(pjCommitmentMoney);
        for (PjCommitmentMoney pjCommitmentMoney1:mList) {
           Double leftnum= pjCommitmentMoney1.getLeftNum();
           Double rightnum= pjCommitmentMoney1.getRightNum();
           if (deviationValue>=leftnum && (deviationValue<=rightnum || rightnum==null ) ){
               money= pjCommitmentMoney1.getBase()*deviationValue;
               return money;
           }
        }
        return money;
    }


    /**
     * 偏离值
     * @param realScore 打分值
     * @param weightScore 权重均分
     * @return
     */
    public double deviationValue(double realScore ,double weightScore){
        return (realScore-weightScore)/weightScore;
    }

    /**
     * 个人权重分值
     * @param realScore
     * @param Weightings
     * @return
     */
    public double weightScore(double realScore,double Weightings){
        return realScore*Weightings;
    }
    //比率
    public double ratio(double realScore,double sumScore){
        return realScore/sumScore;
    }
    //担当贡献率
    public double contributionRate(double ratio){
        return ratio*ratio;
    }

    /**
     * 第二次修正
     * @param weightScore 权重均值
     * @param deviationList 偏离率集合
     * @return
     */
    public double secondCorrection(double weightScore,List<Double> deviationList){
        double max=deviationList.get(0);
        double min=deviationList.get(0);
        double zSum=0;
        double fSum=0;
        int zCount=0;
        int fCount=0;
        int size=deviationList.size();
        for (Double d:deviationList) {
            max = (max<d)?d:max;
            min = (min>d)?d:min;
            if (d>0){
                zSum+=d;
                zCount++;
            }
            if (d<0){
                fSum+=d;
                fCount++;
            }
        }

        double zCorrection=(max+fSum/fCount)/size*weightScore;
        double fCorrection=(min+zSum/zCount)/size*weightScore;
        return weightScore-zCorrection-fCorrection;
    }
//    //总经理修正
//    public double endCorrection(Double secondCorrectScore,Double rate){
//        rate = rate>1.2?1.2:rate;
//        rate = rate<0.8?0.8:rate;
//        return secondCorrectScore*rate;
//    }
    //获取时间的年月
}
