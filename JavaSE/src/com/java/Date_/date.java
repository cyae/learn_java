package com.java.Date_;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class date {
    public static void main(String[] args) {
        // jdk1.0
        Date d = new Date();
        System.out.println("默认Date格式:" + d);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss E");
        String formated = sdf.format(d);

        System.out.println("sdf格式化后:" + formated);

        // 无格式化，线程不安全，月从0开始
        Calendar c = Calendar.getInstance();
        System.out.println(c);
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.MONTH) + 1); // 默认0开始

        // jdk8
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonth());
        System.out.println(ldt.getMonthValue());

        LocalDateTime ldt2 = ldt.plusDays(100);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss E");
        System.out.println(dtf.format(ldt));
        System.out.println("100 days later..." + dtf.format(ldt2));
    }
}
