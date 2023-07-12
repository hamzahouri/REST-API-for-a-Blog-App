package com.hamza.blog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info=@Info(
                title="Blog rest Api",
                description = "Spring boot springboot REST API blog",
                version = "1.0",
                contact = @Contact(
                        name = "hamza houri",
                        email = "hamzahouri@gmail.com"
                ),
                license = @License(
                        name = "MIT"
                )
        )
)
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }
}
