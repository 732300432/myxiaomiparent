package com.cp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * cp 2019-09-10  19:29
 */
public class RandomUtils {
    public static String getTime(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }
    public static String createActive(){
        return getTime()+Integer.toHexString(new Random().nextInt(900)+100);
    }
    //生成订单编号
    public static String createOrderId(){
        return getTime();
    }
}
