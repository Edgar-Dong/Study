package com.java.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author:無忌
 * @date:2020/12/10
 * @description:
 */
public class TimeUtil {
    public static String getCurTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        return dtf.format(LocalDateTime.now());
    }
}
