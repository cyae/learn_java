package com.learn.springboot.autoconfigure_demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.learn.springboot")
public class CommonProperties {
    
    private String prefix = "common_prefix";
    private String suffix = "common_suffix";
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    
}
