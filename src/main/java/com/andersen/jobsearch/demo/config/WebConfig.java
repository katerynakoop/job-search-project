package com.andersen.jobsearch.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ClassLoaderTemplateResolver templateResolver() 
    {
        ClassLoaderTemplateResolver templateResolver = 
                new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() 
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
    	registry.addResourceHandler("/images/**").addResourceLocations("/static/img/");
    }
}