package com.thinkgem.jeesite.modules.pj.vo.eval;

public class RaterMoneyInfo {

    private double pll; //偏离率
    private double punishMoney;   //惩罚金额
    private boolean isMaxPl;


    public double getPll() {
        return pll;
    }

    public void setPll(double pll) {
        this.pll = pll;
    }

    public double getPunishMoney() {
        return punishMoney;
    }

    public void setPunishMoney(double punishMoney) {
        this.punishMoney = punishMoney;
    }

    public boolean isMaxPl() {
        return isMaxPl;
    }

    public void setMaxPl(boolean maxPl) {
        isMaxPl = maxPl;
    }
}