package com.smartlogix.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuracion de enrutamiento del API Gateway.
// Define el unico punto de entrada del backend: todas las peticiones del frontend
// que empiezan con /api/bff/** se reenvian al BFF. De esta forma el frontend no
// conoce ni las direcciones ni los puertos de los componentes internos.
@Configuration
public class RouteConfig {

    // URL del BFF (configurable en application.yml). El BFF escucha en el puerto 8088.
    @Value("${bff.url}")
    private String bffUrl;

    @Bean
    public RouteLocator rutas(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ruta hacia el BFF: /api/bff/** -> BFF
                .route("bff", r -> r.path("/api/bff/**").uri(bffUrl))
                .build();
    }
}
