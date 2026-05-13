# 00 - Vision General del Proyecto

## Nombre

Proyecto Semillero - Sistema de Consulta de Convenios

## Objetivo

Construir una aplicacion web serverless en AWS que permita visualizar un listado de convenios
con imagen, nombre y ciudad. El proyecto sirve como POC (Proof of Concept) para validar la
arquitectura propuesta.

## Componentes del sistema

1. **Amazon S3**: Aloja los archivos estaticos de la aplicacion React.
2. **Amazon CloudFront**: CDN que distribuye el contenido del bucket S3.
3. **Amazon API Gateway**: Proxy REST que recibe las peticiones del frontend y las envia a Lambda.
4. **AWS Lambda**: Funcion Java que procesa la logica de negocio.
5. **Amazon CloudWatch Logs**: Almacena los logs generados por la funcion Lambda.

## Flujo de datos

1. El usuario accede via CloudFront a la SPA alojada en S3.
2. La SPA realiza una peticion GET a API Gateway en la ruta /convenios.
3. API Gateway invoca la funcion Lambda con el evento de proxy.
4. La Lambda consulta la lista hardcodeada de 20 convenios.
5. La Lambda registra logs en CloudWatch.
6. La respuesta JSON viaja de vuelta al frontend.
7. La SPA renderiza las tarjetas de convenios en el navegador.

## Stack tecnologico

- Frontend: React 18, TypeScript, Vite 5
- Backend: Java 11, AWS Lambda, Maven
- Infraestructura: Docker Compose (desarrollo local)
- API: REST sobre HTTP, formato JSON
