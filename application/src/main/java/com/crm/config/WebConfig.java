package com.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Applies to all endpoints
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:3001",
                        "https://crm-production-747d.up.railway.app/swagger-ui/index.html",
                        "https://crm-production-747d.up.railway.app",
                        "https://crm-frontend-taupe.vercel.app"
                )  // Swagger UI domain
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}