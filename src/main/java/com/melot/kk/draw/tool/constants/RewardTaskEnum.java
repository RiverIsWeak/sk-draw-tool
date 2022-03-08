package com.melot.kk.draw.tool.constants;

/**
 * BasePotTypeEnum.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.activity.baseUtils.constants-BasePotTypeEnum
 * date: 2022-3-3 15:41
 * version: 1.0.0
 */
public enum RewardTaskEnum {
    /**
     * 小时任务
     */
    HOURLY(1, "|hourly task|"),

    /**
     * 日任务
     */
    DAILY(2, "|daily task|"),

    /**
     * 周任务
     */
    WEEKLY(3, "|weekly task|");

    RewardTaskEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private  final Integer type;
    private  final String desc;

    @Override
    public String toString() {
        return "RewardTaskEnum{" +
                "type=" + type +
                ", desc='" + desc + '\'' +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
