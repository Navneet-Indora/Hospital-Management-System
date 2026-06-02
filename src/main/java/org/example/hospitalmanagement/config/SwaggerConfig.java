package org.example.hospitalmanagement.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Management System API")
                        .description("REST API for managing hospital operations" +
                             " including Patient, Doctor, Appointment and Billing")
                        .contact(new Contact()
                                .name("Navneet Indora")
                                .email("navneet.indora1@gmail.com")
                        )
                        .version("1.0"))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("bearerAuth"))

                .components(new Components()
                        .addSecuritySchemes(
                                "bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ));
    }
}
