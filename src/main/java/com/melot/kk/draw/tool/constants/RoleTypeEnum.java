package com.melot.kk.draw.tool.constants;

/**
 * RewardTypeEnum.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.constants-RewardTypeEnum
 * date: 2022-3-6 20:07
 * version: 1.0.0
 */
public enum RoleTypeEnum {
    /**
     * 普通用户角色
     */
    USER(1, "|USER|"),

    /**
     * 主播角色
     */
    TALENT(2, "|TALENT|");

    private final Integer type;

    private final String desc;

    RoleTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "RoleTypeEnum{" +
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
