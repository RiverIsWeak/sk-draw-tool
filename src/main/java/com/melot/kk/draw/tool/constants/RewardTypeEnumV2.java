package com.melot.kk.draw.tool.constants;

import java.io.Serializable;

/**
 * RewardTypeEnumV2.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.constants-RewardTypeEnumV2
 * date: 2022-3-10 10:54
 * version: 1.0.0
 */
public enum RewardTypeEnumV2 implements Serializable {

    /**
     * 无奖励
     */
    NO_REWARD(0, "no reward"),
    BEAN(1, "Beans"),
    GEM(2, "Gems"),
    GIFT(3, "Gift"),
    THEME(4, "Theme"),
    AVATAR(5, "Profile Frame"),
    VIP(6, "VIP"),
    S_VIP(7, "SVIP"),
    ENTRANCE(8, "Entrance"),
    BADGE(9, "Badge"),
    SHOW_TAG(10, "Show Tag"),
    FOLLOW_CARD(11, "Follow Card"),
    VIRTUAL_ITEM(12, "Virtual Item");


    RewardTypeEnumV2(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private final Integer type;

    private final String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getIdForNoIdResource(boolean testSwitch) {
        if (type.equals(RewardTypeEnumV2.NO_REWARD.getType())) {
            return 0;
        } else if (type.equals(RewardTypeEnumV2.BEAN.getType())) {
            return RewardIdEnum.BEAN;
        } else if (type.equals(RewardTypeEnumV2.GEM.getType())) {
            return RewardIdEnum.GEM;
        } else if (type.equals(RewardTypeEnumV2.SHOW_TAG.getType())) {
            return RewardIdEnum.SHOW_TAG;
        } else if (type.equals(RewardTypeEnumV2.VIRTUAL_ITEM.getType())) {
            return RewardIdEnum.VIRTUAL_ITEM;
        } else if (type.equals(RewardTypeEnumV2.VIP.getType())) {
            return 100001;
        } else if (type.equals(RewardTypeEnumV2.S_VIP.getType())) {
            return 100004;
        }
        return null;
    }
}
