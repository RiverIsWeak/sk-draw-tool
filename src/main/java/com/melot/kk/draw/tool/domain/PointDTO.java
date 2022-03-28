package com.melot.kk.draw.tool.domain;

import java.io.Serializable;
import java.util.List;

/**
 * PointDTO.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.domain-PointDTO
 * date: 2022-3-28 16:13
 * version: 1.0.0
 */
public class PointDTO implements Serializable {
    private static final long serialVersionUID = -7972921735622349533L;

    private Integer index;

    private Integer point;

    private List<RewardDTO> rewardList;

    @Override
    public String toString() {
        return "PointDTO{" +
                "index=" + index +
                ", point=" + point +
                ", rewardList=" + rewardList +
                '}';
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public List<RewardDTO> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<RewardDTO> rewardList) {
        this.rewardList = rewardList;
    }
}
