package com.freelanceworld.common.util;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "ezLaborCrudApi")
    public OpenAPI ezLaborCrudApi() {
        return new OpenAPI().components(new Components()).info(new Info().title("freelanceWorld API with SpringBoot")
                .description("freelanceWorld Crud API implemented with Spring Boot RESTFUL" +
                        "documented using springdoc-openapi"));
    }

}