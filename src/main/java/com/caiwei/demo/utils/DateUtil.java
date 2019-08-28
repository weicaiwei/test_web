package com.caiwei.demo.utils;

import org.aspectj.lang.annotation.AfterReturning;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: DateUtil
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/6/24 23:49
 */
public class DateUtil {

    public static Date getAppointedDateTimeByNow(TimeUnit timeUnit, Integer several){
        Calendar calendar = Calendar.getInstance();
        switch (timeUnit) {
            case SECONDS:
                calendar.add(Calendar.SECOND, several);
                return calendar.getTime();
            case MINUTES:
                calendar.add(Calendar.MINUTE, several);
                return calendar.getTime();
            case HOURS:
                calendar.add(Calendar.HOUR_OF_DAY, several);
                return calendar.getTime();
            case DAYS:
                calendar.add(Calendar.DAY_OF_MONTH, several);
                return calendar.getTime();
            default:
                return calendar.getTime();
        }
    }

    public static String getFormatNow(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getFormatDateTime(Date date, String pattern) {

        return new SimpleDateFormat(pattern).format(date);
    }
}
