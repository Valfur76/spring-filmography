package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootApplication
public class SpringFilmographyApplication implements CommandLineRunner {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(SpringFilmographyApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Swagger path: http://localhost:8080/swagger-ui/index.html");
    }
}
