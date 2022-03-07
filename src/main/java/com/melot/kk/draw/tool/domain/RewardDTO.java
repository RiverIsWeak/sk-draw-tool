package com.melot.kk.draw.tool.domain;

import java.io.Serializable;

/**
 * RewardDTO.
 * 用户奖励实体
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.domain-RewardDTO
 * date: 2022-3-6 19:27
 * version: 1.0.0
 */
public class RewardDTO implements Serializable {

    private static final long serialVersionUID = 8573214263287921368L;

    /**
     * 奖励对应资源ID
     */
    private Integer rewardId;

    /**
     * 奖励数量
     */
    private Integer rewardCount;

    /**
     * 奖励类型
     * @see com.melot.kk.draw.tool.constants.RewardTypeEnum
     */
    private Integer rewardType;

    /**
     * 奖励描述
     */
    private String desc;

    /**
     * 拓展属性
     */
    private String exValue;

    @Override
    public String toString() {
        return "RewardDTO{" +
                "rewardId=" + rewardId +
                ", rewardCount=" + rewardCount +
                ", rewardType=" + rewardType +
                ", desc=" + desc +
                ", exValue='" + exValue + '\'' +
                '}';
    }

    public String getExValue() {
        return exValue;
    }

    public void setExValue(String exValue) {
        this.exValue = exValue;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getRewardCount() {
        return rewardCount;
    }

    public void setRewardCount(Integer rewardCount) {
        this.rewardCount = rewardCount;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
