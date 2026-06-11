package com.Assignment.shopping_carts.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * CORS Config
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */


@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Value("${APP_CORS_ALLOWED_ORIGINS:http://localhost:3000,http://localhost:3001,https://nightlyaffair.github.io,https://*.up.railway.app,https://*.railway.app}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints
                .allowedOriginPatterns(Arrays.stream(allowedOrigins.split(","))
                        .map(String::trim)
                        .filter(origin -> !origin.isEmpty())
                        .toArray(String[]::new))
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // Cache preflight response for 1 hour
    }
}
