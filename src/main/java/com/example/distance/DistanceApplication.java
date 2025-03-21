package com.example.distance;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPI31
@OpenAPIDefinition(
        info = @Info(
                title = "MedHeadPoc Distance",
                version = "1.0.0",
                description = "API for POC"
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Local server"),
                @Server(url = "https://api.distance.example.com", description = "Production server")
        }
)
public class DistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceApplication.class, args);
    }

}
