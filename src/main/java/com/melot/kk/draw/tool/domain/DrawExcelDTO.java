package com.melot.kk.draw.tool.domain;

import java.io.Serializable;

/**
 * DrawExcelDTO.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.domain-DrawExcelDTO
 * date: 2022-3-9 16:50
 * version: 1.0.0
 */
public class DrawExcelDTO implements Serializable {
    private static final long serialVersionUID = 8651382775464824825L;

    private Integer needPoint;

    private Integer drawIndex;

    private Integer rewardLevel;

    private Double odds;

    private String reward;

    @Override
    public String toString() {
        return "DrawExcelDTO{" +
                "needPoint=" + needPoint +
                ", drawIndex=" + drawIndex +
                ", rewardLevel=" + rewardLevel +
                ", odds=" + odds +
                ", reward='" + reward + '\'' +
                '}';
    }

    public Integer getRewardLevel() {
        return rewardLevel;
    }

    public void setRewardLevel(Integer rewardLevel) {
        this.rewardLevel = rewardLevel;
    }

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public Integer getNeedPoint() {
        return needPoint;
    }

    public void setNeedPoint(Integer needPoint) {
        this.needPoint = needPoint;
    }

    public Integer getDrawIndex() {
        return drawIndex;
    }

    public void setDrawIndex(Integer drawIndex) {
        this.drawIndex = drawIndex;
    }
}
