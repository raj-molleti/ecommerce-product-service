package com.nisum.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI / Swagger configuration.
 * UI available at: http://localhost:8080/swagger-ui.html
 * JSON spec at:     http://localhost:8080/v3/api-docs
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecommerceProductOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce Product Catalog API")
                        .description("REST APIs for managing Products and Categories in the ecommerce catalog")
                        .version("v1.0.0")
                        .contact(new Contact().name("Nisum Ecommerce Team").email("catalog-team@example.com"))
                        .license(new License().name("Internal Use Only")));
    }
}
