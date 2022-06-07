package com.melot.kk.draw.tool.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DrawGameShowDTO.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.domain-DrawGameShowDTO
 * date: 2022-4-2 15:39
 * version: 1.0.0
 */
public class DrawGameShowDTO implements Serializable {
    private static final long serialVersionUID = 3069953137997351174L;

    private Integer firstLevel;

    private List<Integer> levelList;

    private List<RewardDTO> showList;

    private Map<String, Object> extraInfo;

    @Override
    public String toString() {
        return "DrawGameShowDTO{" +
                "firstLevel=" + firstLevel +
                ", levelList=" + levelList +
                ", showList=" + showList +
                ", extraInfo=" + extraInfo +
                '}';
    }

    public Integer getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(Integer firstLevel) {
        this.firstLevel = firstLevel;
    }

    public List<Integer> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Integer> levelList) {
        this.levelList = levelList;
    }

    public List<RewardDTO> getShowList() {
        showList.forEach(show -> show.setRewardType(null));
        return showList;
    }

    public void setShowList(List<RewardDTO> showList) {
        showList.forEach(show -> show.setRewardType(null));
        this.showList = showList;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }
}
