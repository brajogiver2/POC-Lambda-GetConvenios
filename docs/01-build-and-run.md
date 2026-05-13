# 01 - Compilacion y Ejecucion

## Requisitos

- Docker & Docker Compose
- Node.js 20+ (solo para desarrollo frontend)
- Java 11+ y Maven 3.9+ (solo para desarrollo backend)

## Compilacion individual

### Backend (Lambda)

```bash
cd lambda-consulta-convenios
mvn clean package
```

Esto genera `target/lambda-consulta-convenios-1.0.0.jar`.

### Frontend (React SPA)

```bash
cd frontend-react-spa
npm install
npm run build
```

Esto genera la carpeta `dist/` con los archivos estaticos.

## Ejecucion local con Docker Compose

```bash
# Desde la raiz del proyecto
docker compose up --build
```

Accesos:
- Frontend: http://localhost:8080
- API: http://localhost:9000/convenios

## Ejecucion local sin Docker

### Backend

```bash
cd lambda-consulta-convenios
mvn clean package
java -cp target/lambda-consulta-convenios-1.0.0.jar com.semillero.convenios.Handler
```

### Frontend

```bash
cd frontend-react-spa
npm install
npm run dev
# Acceder a http://localhost:3000
```
