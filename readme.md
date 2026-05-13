# Proyecto Semillero - Listado de Convenios

Aplicacion fullstack POC para visualizar un listado de convenios con imagen, nombre y ciudad.
El stack completo incluye un frontend React (S3 + CloudFront), un backend Lambda (Java) con API Gateway,
y logging centralizado en CloudWatch.

## Arquitectura general

```
Usuario → CloudFront → S3 (React SPA) → API Gateway → Lambda (Java) → Lista hardcodeada (20 convenios)
                                                              ↓
                                                        CloudWatch Logs
```

## Modulos

| Modulo                   | Lenguaje   | Proposito                                    |
|--------------------------|------------|----------------------------------------------|
| frontend-react-spa       | TypeScript | SPA React con listado de convenios           |
| lambda-consulta-convenios | Java      | Funcion Lambda que expone GET /convenios     |

## Requisitos previos

- Docker & Docker Compose (recomendado)
- Node.js 20+ (para desarrollo local del frontend)
- Java 11+ y Maven 3.9+ (para desarrollo local del backend)

## Inicio rapido

```bash
# Clonar el repositorio
cd ProyectoSemillero

# Levantar todo el stack
docker compose up --build

# Frontend: http://localhost:8080
# Backend:  http://localhost:9000/convenios
```

## Documentacion

| Archivo                                    | Descripcion                                  |
|--------------------------------------------|----------------------------------------------|
| docs/00-overview.md                        | Vision general del proyecto                  |
| docs/01-build-and-run.md                   | Compilacion y ejecucion                      |
| docs/02-installation-by-environment.md     | Instalacion por entorno                      |
| docs/03-testing.md                         | Estrategia de pruebas                        |
| docs/04-backend-services.md                | Servicios backend y API                      |
| docs/05-docker-compose.md                  | Guia de Docker Compose                       |
| postman/collection.postman_collection.json | Coleccion Postman para pruebas de API        |
