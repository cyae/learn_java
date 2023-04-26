package com.java.Annotation;

import com.JNF.RepeatableAnno;

// 如果注解类定义了变量, 则必须赋值使用
// 如果注解类没有变量, 比如@Override, 称为标记
@MyAnno({ @RepeatableAnno(value = "eee"), @RepeatableAnno(value = "rrr") })
public class TestClass {

}
