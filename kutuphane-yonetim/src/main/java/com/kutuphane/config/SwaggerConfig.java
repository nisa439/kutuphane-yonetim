package com.kutuphane.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Kütüphane Yönetim Sistemi API")
                        .version("1.0.0")
                        .description("Spring Boot ile geliştirilmiş kütüphane yönetim sistemi REST API dökümantasyonu")
                        .contact(new Contact()
                                .name("Geliştirici")
                                .email("developer@kutuphane.com")));
    }
}