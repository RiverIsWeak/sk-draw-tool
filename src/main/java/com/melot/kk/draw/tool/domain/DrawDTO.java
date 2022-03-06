package com.melot.kk.draw.tool.domain;

import java.io.Serializable;
import java.util.List;

/**
 * DrawDTO.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.domain-DrawDTO
 * date: 2022-3-6 19:48
 * version: 1.0.0
 */
public class DrawDTO implements Serializable {
    private static final long serialVersionUID = -9105186493456278750L;

    /**
     * 奖励等级配置list
     */
    private List<DrawLevelDTO> drawLevelList;

    /**
     * 抽奖index
     */
    private Integer drawIndex;

    /**
     * 所需积分
     */
    private Integer needPoint;

    @Override
    public String toString() {
        return "DrawDTO{" +
                "drawLevelList=" + drawLevelList +
                ", drawIndex=" + drawIndex +
                ", needPoint=" + needPoint +
                '}';
    }

    public List<DrawLevelDTO> getDrawLevelList() {
        return drawLevelList;
    }

    public void setDrawLevelList(List<DrawLevelDTO> drawLevelList) {
        this.drawLevelList = drawLevelList;
    }

    public Integer getDrawIndex() {
        return drawIndex;
    }

    public void setDrawIndex(Integer drawIndex) {
        this.drawIndex = drawIndex;
    }

    public Integer getNeedPoint() {
        return needPoint;
    }

    public void setNeedPoint(Integer needPoint) {
        this.needPoint = needPoint;
    }
}
