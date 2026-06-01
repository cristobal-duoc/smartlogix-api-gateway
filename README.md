# api-gateway

API Gateway de SmartLogix, construido con **Spring Cloud Gateway**.

## Responsabilidad

Es la **puerta de entrada unica** del backend. Todas las peticiones del frontend entran
por aqui, y el Gateway las enruta hacia el BFF. Asi el frontend no conoce las direcciones
ni los puertos de los componentes internos, y se centraliza el acceso al sistema.

Flujo: `Frontend -> API Gateway (8080) -> BFF (8088) -> microservicios (8081 / 8082)`.

## Puerto

`8080` (entrada unica del backend).

## Tecnologias

- Java 17
- Spring Boot 3.2.5
- Spring Cloud Gateway 2023.0.3 (reactivo, WebFlux)

## Enrutamiento

| Ruta de entrada | Destino |
|-----------------|---------|
| `/api/bff/**`   | BFF (`http://localhost:8088`) |

La ruta se define en `RouteConfig` (bean `RouteLocator`), lo que la hace explicita y testeable.

## Diferencia con el BFF

El API Gateway **no reemplaza** al BFF: son capas distintas.
- **API Gateway**: punto de entrada unico, enrutamiento y acceso centralizado (borde del sistema).
- **BFF**: adapta y agrega los datos para el frontend (detras del Gateway).

## Instalacion y ejecucion

```bash
mvn spring-boot:run
```

## Pruebas y cobertura

```bash
mvn test      # ejecuta las pruebas
mvn verify    # pruebas + reporte de cobertura + validacion del minimo (>=60%)
```

- `ApiGatewayApplicationTests`: carga del contexto.
- `RouteConfigTest`: verifica que la ruta hacia el BFF este definida y apunte al puerto correcto.

Reporte de cobertura (JaCoCo): `target/site/jacoco/index.html`. La regla `jacoco:check`
falla el build si la cobertura baja del 60%. Cobertura actual: **66.7% (SonarCloud) / 71.4% (JaCoCo)**,
por encima del minimo exigido (la diferencia se debe a la clase de arranque, que solo contiene el metodo main).
