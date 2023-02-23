package com.dongdongshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        第二步：设置模板文件所在的路径。
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:freemarker");
//        第三步：设置模板文件使用的字符集。一般就是 utf-8.
        freeMarkerConfigurer.setDefaultEncoding("utf-8");
        return freeMarkerConfigurer;
    }

}
