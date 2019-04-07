package com.domloge.catholicon.catholiconmsseasons;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.domloge.catholicon.ms.common.CacheControlFilter;

@Configuration
public class RestConfiguration {

    @Bean
    public FilterRegistrationBean cacheControlFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter filter = new CacheControlFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/seasons/*");
        return registration;
    }
}