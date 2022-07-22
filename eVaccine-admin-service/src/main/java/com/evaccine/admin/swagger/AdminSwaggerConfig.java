package com.evaccine.admin.swagger;

import static springfox.documentation.builders.RequestHandlerSelectors.any;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AdminSwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo("Admin Service Rest APIs", "APIs for Admin Service.", "1.0", "Terms of service",
                new Contact("venu", "www.google.com", "venu@swaggeradmin.com"), "License of API", "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select().apis(any())
                .paths(PathSelectors.any()).build();
    }
}
