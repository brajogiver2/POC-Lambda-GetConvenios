# 05 - Docker Compose

## Requisitos

- Docker Engine 20.10+
- Docker Compose 2.20+

### Instalacion de Docker

```bash
# macOS
brew install --cask docker

# Linux (Ubuntu/Debian)
sudo apt update && sudo apt install docker.io docker-compose-v2

# Verificar instalacion
docker --version
docker compose version
```

## Archivo docker-compose.yml

Ubicacion: `./docker-compose.yml`

### Servicios

| Servicio                   | Puerto  | Descripcion                           |
|----------------------------|---------|---------------------------------------|
| lambda-consulta-convenios  | 9000    | Backend Lambda con API de convenios   |
| frontend-react-spa         | 8080    | Frontend React servido via nginx      |

### Comandos esenciales

```bash
# Construir e iniciar todos los servicios
docker compose up --build

# Iniciar en segundo plano (detached)
docker compose up --build -d

# Ver logs en tiempo real
docker compose logs -f

# Detener servicios
docker compose down

# Detener y eliminar volumenes
docker compose down -v

# Reconstruir un servicio especifico
docker compose build lambda-consulta-convenios

# Ver estado de los servicios
docker compose ps
```

## Variables de entorno

| Variable         | Servicio                | Descripcion                          | Default               |
|------------------|-------------------------|--------------------------------------|-----------------------|
| JAVA_TOOL_OPTIONS| lambda-consulta-convenios| Opciones JVM                         | -DLOG_LEVEL=INFO      |
| VITE_API_URL     | frontend-react-spa      | URL del API backend para el frontend | http://localhost:9000  |

## Verificacion del stack

```bash
# 1. Iniciar servicios
docker compose up --build -d

# 2. Verificar que ambos esten corriendo
docker compose ps

# 3. Probar API
curl http://localhost:9000/convenios

# 4. Abrir frontend en navegador
open http://localhost:8080

# 5. Ver logs
docker compose logs -f
```
