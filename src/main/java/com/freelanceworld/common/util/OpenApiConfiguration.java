package com.freelanceworld.common.util;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "FreelanceWorldCrudApi")
    public OpenAPI FreelanceWorldCrudApi() {
        return new OpenAPI().components(new Components()).info(new Info().title("FreelanceWorld API with SpringBoot")
                .description("FreelanceWorld Crud API implemented with Spring Boot RESTFUL " +
                        "documented using springdoc-openapi"));
    }
}