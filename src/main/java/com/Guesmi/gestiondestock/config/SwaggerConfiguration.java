package com.Guesmi.gestiondestock.config;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;



@OpenAPIDefinition(
        info=@Info(
                contact= @Contact(
                        name="Guesmish",
                        email ="guesmi.achraf3@gmail.com",
                        url="https://guesmishCoding.com"
                ),
                description = "Gestion de stock API documentation",
                title = "Gestion de stock REST API",
                version = "1.0",
                license= @License(
                        name="Licence name",
                        url="https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers={
            @Server(
                    description="Local ENV",
                    url="http://localhost:8082"
            ),
                @Server(
                        description="PROD ENV",
                        url="https://guesmishCoding.com"
                )
},
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }

)

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme="bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in= SecuritySchemeIn.HEADER
)


public class SwaggerConfiguration {
}



