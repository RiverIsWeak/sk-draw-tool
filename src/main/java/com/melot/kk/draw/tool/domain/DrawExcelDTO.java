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

    private Integer point;

    private Integer index;

    private Integer level;

    private Double odds;

    private String rewardName;

    private String rewardType;

    private Integer count;

    private Integer id;

    @Override
    public String toString() {
        return "DrawExcelDTO{" +
                "point=" + point +
                ", index=" + index +
                ", level=" + level +
                ", odds=" + odds +
                ", rewardName='" + rewardName + '\'' +
                ", rewardType=" + rewardType +
                ", count=" + count +
                ", id=" + id +
                '}';
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
