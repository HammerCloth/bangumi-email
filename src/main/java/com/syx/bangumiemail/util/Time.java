package com.syx.bangumiemail.util;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * @ClassName Time
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/18 23:03
 * @Version v0.1
 **/
@Component
public class Time {

    /**
     * 根据传入的循环时间格式判断是不是今天播出
     * @param broadcast 循环时间字符串
     * @return true是在今天播出，false不是在今天播出
     */
    public boolean isBroadcastToday(String broadcast) {
        String unit = broadcast.substring(29);//获取循环时间单位
        String period = broadcast.substring(28, 29);//循环时间间隔，可能为0
        String startTime = broadcast.substring(2, 26);//循环开始时间
        return isBroadcastToday(startTime, period, unit);
    }

    /**
     * 根据传入的各项参数来判断是不是今天播出
     * @param startTime 循环开始时间
     * @param period 循环时间间隔，可能为0
     * @param unit 循环时间单位
     * @return true是在今天播出，false不是在今天播出
     */
    public boolean isBroadcastToday(String startTime, String period, String unit) {
        Integer times = Integer.valueOf(period);
        MutableDateTime now = new MutableDateTime();//获取当前时间
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        MutableDateTime start = fmt.parseMutableDateTime(startTime);
        now.setTime(start);//将时间设置为和开始时间一样的时间，但是日期不变，方便比较
        //特例0天
        if (times==0) return start.isEqual(now);
        while(now.isAfter(start)){
            if (unit.equals("D")){
                start.addDays(times);
            }else if(unit.equals("M")){
                start.addMonths(times);
            }
            if (start.isEqual(now)) return true;
        }
        return false;
    }
}
