package com.semillero.convenios;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.semillero.convenios.domain.Convenio;
import com.semillero.convenios.infrastructure.LoggerAdapter;
import com.semillero.convenios.service.ConvenioService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * AWS Lambda entry point triggered by API Gateway (REST API proxy integration).
 * Handles HTTP GET /convenios and returns the full list of convenios as a JSON array.
 * On error, returns a structured 500 error response.
 *
 * <p>Implements {@code RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>}
 * for the standard API Gateway proxy event format.
 *
 * <p>Contains a {@code main} method with an embedded HTTP server (Java HTTP Server,
 * {@code jdk.httpserver} module) for local development and Docker Compose testing.
 * When deployed on AWS Lambda, the runtime calls {@link #handleRequest} directly.
 */
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ConvenioService convenioService;
    private final LoggerAdapter logger;
    private final Gson gson;

    /** Production constructor with real service, logger, and Gson instances. */
    public Handler() {
        this.convenioService = new ConvenioService();
        this.logger = new LoggerAdapter(Handler.class);
        this.gson = new Gson();
    }

    /**
     * Test-friendly constructor with injectable dependencies for unit testing.
     *
     * @param convenioService Mock or real convenio service
     * @param logger          Mock or real logger adapter
     * @param gson            Gson instance for JSON serialization
     */
    public Handler(ConvenioService convenioService, LoggerAdapter logger, Gson gson) {
        this.convenioService = convenioService;
        this.logger = logger;
        this.gson = gson;
    }

    /**
     * Main Lambda handler invoked by API Gateway on HTTP requests to /convenios.
     * Delegates to ConvenioService, serializes the result as JSON, and returns
     * an APIGatewayProxyResponse with CORS headers.
     *
     * @param event   API Gateway proxy request containing HTTP method, path, headers, query params
     * @param context Lambda execution context (provides metadata like function name, request ID)
     * @return APIGatewayProxyResponseEvent with status 200 and JSON body on success,
     *         or status 500 with a structured error payload on failure
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        logger.info("Solicitud recibida: metodo=" + event.getHttpMethod() + ", path=" + event.getPath());

        try {
            List<Convenio> convenios = convenioService.obtenerConvenios();
            String body = gson.toJson(convenios);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");  // CORS: allow any origin for the POC

            logger.info("Respuesta exitosa: " + convenios.size() + " convenios en body");

            return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(headers)
                .withBody(body);

        } catch (Exception e) {
            logger.error("Error interno al procesar la solicitud", e);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");

            Map<String, Object> errorPayload = new HashMap<>();
            errorPayload.put("codigo", 500);
            errorPayload.put("mensaje", "Ocurrio un error al procesar la solicitud.");
            String errorBody = gson.toJson(errorPayload);

            return new APIGatewayProxyResponseEvent()
                .withStatusCode(500)
                .withHeaders(headers)
                .withBody(errorBody);
        }
    }

    /**
     * Embedded HTTP server entry point for local development and Docker Compose testing.
     * Starts a lightweight HTTP server on port 8080 (configurable via PORT env var)
     * that bridges HTTP requests to the Lambda handler via {@link #handleRequest}.
     *
     * <p>When deployed on AWS Lambda, this method is ignored; the Lambda runtime
     * calls {@code handleRequest} directly through the Java runtime interface.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if the HTTP server socket cannot be bound
     */
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        Handler handler = new Handler();
        LoggerAdapter log = new LoggerAdapter(Handler.class);
        Gson gson = new Gson();

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/convenios", exchange -> {
            try {
                APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
                event.setHttpMethod(exchange.getRequestMethod());
                event.setPath(exchange.getRequestURI().getPath());
                Map<String, String> eventHeaders = new HashMap<>();
                exchange.getRequestHeaders().forEach((k, v) -> eventHeaders.put(k, String.join(",", v)));
                event.setHeaders(eventHeaders);

                byte[] reqBody = exchange.getRequestBody().readAllBytes();
                if (reqBody.length > 0) {
                    event.setBody(new String(reqBody, StandardCharsets.UTF_8));
                }

                String query = exchange.getRequestURI().getQuery();
                if (query != null) {
                    Map<String, String> queryParams = new HashMap<>();
                    for (String param : query.split("&")) {
                        String[] kv = param.split("=", 2);
                        queryParams.put(kv[0], kv.length > 1 ? kv[1] : "");
                    }
                    event.setQueryStringParameters(queryParams);
                }

                APIGatewayProxyResponseEvent response = handler.handleRequest(event, null);

                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                byte[] respBytes = response.getBody().getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(response.getStatusCode(), respBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(respBytes);
                }
            } catch (Exception e) {
                log.error("Error en servidor HTTP local", e);
                String errorBody = gson.toJson(Map.of("codigo", 500, "mensaje", "Error interno del servidor"));
                byte[] respBytes = errorBody.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(500, respBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(respBytes);
                }
            } finally {
                exchange.close();
            }
        });

        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();
        log.info("Lambda handler HTTP server iniciado en puerto " + port);
    }
}
