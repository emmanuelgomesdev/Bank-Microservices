package com.emmanuel.accountservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                        .title("Account API")
                        .description("REST API for account management")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Emmanuel Gomes")
                                .email("emmanueu@gmail.com")
                                .url("https://github.com/EmmanuelGomesSilva")));
    }
}
