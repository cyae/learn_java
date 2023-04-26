package com.learnjava.spring.demo_aop.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.learnjava.spring.demo_aop.mapper.UserMapper;
import com.learnjava.spring.demo_aop.mapper.UserMapperImpl;

public class JDKProxy {
    public static void main(String[] args) {
        // newProxyInstance返回被代理接口的增强代理类对象
        UserMapper enhancedMapper = (UserMapper) Proxy.newProxyInstance(
            // 类加载器, 用于加载增强代理类
                JDKProxy.class.getClassLoader(),
            // 被代理接口
                UserMapperImpl.class.getInterfaces(),
            // 方法调用类
                new IH(new UserMapperImpl())
            );

        // 调用增强代理类对象的方法
        int res = enhancedMapper.add(7, 2);
        System.out.println("结果:" + res);

        System.out.println("******************************************");

        enhancedMapper.update("1").update("2").update("3");
    }
}

// 方法调用类, 在此实现功能增强
class IH implements InvocationHandler {

    // 传入被代理类的对象proxyee, 用于调用其方法
    private Object proxyee;

    public IH(Object proxyee) {
        this.proxyee = proxyee;
    }

    // 具体增强
    @Override
    public Object invoke(Object proxyer, Method method, Object[] args) throws Throwable {
        // proxyer是真实代理对象, 在代理期间动态创建, 其类型为com.sun.proxy.$Proxy0


        if (method.getName().equals("add")) {
            // 增强切面1
            System.out.println("方法执行前, 向" + method.getName() + "传递参数:" + Arrays.toString(args));

            // 被代理类方法调用
            Object res = method.invoke(proxyee, args);

            // 增强切面2
            System.out.println("方法执行后");

            // 返回期望的方法调用结果
            return res;
        } else if (method.getName().equals("update")) {

            // 增强切面1
            System.out.println("方法执行前, 向" + method.getName() + "传递参数:" + Arrays.toString(args));

            // 被代理类方法调用
            method.invoke(proxyee, args);

            // 增强切面2
            System.out.println("方法执行后");
            
            // 如果返回增强代理对象proxyer, 则可以链式调用
            return proxyer;
        }
        
        return null;
    }

}