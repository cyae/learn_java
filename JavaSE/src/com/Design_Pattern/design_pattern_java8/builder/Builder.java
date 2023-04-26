package com.Design_Pattern.design_pattern_java8.builder;

public class Builder {
    
    public static void main(String[] args) {
        Mail.send(mail -> 
            mail.from("xxx@qq.com")
                .to("yyy@qq.com")
                .subject("greetings")
                .body("hello!"));
    }
}
