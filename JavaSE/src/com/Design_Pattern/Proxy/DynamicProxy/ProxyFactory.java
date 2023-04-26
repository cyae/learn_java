package com.Design_Pattern.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* 
动态代理的必要性：
🟠越来越多的非业务需求(日志和验证等)插入后，原有的业务方法随之横向膨胀。
🟠每个业务方法在处理核心逻辑的同时，还必须兼顾这些非业务功能。
🟠每新添加了方法，就得重复写相应的日志和验证逻辑。
🟠如果日志需求发生变化，必须修改所有模块的日志功能。
🟠比如日志：在此类的方法里都显示方法名->显示方法名+参数列表+返回值，那么每个方法都得一个一个改。

静态代理或普通的模板模式无法解决上述问题：
❌虽然可以用非业务部分包装核心业务，但静态代理需要为每组接口(及其实现类)创建1个代理类
❌如果被代理类的接口抽象方法拓展，需要改源码拓展代理类，且会导致代理类过多
💡只需创建一个通用代理类实现方法调用接口，无论被代理类的接口中的抽象方法如何拓展，包装这个方法调用接口即可
💡使用反射，在运行期间根据被代理类及其接口确定代理类，并使用动态代理类调用被代理类实现的方法

动态代理的优势：
🟢根据已有被代理类，动态生成(而非静态编写)代理类及其对象，并调用被代理类的方法
🟢使用代理类对象调用被代理类方法的同时，可以根据模板模式思想，进行方法丰富
🟢如果需求发生更改，只用改变模板部分即可
🟢适用于所有接口(及其实现类)，因为是对 调用方法invoke() 这一动作的封装
*/

public class ProxyFactory {
    // 通过被代理类对象obj，生产代理类Proxy
    // 生产的代理类也需实现和obj同样的接口,方便方法调用
    @SuppressWarnings("unchecked") // 强转泛型
    public static <T> T getProxyInstance(T obj) {
        IH handler = new IH(obj);
        // handler.bind(obj);
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(), // 被代理类的类加载器, 用于加载代理类
                obj.getClass().getInterfaces(), // 被代理类实现的所有接口，也是代理类的接口
                handler); // handler用于动态调用被代理类中实现的方法
    }
}

// 通过动态创建的代理类调用被代理类的实现方法
// IH对象作为参数返回给动态创建的代理类
// ❗注意，在静态代理中，这一步是通过在具体代理类中重写接口方法实现的
// ❗在动态代理中代理类是运行时动态创建的，没有具体代理类
// ❗所以不能像静态代理重写方法，只能通过反射调用动态代理类clazz的Method的invoke方法
// ❗invoke参数中的调用对象改成被代理对象即可，仍然间接体现向上转型及动态绑定机制
class IH implements InvocationHandler {

    private Object obj; // 被代理类对象

    public void bind(Object obj) { // 或写成构造器
        this.obj = obj;
    }

    public IH(Object obj) {
        this.obj = obj;
    }

    // 通过反射调用被代理类对象obj的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LoggerUtil logger = new LoggerUtil();

        logger.startLogger(); // 非业务通用方法

        method.invoke(obj, args); // AOP：面向切面变成，核心method可变

        logger.endLogger(); // 非业务通用方法

        return null;
    }
}

class LoggerUtil {
    public void startLogger() {
        System.out.println("日志记录器启动~~~");
    }

    public void endLogger() {
        System.out.println("日志记录器关闭~~~");
    }
}