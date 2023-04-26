package com.learn.springmvc.config;

import com.learn.springmvc.interceptor.myInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;


/**
 * 代替spring-mvc.xml
 * 1. 开启注解扫描
 * 2. 开启thymeleaf视图解析器
 * 3. 开启简单页面跳转 view-controller
 * 4. 开启默认servlet, 处理静态资源访问 default-servlet-handler
 * 5. 开启mvc注解驱动 annotation-driven
 * 6. 开启上传解析器 multipartResolver
 * 7. 异常处理 SimpleMappingExceptionResolver
 * 8. 开启自定义拦截器 interceptor
 */
@Configuration
@ComponentScan("com.learn.springmvc") // 1
@EnableWebMvc // 5
public class WebConfig implements WebMvcConfigurer {

    // 2.1 templateResolver
    @Bean // @Bean注解返回bean对象
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    // 2.2 templateEngine, 并注入templateResolver
    // 以传参的形式进行自动装配
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    // 2.3 viewResolver, 并注入templateEngine
    // 以传参的形式进行自动装配
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    // 3
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("index");
    }

    //4
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
    }

    // 6
    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    //7 异常处理也可以省略, 使用@ControllerAdvice
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        prop.setProperty("java.lang.Exception", "error/exception1");
        prop.setProperty("java.lang.RuntimeException", "error/exception2");
        smer.setExceptionMappings(prop);
        smer.setExceptionAttribute("exception");
        smer.setDefaultErrorView("/error/exception");
        smer.setDefaultStatusCode(500);
        resolvers.add(smer);
    }

    // 8
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new myInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/");
    }
}
