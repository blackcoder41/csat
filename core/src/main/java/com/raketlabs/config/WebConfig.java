package com.raketlabs.config;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;


//@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    ResourceHandlerRegistry mResourceHandlerRegistry;
    
    
    
    @Value("${spring.resources.chain.strategy.content.enabled}")
    private boolean strategyContentEnabled;
    
    @Value("${spring.resources.chain.strategy.content.paths}")
    private String[] strategyContentPaths;
    

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        mResourceHandlerRegistry = registry;
        
        cacheResource("/images/**", "classpath:/static/images/", 365);
        cacheResource("/css/**", "classpath:/static/css/", 365);
        cacheResource("/js/**", "classpath:/static/js/", 365);
        cacheResource("/webjars/**", "/webjars/", 365);
        cacheResource("/qr/**", "file:public/", 365);
    }
    
    public void cacheResource (String antPath, String resourceLocation, int days) {
        
        ResourceChainRegistration res = 

        mResourceHandlerRegistry.addResourceHandler(antPath)
        .addResourceLocations(resourceLocation)
        .setCacheControl(CacheControl.maxAge(days, TimeUnit.DAYS))
        .resourceChain(false);
        
        if (strategyContentEnabled)
        res.addResolver(new VersionResourceResolver().addContentVersionStrategy(strategyContentPaths));
        
    }
}
