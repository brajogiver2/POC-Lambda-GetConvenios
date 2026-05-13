# 04 - Servicios Backend

## Lambda Consulta Convenios

Funcion AWS Lambda que expone un endpoint REST para consultar la lista de convenios.

### Endpoint

| Metodo | Path         | Descripcion                           |
|--------|--------------|---------------------------------------|
| GET    | /convenios   | Retorna un arreglo JSON de convenios  |

### Formato de respuesta exitosa (200)

```json
[
  {
    "nombre": "Gimnasio BodyTech",
    "url": "https://www.bodytech.com.co/convenios/empresa",
    "ciudad": "Bogota",
    "imagen": "https://picsum.photos/seed/bodytech/400/300"
  }
]
```

### Formato de respuesta de error (500)

```json
{
  "codigo": 500,
  "mensaje": "Ocurrio un error al procesar la solicitud."
}
```

### Logs

La funcion utiliza `java.util.logging` para registrar eventos. En AWS Lambda,
estos logs se envian automaticamente a **CloudWatch Logs** bajo el grupo
`/aws/lambda/<function-name>`.

### Datos

Para fines de la POC, la funcion contiene 20 registros hardcodeados.
En un entorno productivo, los datos provendrian de una base de datos o API externa.

### Documentacion OpenAPI

La especificacion OpenAPI 3.0.3 se encuentra en:
`lambda-consulta-convenios/openapi/openapi.yaml`
