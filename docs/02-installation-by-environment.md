# 02 - Instalacion por Entorno

## Entorno de desarrollo local

### Prerrequisitos

```bash
# Verificar Docker
docker --version

# Verificar Docker Compose
docker compose version

# Verificar Node.js (para frontend)
node --version   # >= 18

# Verificar Java (para backend)
java --version   # >= 11

# Verificar Maven (para backend)
mvn --version    # >= 3.9
```

### Instalacion

```bash
git clone <repo-url> ProyectoSemillero
cd ProyectoSemillero
docker compose up --build
```

## Entorno AWS (produccion)

Los modulos estan disenados para desplegarse en AWS:

1. **Frontend**: Build de Vite -> subir carpeta `dist/` a S3 -> configurar CloudFront.
2. **Backend**: `mvn package` -> subir uber-jar a Lambda -> configurar API Gateway como trigger.
3. **Logs**: Se generan automaticamente en CloudWatch Logs.

### Despliegue manual del backend

```bash
cd lambda-consulta-convenios
mvn clean package
# Subir target/lambda-consulta-convenios-1.0.0.jar a AWS Lambda
# Configurar API Gateway como trigger HTTP (proxy)
```

### Despliegue manual del frontend

```bash
cd frontend-react-spa
npm install
npm run build
# Subir contenido de dist/ a bucket S3
# Configurar bucket para static hosting o usar CloudFront
# Configurar VITE_API_URL con la URL de API Gateway
```
