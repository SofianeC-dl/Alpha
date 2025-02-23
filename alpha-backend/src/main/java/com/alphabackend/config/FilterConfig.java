package com.alphabackend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestCachingFilter> cachingFilterRegistration() {
        FilterRegistrationBean<RequestCachingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestCachingFilter());
        registrationBean.addUrlPatterns("/*"); // Appliquer à toutes les URL
        registrationBean.setOrder(1); // S'assurer que le filtre est exécuté en premier
        return registrationBean;
    }
}

