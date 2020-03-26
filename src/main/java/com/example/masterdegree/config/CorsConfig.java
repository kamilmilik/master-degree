package com.example.masterdegree.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000", "http://localhost:4200",  "http://localhost:8095", "http://localhost:8080"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(true);
    }



}
// Swagger: http://localhost:8095/v3/api-docs/, http://localhost:8095/swagger-ui.html
