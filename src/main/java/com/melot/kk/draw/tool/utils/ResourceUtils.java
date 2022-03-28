package com.melot.kk.draw.tool.utils;

import cn.hutool.core.util.RandomUtil;
import com.melot.kk.draw.tool.constants.RewardIdEnum;
import com.melot.kk.draw.tool.constants.RewardTypeEnumV2;

import java.util.Arrays;
import java.util.List;

/**
 * ResourceUtils.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.constants-ResourceUtils
 * date: 2022-3-28 11:15
 * version: 1.0.0
 */
public class ResourceUtils {

    private static final List<Integer> ENTRANCE_LIST = Arrays.asList(1594,1593,1592,1591,1590,1589,1588,1587,1586,1585,1584,1583);
    private static final List<Integer> AVATAR_LIST = Arrays.asList(32,31,30,29,28,27,16,15,14,13,12,11,10,9,8,1);
    private static final List<Integer> THEME_LIST = Arrays.asList(31,30,29,28,27,26,25,24,23,22,17,16,15,14,13,12,11,10,9,8);
    private static final List<Integer> GIFT_LIST = Arrays.asList(41002860, 41002842);
    private static final List<Integer> BADGE_LIST = Arrays.asList(10055,902,10047,10066,10048,10046,1087,900,9001,1088,1054,1070,1051,9000,1072,1061,1055,1069,1057,1056,1058,1066,1067,1068,9600,1052,9581,9002,9003,901,10020,10018,10009,1077,1078,10008);

    public static Integer getRandomIdForTest(RewardTypeEnumV2 type) {
        if (type.equals(RewardTypeEnumV2.GIFT)) {
            return GIFT_LIST.get(RandomUtil.randomInt(GIFT_LIST.size()));
        } else if (type.equals(RewardTypeEnumV2.AVATAR)) {
            return AVATAR_LIST.get(RandomUtil.randomInt(AVATAR_LIST.size()));
        } else if (type.equals(RewardTypeEnumV2.THEME)) {
            return THEME_LIST.get(RandomUtil.randomInt(THEME_LIST.size()));
        }else if (type.equals(RewardTypeEnumV2.ENTRANCE)) {
            return ENTRANCE_LIST.get(RandomUtil.randomInt(ENTRANCE_LIST.size()));
        }else if (type.equals(RewardTypeEnumV2.BADGE)) {
            return BADGE_LIST.get(RandomUtil.randomInt(BADGE_LIST.size()));
        }
        return null;
    }

    public static Integer getIdForNoIdResource(RewardTypeEnumV2 type) {
        if (type.equals(RewardTypeEnumV2.NO_REWARD)) {
            return 0;
        } else if (type.equals(RewardTypeEnumV2.BEAN)) {
            return RewardIdEnum.BEAN;
        } else if (type.equals(RewardTypeEnumV2.GEM)) {
            return RewardIdEnum.GEM;
        } else if (type.equals(RewardTypeEnumV2.SHOW_TAG)) {
            return RewardIdEnum.SHOW_TAG;
        } else if (type.equals(RewardTypeEnumV2.VIRTUAL_ITEM)) {
            return RewardIdEnum.VIRTUAL_ITEM;
        } else if (type.equals(RewardTypeEnumV2.VIP)) {
            return 100001;
        } else if (type.equals(RewardTypeEnumV2.S_VIP)) {
            return 100004;
        } else if (type.equals(RewardTypeEnumV2.FOLLOW_CARD)) {
            return RewardIdEnum.FOLLOW_CARD;
        }
        return null;
    }

    public static String getDescForShow(String name, RewardTypeEnumV2 enumV2, int count) {
        if (enumV2.equals(RewardTypeEnumV2.NO_REWARD)) {
            return name;
        }

        String countDesc = "day";
        if (enumV2.equals(RewardTypeEnumV2.VIRTUAL_ITEM) ||
                enumV2.equals(RewardTypeEnumV2.BEAN) ||
                enumV2.equals(RewardTypeEnumV2.GEM) ||
                enumV2.equals(RewardTypeEnumV2.GIFT)) {
        countDesc = "";
        }

        if (count > 1) {
            countDesc = countDesc + "s";
        }

        return name + " × " + countDesc;
    }
}
