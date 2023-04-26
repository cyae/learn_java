package com.java.Annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Retention声明自定义注解的最大生命周期, 默认是RetentionPolicy.CLASS
// 如果是RetentionPolicy.RUNTIME级别, 注解会一直保留在JVM运行期间, 就可以使用反射读取注解
@Retention(RetentionPolicy.RUNTIME)
public @interface MetaAnno {

}

// Target声明自定义注解的可修饰范围, jdk8将泛型类型加入修饰范围
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
@interface MetaAnno1 {

}

// Documented声明在生成被自定义注解修饰目标的javadoc时, 包括自定义注解信息
@Documented
@interface MetaAnno2 {

}

// Inherented使注解类可被继承