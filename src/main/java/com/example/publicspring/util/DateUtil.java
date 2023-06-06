package com.example.publicspring.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import org.springframework.util.Assert;

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


}
