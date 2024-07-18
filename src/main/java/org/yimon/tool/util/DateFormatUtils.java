package org.yimon.tool.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: ym.gao
 * @description: 日期转换工具类
 * @date: 2024/4/28 19:22
 */
public final class DateFormatUtils {

    private DateFormatUtils() {
    }

    /**
     * date转string
     *
     * @param date    日期
     * @param pattern 格式
     * @return String
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * calendar转string
     *
     * @param calendar 日期
     * @param pattern  格式
     * @return String
     */
    public static String format(Calendar calendar, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(calendar);
    }

    /**
     * timestamp转string
     *
     * @param timestamp 日期
     * @param pattern   格式
     * @return String
     */
    public static String format(Timestamp timestamp, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(timestamp);
    }

    /**
     * string转date
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * date转Calendar
     *
     * @param date 日期
     * @return Calendar
     */
    public static Calendar date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 将 Date 对象设置到 Calendar 中
        calendar.setTime(date);
        return calendar;
    }

    /**
     * date转Timestamp
     *
     * @param date 日期
     * @return Timestamp
     */
    public static Timestamp date2Timestamp(Date date) {
        return new Timestamp(date.getTime());
    }
}
