package com.smartlogix.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Punto de entrada del API Gateway de SmartLogix.
// Es la puerta de entrada unica del backend: el frontend siempre entra por aqui
// y el Gateway enruta hacia el BFF (y este, a su vez, hacia los microservicios).
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
