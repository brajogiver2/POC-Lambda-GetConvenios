# Lambda Consulta Convenios

Funcion AWS Lambda en Java que expone un endpoint REST para consultar la lista de convenios.
Los datos estan hardcodeados (20 convenios) para fines de la POC.

## Arquitectura

- **Lenguaje:** Java 11
- **Framework:** AWS Lambda Java Core + API Gateway Events
- **Estilo:** Hexagonal (dominio, servicio, infraestructura)
- **Build:** Maven con shade plugin para uber-jar

## Estructura del proyecto

```
lambda-consulta-convenios/
  pom.xml
  Dockerfile
  README.md
  openapi/openapi.yaml
  src/main/java/com/semillero/convenios/
    Handler.java                   # Entry point Lambda
    domain/Convenio.java           # Modelo de dominio
    domain/ErrorResponse.java      # Modelo de error
    service/ConvenioService.java   # Logica de negocio
    infrastructure/HardcodedDataStore.java  # Datos hardcodeados
    infrastructure/LoggerAdapter.java       # Adaptador de logging CloudWatch
  src/test/java/com/semillero/convenios/
    service/ConvenioServiceTest.java
    HandlerTest.java
```

## Compilacion y ejecucion

```bash
# Compilar
cd lambda-consulta-convenios
mvn clean package

# Ejecutar pruebas
mvn test

# Cobertura
mvn test jacoco:report
```

## Endpoint

| Metodo | Path         | Descripcion                   |
|--------|--------------|-------------------------------|
| GET    | /convenios   | Retorna lista de 20 convenios |

## Logs

La funcion utiliza java.util.logging. En AWS Lambda, toda salida de log se envia automaticamente a CloudWatch Logs.

## Pruebas unitarias

- `ConvenioServiceTest`: Verifica que el servicio retorna 20 convenios y valida inyeccion de dependencias.
- `HandlerTest`: Verifica codigos HTTP 200 y 500 segun el escenario.

## Cobertura de codigo

Ejecutar `mvn test` para validar pruebas. Se recomienda agregar jacoco-maven-plugin para reporte de cobertura.
