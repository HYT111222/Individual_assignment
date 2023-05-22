package com.service.impl;

import java.util.Calendar;

/**
 * @Auther HYT
 * @Date 2023/5/22
 * @Desc
 */
public class global {
    public static String time(){
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH);
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR_OF_DAY);
        int mi=cal.get(Calendar.MINUTE);
        int s=cal.get(Calendar.SECOND);
        System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒");
        String time =y+"-"+m+"-"+d+"--"+h+":"+mi+":"+s;
        return time;
    }
}
