package br.com.luiz.agendamentoservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(info =
@Info(title = "Agendamento Service Api",
        version = "1.0.0",
        description = "Documentation of Agendamento Service API")
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Agendamento Service Api")
                                .version("1.0.0")
                                .license(new License()
                                        .name("Apache License, Version 2.0")
                                        .url("http://springdoc.org"))
                );
    }

}
