package com.Guesmi.gestion.du.stock.config;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

import static com.Guesmi.gestion.du.stock.utils.Constants.APP_ROOT;


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
}
)
public class SwaggerConfiguration {

}
