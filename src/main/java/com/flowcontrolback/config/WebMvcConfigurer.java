package com.flowcontrolback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebMvcConfigurer {

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMapping(CorsRegistry registry) {
                registry.addMapping("/api")
                        .allowedOrigins("/**")
                        .allowedMethods("PUT", "GET", "DELETE", "POST")
                        .maxAge(3600);
            }
        };
    }

}
