package com.learn.springmvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

// 使用注解文件代替web.xml
// 对于springMVC, web.xml需要配置DispatcherServlet + CharacterEncodingFilter + HiddenHttpMethodFilter
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * @return 指定spring的配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * @return 指定SpringMVC的配置类, 等价于spring-mvc.xml
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * @return 指定DispatcherServlet的映射路径, 等价于<url-pattern>
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * @return 过滤器
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                new CharacterEncodingFilter("UTF-8", true),
                new HiddenHttpMethodFilter()
        };
    }
}
