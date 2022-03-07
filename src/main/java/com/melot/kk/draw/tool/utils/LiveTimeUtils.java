package com.melot.kk.draw.tool.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LiveTimeUtils.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.utils-LiveTimeUtils
 * date: 2022-3-7 15:30
 * version: 1.0.0
 */
public class LiveTimeUtils {
    /**
     * 获取起止时间每天的分钟数（用于活动直播时间统计）
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 每天的分钟数map
     */
    public static Map<Date, Long> getLiveMinutesMap(Date startTime, Date endTime) {
        Map<Date, Long> result = new LinkedHashMap<>();
        //判断参数若存在null直接返回空map
        if (startTime == null || endTime == null) {
            return result;
        }
        long betweenDay = DateUtil.betweenDay(startTime, endTime, true);
        //重置点位时间相差天数 >= 1 将中间天数计入 首尾时间另算
        if (betweenDay >= 1) {

            Calendar calendar = Calendar.getInstance();
            DateTime startTimeDt = DateUtil.beginOfDay(startTime);
            calendar.setTime(startTimeDt);
            DateTime endTimeDt = DateUtil.beginOfDay(endTime);

            for (int i = 0; i < betweenDay + 1; i++) {
                if (i == 0) {
                    //头部时间插入
                    long minutes = 24 * 60 - DateUtil.between(startTime, startTimeDt, DateUnit.MINUTE);
                    result.put(startTimeDt.toJdkDate(), minutes);
                } else if (i < betweenDay) {
                    //中部时间插入
                    result.put(calendar.getTime(), 24 * 60L);
                } else {
                    //尾部时间插入
                    long minutes = DateUtil.between(endTime, endTimeDt, DateUnit.MINUTE);
                    result.put(endTimeDt.toJdkDate(), minutes);
                }

                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
        } else {
            //当日直播记录统计
            long betweenMinutes = DateUtil.between(startTime, endTime, DateUnit.MINUTE);
            result.put(startTime, betweenMinutes);
        }
        return result;
    }
}
