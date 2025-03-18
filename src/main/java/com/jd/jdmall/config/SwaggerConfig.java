package com.jd.jdmall.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("仿京东商城 API 文档")
                        .version("1.0")
                        .description("仿京东商城后端API文档，基于Spring Boot和Swagger"));
    }
}