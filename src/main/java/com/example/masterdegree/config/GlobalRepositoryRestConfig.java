package com.example.masterdegree.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class GlobalRepositoryRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
                .addMapping("/**")
                .allowedOrigins("http://localhost:5000","http://localhost:3000", "http://localhost:4200", "http://localhost:8095")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(true);

    }


}