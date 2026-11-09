# Laboratorio 1.1

Desarrollo de una API REST con inyección de dependencias y manejo centralizado de excepciones.

## Requisitos
Java 25 LTS (Maven Wrapper incluido).

## Ejecutar
```
./mvnw spring-boot:run        
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

Asignatura: Programación Avanzada
Autor: Steven Sebastián Sandoval Casillas
NRC: 36759
