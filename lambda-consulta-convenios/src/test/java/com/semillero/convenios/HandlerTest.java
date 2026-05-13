package com.semillero.convenios;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.semillero.convenios.domain.Convenio;
import com.semillero.convenios.infrastructure.LoggerAdapter;
import com.semillero.convenios.service.ConvenioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HandlerTest {

    @Mock
    private ConvenioService convenioService;

    @Mock
    private LoggerAdapter logger;

    private final Gson gson = new Gson();

    @Test
    void handleRequest_deberiaRetornar200ConBody() {
        List<Convenio> mockConvenios = List.of(
            new Convenio("BodyTech", "https://bodytech.com", "Bogota", "https://picsum.photos/seed/bt/400/300")
        );
        when(convenioService.obtenerConvenios()).thenReturn(mockConvenios);

        Handler handler = new Handler(convenioService, logger, gson);
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("GET");
        request.setPath("/convenios");

        APIGatewayProxyResponseEvent response = handler.handleRequest(request, null);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().contains("BodyTech"));
        assertTrue(response.getBody().contains("Bogota"));
        assertTrue(response.getHeaders().containsKey("Content-Type"));
        assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Origin"));
    }

    @Test
    void handleRequest_cuandoFallaelServicio_deberiaRetornar500() {
        when(convenioService.obtenerConvenios()).thenThrow(new RuntimeException("Error interno simulado"));

        Handler handler = new Handler(convenioService, logger, gson);
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("GET");
        request.setPath("/convenios");

        APIGatewayProxyResponseEvent response = handler.handleRequest(request, null);

        assertEquals(500, response.getStatusCode());
        assertTrue(response.getBody().contains("codigo"));
        assertTrue(response.getBody().contains("mensaje"));
    }
}
