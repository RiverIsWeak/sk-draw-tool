package com.melot.kk.draw.tool.domain;

import java.io.Serializable;
import java.util.List;

/**
 * DrawResultDTO.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.domain-DrawResultDTO
 * date: 2022-3-7 17:47
 * version: 1.0.0
 */
public class DrawResultDTO implements Serializable {
    private static final long serialVersionUID = -6972275384364073359L;

    /**
     * 全结果列表
     */
    private List<DrawLevelDTO> allResultList;

    /**
     * 用于发奖list
     */
    private List<RewardDTO> rewardList;

    /**
     * 奖励描述
     */
    private String rewardContent;

    /**
     * 用户中奖第一等级
     */
    private Integer firstLevel;

    public List<DrawLevelDTO> getAllResultList() {
        return allResultList;
    }

    public void setAllResultList(List<DrawLevelDTO> allResultList) {
        this.allResultList = allResultList;
    }

    public List<RewardDTO> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<RewardDTO> rewardList) {
        this.rewardList = rewardList;
    }

    public String getRewardContent() {
        return rewardContent;
    }

    public void setRewardContent(String rewardContent) {
        this.rewardContent = rewardContent;
    }

    public Integer getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(Integer firstLevel) {
        this.firstLevel = firstLevel;
    }

    @Override
    public String toString() {
        return "DrawResultDTO{" +
                ", rewardList=" + rewardList +
                ", rewardContent='" + rewardContent + '\'' +
                ", firstLevel=" + firstLevel +
                ", allResultList=" + allResultList +
                '}';
    }
}
