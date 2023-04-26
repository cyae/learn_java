package com.learn.springboot.autoconfigure_demo.service;

import com.learn.springboot.autoconfigure_demo.bean.CommonProperties;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * CommonService
 */
public class CommonService {

    @Autowired
    CommonProperties commonProperties;

    public String commonMethod(String arg) {
        return commonProperties.getPrefix() + ":" + arg + ", " + commonProperties.getSuffix();
    }
}