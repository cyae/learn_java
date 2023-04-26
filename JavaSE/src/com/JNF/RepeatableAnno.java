package com.JNF;

import java.lang.annotation.Repeatable;

import com.java.Annotation.MyAnno;

// 如果需要多次使用同一注解修饰某元素
@Repeatable(MyAnno.class)
public @interface RepeatableAnno {
    String value();
}

@RepeatableAnno(value = "123")
@RepeatableAnno(value = "456")
class A {
}
