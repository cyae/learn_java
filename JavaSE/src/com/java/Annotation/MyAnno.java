package com.java.Annotation;

import com.JNF.RepeatableAnno;

// 在框架中, 使用反射读取注解类, 可以代替xml配置文件的功能
public @interface MyAnno {
    // 参照SuppressedWarning, 此处的value()是变量

    String value1() default "def";

    RepeatableAnno[] value();
}
