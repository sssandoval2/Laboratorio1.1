# Laboratorio 1.1 – API REST de productos (Spring Boot)

Práctica 1.1 de Programación Avanzada (ESPE). API REST con inyección de dependencias y manejo centralizado de excepciones. Sin base de datos: los productos viven en memoria.

## Requisitos
Java 25 LTS (Maven Wrapper incluido).

## Ejecutar
```
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
./mvnw test
```
La API queda en http://localhost:8080

## Endpoints
| Método | Ruta | Respuesta |
|---|---|---|
| GET | /api/productos | 200 |
| GET | /api/productos/{id} | 200 / 404 |
| POST | /api/productos | 201 / 400 |
| DELETE | /api/productos/{id} | 204 / 404 |

Ejemplo: `curl -i -X POST -H "Content-Type: application/json" -d '{"nombre":"Laptop","precio":850.5,"stock":10}' http://localhost:8080/api/productos`

Errores: `{"mensaje":"...","estado":404}`

Autor: Steven Sebastián Sandoval Casillas
