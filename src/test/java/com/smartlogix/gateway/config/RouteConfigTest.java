package com.smartlogix.gateway.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Verifica que el Gateway define la ruta hacia el BFF.
// Asi se prueba que el enrutamiento del punto de entrada esta configurado correctamente.
@SpringBootTest
class RouteConfigTest {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void debeDefinirLaRutaHaciaElBff() {
        List<Route> rutas = routeLocator.getRoutes().collectList().block();

        assertNotNull(rutas);
        assertTrue(rutas.stream().anyMatch(r -> r.getId().equals("bff")),
                "Debe existir una ruta con id 'bff' que apunte al BFF");
    }

    @Test
    void laRutaDelBffDebeApuntarAlPuertoDelBff() {
        List<Route> rutas = routeLocator.getRoutes().collectList().block();
        Route bff = rutas.stream().filter(r -> r.getId().equals("bff")).findFirst().orElseThrow();

        assertEquals("localhost", bff.getUri().getHost());
        assertEquals(8088, bff.getUri().getPort());
    }
}
