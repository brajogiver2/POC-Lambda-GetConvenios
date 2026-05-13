# 03 - Estrategia de Pruebas

## Backend (Lambda Java)

Framework: JUnit 5 + Mockito

### Pruebas unitarias

| Archivo                       | Escenarios                                        |
|-------------------------------|---------------------------------------------------|
| ConvenioServiceTest.java      | Retorno desde dataStore mock, retorno real (20)   |
| HandlerTest.java              | HTTP 200 con body correcto, HTTP 500 en error     |

### Ejecucion

```bash
cd lambda-consulta-convenios
mvn test
```

## Frontend (React TypeScript)

Framework: Vitest + Testing Library

### Pruebas unitarias

| Archivo                  | Escenarios                                  |
|--------------------------|---------------------------------------------|
| apiClient.test.ts        | Fetch exitoso, error HTTP, error de red     |
| ConveniosList.test.tsx   | Loading, renderizado, error                 |

### Ejecucion

```bash
cd frontend-react-spa
npm test           # Una vez
npm run test       # Modo watch
npm run test:coverage  # Con reporte de cobertura
```

## Cobertura de codigo

- Backend: se recomienda integrar jacoco-maven-plugin para reporte.
- Frontend: Vitest genera reporte con `--coverage`.

Ejecutar las pruebas completas del stack:

```bash
cd lambda-consulta-convenios && mvn test && cd ../frontend-react-spa && npm test
```
