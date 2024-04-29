package com.example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private CustomHeaderAuthFilter customHeaderAuthFilter;

    @Bean
    public FilterRegistrationBean<CustomHeaderAuthFilter> filterRegistrationBean() {
        FilterRegistrationBean<CustomHeaderAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(customHeaderAuthFilter);
      
        registrationBean.addUrlPatterns("/api/v1/admins"); 
        registrationBean.addUrlPatterns("/api/v1/users");
        registrationBean.addUrlPatterns("/api/v1/appointments");
        return registrationBean;
    }
}
