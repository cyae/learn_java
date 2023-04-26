package com.learnjava.spring.demo_jdbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {
    
    private static final Logger log = LoggerFactory.getLogger(MyLogger.class);

    public static void main(String[] args) {
        log.info("test1");
        log.warn("test1");
    }
}
