package com.domloge.catholicon.catholiconmsseasons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.domloge.catholicon.ms.common.CacheControlFilter;

@Configuration
public class CacheControlFilterConfig {
	
	@Autowired
	private ApplicationContext context;

    
    @Bean
    public FilterRegistrationBean cacheFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CacheControlFilter filter = context.getBean(CacheControlFilter.class);
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/seasons"); 
        registrationBean.setName("cacheControlFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}