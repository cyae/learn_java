package com.learn.springboot.demo1.config;

import com.learn.springboot.demo1.bean.Car;
import com.learn.springboot.demo1.bean.Pet;
import com.learn.springboot.demo1.bean.User;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SuppressWarnings("unused")
/**
 * @Configuration注解表示该类是一个配置类
 * 1. 配置类里使用@Bean注解向容器中注册组件, 默认是单实例的
 * 2. 配置类MyConfig本身也会注册为一个组件
 * 3. proxyBeanMethods, 是否开启CGLIB动态代理: 
 *      默认是true, 表示进行增强代理, 增强效果为所有@Bean方法返回单例对象(单例模式) | 但导致额外的CGLIB子类扫描
 *      如果是false, 表示不进行增强代理, 效果为所有@Bean方法返回新创建的对象(原型模式) | 没有子类扫描, 速度快
 *      作用: 解决组件间相互依赖的问题
 */
@Configuration(proxyBeanMethods = true)

/**
 * @Import注解可以自动调用类的构造器, 创建组件并注册到IOC
 * 相比于@Configuration + @Bean, @Import可以导入多个类, 且类的来源更灵活(如来自第三方jar包)
 * 组件名为全类名
 */
@Import({Pet.class, Car.class})
@EnableConfigurationProperties(Car.class)

/**
 * 针对使用springMVC或spring的老项目, 或某些依赖:
 * @ImportResource注解可以扫描在xml中配置的组件, 并注册到IOC
 */
// @ImportResource("classpath:beans.xml")
public class MyConfig {

    // 等价于在xml中配置bean组件, <bean id=方法名  或  @Bean中的名字>
    // 返回类型为注册组件的类型, 返回值为组件在IOC容器中保存的对象
    @Bean("userComponent01")

    // 条件配置: 只有当前容器中存在Pet类型的组件时, 才会创建User组件
    // 注意条件配置的顺序性: 因为MyConfig上已经用@Import注解导入了Pet类型的组件, 所以这里可以注册User组件
    // 但如果只存在下边的@Bean("petComponent01"), 由于其在条件配置之后, 所以不会创建User组件
    @ConditionalOnBean(Pet.class)
    public User user01() {
        // 这里组件user依赖于组件pet, 如果使用CGLIB动态代理, 则IOC容器中的pet也是单例的
        return new User("zhangsan", 18, pet01());
    }

    @Bean("petComponent01")
    public Pet pet01() {
        return new Pet("cat", 4);
    }

}
