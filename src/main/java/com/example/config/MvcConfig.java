package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    public static final String WEB_JARS_PATH_PATTERN = "/webjars/**";

    /**
     * Static resources mapping
     *
     * @param registry Stores registrations of resource handlers for serving static resources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern(WEB_JARS_PATH_PATTERN)) {
            registry.addResourceHandler(WEB_JARS_PATH_PATTERN).addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

}