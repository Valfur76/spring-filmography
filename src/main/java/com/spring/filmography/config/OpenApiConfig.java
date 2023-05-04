package com.spring.filmography.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI filmographyProject() {
        return new OpenAPI()
                .info(new Info()
                        .title("Онлайн Фильмотека.")
                        .description("Сервис позволяющий просмотреть или купить фильм.")
                        .version("0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Anton Kokhno").email("9266010166@mail.ru").url(""))
                );
    }
}
