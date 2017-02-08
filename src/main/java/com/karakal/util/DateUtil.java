package com.karakal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String MONTH_FORMAT = "yyyy-MM";
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String NUMBER_DATETIME_FORMAT = "yyyyMMddHHmmssSSSSSS";
    
    public static final long MONTH_LONG = 2651224907L;
    
    public static String date2String(Date date, String format) {
        if ((date == null) || (format == null))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
    
    public static Date string2Date(String date, String format) {
        if ((date == null) || (format == null))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            LogUtil.LOGEXCEPTION(e);
            return null;
        }
    }
    
    public static Date adjustDate(Date date, int days, int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }
}
