package com.example.publicspring.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.lang.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String MM = "MM";
    public static final String FORMAT6 = "yyMMdd";
    public static final String FORMAT8 = "yyyyMMdd";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMM2 = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";
    public static final String FORMAT10 = "yyyy-MM-dd";
    public static final String FORMAT20 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT20_2 = "yyyy/MM/dd HH:mm:ss";
    public static final String FORMAT30 = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String FORMAT_YMD_CN = "yyyy年MM月dd日";
    public static final String FORMAT_YM_CN = "yyyy年MM月";
    public static final String FORMAT_Y_CN = "yyyy年";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYY = "yyyy";
    public static final String HHMM = "HHmm";
    public static final String HH = "HH";
    public static final String HHMM2 = "HH:mm";
    public static final String TIME_STAMP_OF_TODAY = "HHmmssSSS";

    public static String formatDateTime(Date date, String format) {
        return cn.hutool.core.date.DateUtil.format(date, format);
    }

    public static String getNowFormatTime(String format) {
        return cn.hutool.core.date.DateUtil.format(new Date(), format);
    }

    public static String getToday8() {
        return cn.hutool.core.date.DateUtil.format(new Date(), FORMAT8);
    }

    public static String getToday() {
        return cn.hutool.core.date.DateUtil.format(new Date(), FORMAT10);
    }

    /**
     * @return FORMAT20
     */
    public static String getNow() {
        return cn.hutool.core.date.DateUtil.format(new Date(), FORMAT20);
    }

    /**
     * @return now YYYYMMDDHHMMSS
     */
    public static String getTimestamp() {
        return cn.hutool.core.date.DateUtil.format(new Date(), YYYYMMDDHHMMSS);
    }

    public static String getTimeStamp2() {
        return formatDateTime(new Date(), "YYMMDDHHMMSS");
    }

    public static String getTimeStamp3() {
        return cn.hutool.core.date.DateUtil.format(new Date(), YYMMDDHHMMSSSSS);
    }

    /**
     * yyyyMMddHHmmssSSS
     */
    public static String getFullTimeStamp() {
        return formatDateTime(new Date(), "yyyyMMddHHmmssSSS");
    }

    /**
     * HHmmssSSS
     */
    public static String getTimeStampOfDay() {
        return formatDateTime(new Date(), "HHmmssSSS");
    }

    public static Date getDateBy10(String date) {
        cn.hutool.core.lang.Assert.notNull(date, "Can not be empty");
        Assert.isTrue(date.length() == 10, "Date length must equals 10,date=[{}]", date);
        return cn.hutool.core.date.DateUtil.parseDate(date).toJdkDate();
    }

    public static Date getDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * offset – 偏移秒数，正数向未来偏移，负数向历史偏移
     *
     * @param seconds -2 表示现在向历史时间倒退 2 秒 ，
     * @return "yyyy-MM-dd HH:mm:ss";
     */
    public static String getOffsetTime(int seconds) {
        return cn.hutool.core.date.DateUtil.offsetSecond(new Date(), seconds).toString(FORMAT20);
    }


    /**
     * format20Date 相对时间
     * offset – 偏移秒数，正数向未来偏移，负数向历史偏移
     *
     * @param seconds
     * @return
     */
    public static String getOffsetTime(String format20Date, int seconds) {
        return cn.hutool.core.date.DateUtil.offsetSecond(parseDateTime(format20Date, FORMAT20), seconds).toString(FORMAT20);
    }

    public static String convertDate(String date, String inPattern, String outPattern) {
        try {
            Assert.notNull(date, "date cannot be empty");
            Assert.notNull(inPattern, "inPattern cannot be empty");
            Assert.notNull(outPattern, "outPattern cannot be empty");
            return new DateTime(new SimpleDateFormat(inPattern).parse(date)).toString(outPattern);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNextOffsetDay(String date, int addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDateTime(date, FORMAT10));
        calendar.add(Calendar.DAY_OF_MONTH, addDay);
        return new DateTime(calendar.getTime()).toString(FORMAT10);
    }


    public static String getNextOffsetDay(String date, int addDay, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDateTime(date, format));
        calendar.add(Calendar.DAY_OF_MONTH, addDay);
        return new DateTime(calendar.getTime()).toString(format);

    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    public static Date parseDateTime(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param time      GMT 1970 距离当前时间，  例：1635836418686
     * @param formatStr 格式 ， 例： yyyy-MM-dd HH:mm:ss
     * @return 2021-11-02 15:00:18
     */
    public static String formatGMT1970ToDateStr(long time, String formatStr) {
        return new SimpleDateFormat(formatStr).format(time);
    }

    /**
     * 返回当天日期的yyyyMMdd hh:mm:ss SSS表示
     *
     * @return
     */
    public static String getNowTimeMillisecond() {
        return new DateTime().toString(FORMAT30);
    }

    public static String getYear() {
        return new DateTime().toString(YYYY);
    }

    /**
     * 返回两个日期差值
     *
     * @return
     */
    public static int getOffsetDays(String lDate, String uDate) {
        long upperTime, lowerTime;
        try {
            upperTime = new DateTime(new SimpleDateFormat(FORMAT10).parse(uDate)).getTime();
            lowerTime = new DateTime(new SimpleDateFormat(FORMAT10).parse(lDate)).getTime();
            long result = (upperTime - lowerTime) / (1000 * 60 * 60 * 24);
            return (int) result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回两个日期之间的差多少秒
     *
     * @return
     */
    public static long getDiffSeconds(String startDate, String endDate) {
        try {
            Date upperTime = new DateTime(new SimpleDateFormat(FORMAT20).parse(endDate));
            Date lowerTime = new DateTime(new SimpleDateFormat(FORMAT20).parse(startDate));
            return cn.hutool.core.date.DateUtil.between(upperTime, lowerTime, DateUnit.SECOND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回上月的最后一天
     *
     * @return
     */
    public static String getLastMonthDay() {
        return getOffsetDay(-Integer.parseInt(getToday8().substring(6)));
    }


    /**
     * 返回当前时间与指定时间的时间间隔
     */
    public static long getOffsetInMillisecond(String format20Date) {
        return System.currentTimeMillis() - DateUtil.parseDateTime(format20Date, DateUtil.FORMAT20).getTime();
    }


    public static String getOffsetDay(int i) {
        return getNextOffsetDay(getToday(), i);
    }

    /**
     * 返回前n年的日期字符串FORMAT10
     *
     * @return
     */
    public static String getOffsetYear10(String dataDate10, int years) {
        DateTime dateTime = new DateTime(dataDate10);
        dateTime.setField(DateField.YEAR, dateTime.getField(DateField.YEAR) - years);
        return dateTime.toString(FORMAT10);
    }

}
