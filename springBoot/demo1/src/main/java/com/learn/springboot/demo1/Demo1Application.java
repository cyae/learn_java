package com.learn.springboot.demo1;

import java.util.Arrays;

import com.learn.springboot.demo1.bean.Pet;
import com.learn.springboot.demo1.bean.User;
import com.learn.springboot.demo1.config.MyConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类, 入口
 * 
 * @SpringBootApplication = @SpringBootConfiguration + @ComponentScan
 *                        + @EnableAutoConfiguration
 * 1. @SpringBootConfiguration等价于@Configuration, 表明Demo1Application也是一个配置类, 默认使用CGLIB
 * 2. @ComponentScan用于指定组件扫描范围, 内含2个Filter
 * 3. @EnableAutoConfiguration = @AutoConfigurationPackage + @Import(AutoConfigurationImportSelector.class)
 *      3.1 @AutoConfigurationPackage
 *          等价于@Import(AutoConfigurationPackages.Registrar.class), 
 * 			其中的Registrar内部类
 * 			将@AutoConfiguration所修饰的启动类Demo1Application
 * 			所在包com.learn.springBoot.demo1作为扫描组件的入口
 * 		3.2 @Import(AutoConfigurationImportSelector.class)
 * 			使用AutoConfigurationImportSelector.getAutoConfigurationEntry()方法对候选组件去重/去黑名单并返回
 * 			候选组件列表由getCandidateConfigurations调用工厂方法springFactoriesLoader.loadSpringFactories()方法加载
 * 			在loadSpringFactories方法中, 维护了一个多值Map<String, List<String>>作为IOC容器, 其中的key是组件名称, value是组件的全限定类名
 * 			扫描方式: 将所有依赖jar包中满足/META-INF/spring.factories目录结构的文件作为Enumeration
 * 			遍历该枚举, 将每个元素的配置类内容作为Properties写入到Map中
 * 
 * 			注意: 其中用于自动配置的依赖spring-boot-autoconfiguration已经写死了常用场景的配置, 包括AOP/springMVC/thymeleaf/redis/jdbc/mybatis等
 * 			这些配置类内容对应的组件在启动时默认全部加载, 但由于每个组件的配置方式都用@ConditionalOnXXX来判断是否在POM导包,
 * 			因此, springBoot依靠 默认全量配置类 + 按条件装配使对应配置生效  的方式实现了自动配置.
 * 
 * 4. springBoot常用配置规范:
 *      4.1 @ConditionalOnMissingBean + 配好的组件, 表示springBoot提供默认配置方法, 但如果有用户自定义配置, 则默认配置不生效
 *    		4.1.1 自定义方法一: 自定义配置类, 使用@Configuration + @Bean注解
 * 			4.1.2 自定义方法二: 通过.properties/.yaml配置文件以键值对形式配置
 * 		4.2 @ConditionalBean(bean.class) + @ConditionalOnMissingBean(标准bean名称), 表示如果容器中存在用户自定义的名称不规范的bean, 则将其转为规范名称的bean
 * 			
 * 5. 通过配置文件中debug=true可以看到自动配置报告
 */
@SpringBootApplication(scanBasePackages = "com.learn.springboot.demo1")
public class Demo1Application {

	public static void main(String[] args) {
		// 返回IOC容器
		ConfigurableApplicationContext run = SpringApplication.run(Demo1Application.class, args);

		// 查看IOC容器中的组件名
		// Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);

		// 获取这些组件
		User bean1 = run.getBean("userComponent01", User.class);
		User bean2 = run.getBean("userComponent01", User.class);
		// 组件默认是单例的, 所以两个bean是同一个对象
		System.out.println(bean1 == bean2);

		// 配置类也是一个组件
		MyConfig bean3 = run.getBean(MyConfig.class);
		User user01 = bean3.user01();
		User user02 = bean3.user01();
		System.out.println(user01 == user02);

		// 通过@Bean注册的组件是单例的
		Pet bean4 = run.getBean("petComponent01", Pet.class);
		
		// 通过import注册的组件也是单例的, 但与@Bean注册的组件不是同一个
		Pet bean5 = run.getBean("com.learn.springboot.demo1.bean.Pet", Pet.class);
		Arrays.stream(run.getBeanNamesForType(Pet.class)).forEach(System.out::println);
		Pet bean6 = run.getBean("com.learn.springboot.demo1.bean.Pet", Pet.class);
		
		System.out.println("组件依赖, 开启CGLIB后容器中只存在一类组件" + (user01.getPet() == bean4));
		System.out.println("通过import注册的组件也是单例的: " + (bean5 == bean6));
		System.out.println("但与@Bean注册的组件不是同一个: " + (bean5 == bean4));

		// 关闭IOC容器
		// run.close();
	}

}
