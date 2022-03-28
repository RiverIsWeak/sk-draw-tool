package com.melot.kk.draw.tool.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;

import java.util.Map;

/**
 * EnumUtils.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.utils-EnumUtils
 * date: 2022-3-10 15:07
 * version: 1.0.0
 */
public class EnumUtils {

    /**
     * 获得枚举名对应指定字段值的Map<br>
     * 键为枚举名，值为字段值
     *
     * @param clazz     枚举类
     * @param fieldName 字段名，最终调用getXXX方法
     * @return 枚举名对应指定字段值的Map
     */
    public static Map<String, Object> getNameFieldMap(Class<? extends Enum<?>> clazz, String fieldName) {
        final Enum<?>[] enums = clazz.getEnumConstants();
        if (null == enums) {
            return null;
        }
        final Map<String, Object> map = MapUtil.newHashMap(enums.length, true);
        for (Enum<?> e : enums) {
            map.put((String) ReflectUtil.getFieldValue(e, fieldName), e);
        }
        return map;
    }
}
