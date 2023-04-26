# Spring

## IOC创建对象

* IOC = 工厂模式 + 反射 + xml/注解
* 以往service调用mapper接口/controller调用service接口的方法: 直接new, 不同层间高度耦合

```java
    class xxxService {
        public void service() {
            xxxMapper mapper = new xxxMapper();
            mapper.doService();
        }
    }
```

* spring使用IOC容器, 降低耦合

```xml
<beans>
    <!--
        scope规定了bean创建方式: 
          singleton单例(默认值), 加载xml时创建对象, 类比懒汉式, 慢启动快服务 
          prototype多例, 调用getBean时创建对象, 类比饿汉式, 快启动慢服务
    -->
    <bean id="xxxMapper" class="全类名" scope="singleton"/>
</beans>
```

```java
    class xxxMapperFactory {
        public static xxxMapper getMapperInstance(String XMLPath) {
            String classPath = readFromXML(XMLPath);
            Class<?> clazz = Class.forName(classPath);
            return (xxxMapper) clazz.newInstance();
        }
    }

    class xxxService {
        public void service() {
            xxxMapper mapper = xxxMapperFactory.getMapperInstance();
            mapper.doService();
        }
    }
```

## IOC注入属性

* 以往使用setter和有参构造器设置属性
* spring通过IOC实现属性注入
  * 可以注入一般成员属性(String)
  * 属性值也可能是:
    * 包含与xml语法冲突的特殊字符
    * 空值null
    * 引用
      * 外部引用(一对一关系(即依赖注入), bean-ref->并列bean, 耦合度低)
      * 内部引用(一对多关系, bean-property-嵌套内部bean, 耦合度高, 推荐使用外部引用实现内部引用)
    * 集合(多对一关系)
  * 通过引入泛型, IOC获得的对象可以与xml中配置的class不同, 即FactoryBean\<T>
* 注入风格可以是
  * 手动配置xml, bean-property/constructor
  * 自动配置xml

## IOC容器

* BeanFactory, spring框架的基本IOC容器, 只规定了bean的基本操作
  * 其实现类在加载xml时不会创建对象, 只有调用getBean()获取时才创建对象
  * 启动加载速度快, 但运行时按需响应式加载, 速度慢
* ApplicationContext, 是BeanFactory的子接口, 规定了更多bean操作
  * 其实现类在加载xml时将全部配置的对象进行注册
  * 启动加载慢, 但运行时已经全部加载完, 速度快
* 两者在反射创建对象时, 都默认使用无参构造, 因此pojo必须声明无参构造器

## Bean生命周期

类似servlet的生命周期, bean生命周期包括:

1. 无参构造器创建
2. setter赋值
3. init初始化, 使用AOP实现, 细分为:
    * bean实例传递给bean后置处理器before
    * 调用init
    * bean实例传递给bean后置处理器after
4. 调用方法, 进行服务
5. 当容器关闭时, 销毁bean
