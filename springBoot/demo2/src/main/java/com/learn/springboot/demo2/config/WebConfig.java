package com.learn.springboot.demo2.config;

import com.learn.springboot.demo2.bean.Pet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @Import({ Pet.class})
// @EnableConfigurationProperties(Pet.class)
public class WebConfig {
    
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                Converter<String, Pet> converter = new Converter<>() {
                    @Override
                    public Pet convert(String s) {
                        Pet res = new Pet();
                        String[] ss = s.split(",");
                        res.setName(ss[0]);
                        res.setAge(Integer.parseInt(ss[1]));
                        return res;
                    }
                };
                registry.addConverter(converter);

                // registry.addConverter(new Converter<String, Pet>() {
                //     @Override
                //     public Pet convert(String s) {
                //         Pet res = new Pet();
                //         String[] ss = s.split(",");
                //         res.setName(ss[0]);
                //         res.setAge(Integer.parseInt(ss[1]));
                //         return res;
                //     }
                // });
            }
        };
    }
}
