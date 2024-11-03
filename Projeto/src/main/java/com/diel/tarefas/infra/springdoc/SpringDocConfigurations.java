package com.diel.tarefas.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Lista de Tarefas - Código Certo Coders")
                        .description("API Rest da trilha do Código Certo Coders, contendo as funcionalidades de CRUD de tarefas")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("diel.dev@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://diel/api/licenca"))
                        .version("1.0"));
    }
}
