package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot API với Swagger")
                        .version("1.0.0")
                        .description("Tài liệu API cho ứng dụng Spring Boot + PostgreSQL"));
    }
}
