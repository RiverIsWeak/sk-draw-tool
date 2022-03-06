package com.melot.kk.draw.tool.domain;

import java.io.Serializable;
import java.util.List;

/**
 * DrawLevelDTO.
 * 抽奖实体
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.domain-DrawLevelDTO
 * date: 2022-3-6 19:40
 * version: 1.0.0
 */
public class DrawLevelDTO implements Serializable {
    private static final long serialVersionUID = -9105186493456278750L;

    /**
     * 奖励等级
     */
    private Integer level;

    /**
     * 抽中概率
     */
    private Double odds;

    /**
     * 奖励列表
     */
    private List<RewardDTO> rewardList;

    @Override
    public String toString() {
        return "DrawLevelDTO{" +
                "level=" + level +
                ", odds=" + odds +
                ", rewardList=" + rewardList +
                '}';
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }

    public List<RewardDTO> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<RewardDTO> rewardList) {
        this.rewardList = rewardList;
    }
}
