package com.Design_Pattern.design_pattern_java8.builder;

import java.util.function.Consumer;

public class Mail {
    
    public static void print(String msg) {
        System.out.println(msg);
    }

    public Mail from(String from) {
        print("from: " + from);
        return this;
    }

    public Mail to(String to) {
        print("to: " + to);
        return this;
    }

    public Mail subject(String subject) {
        print("subject: " + subject);
        return this;
    }

    public Mail body(String body) {
        print("body: " + body);
        return this;
    }

    public static void send(Consumer<Mail> consumer) {
        Mail mail = new Mail();
        consumer.accept(mail);
        print("sending...");
    }

}
