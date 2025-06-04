package com.plaza.traceability.infraestructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Trazabiolidad")
                        .version("v1")
                        .description("API para gestionar la trazabilidad de una plazoleta de comidas de un centro comercial"));
    }
}
