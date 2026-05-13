package com.semillero.convenios.service;

import com.semillero.convenios.domain.Convenio;
import com.semillero.convenios.infrastructure.HardcodedDataStore;
import com.semillero.convenios.infrastructure.LoggerAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConvenioServiceTest {

    @Mock
    private HardcodedDataStore dataStore;

    @Mock
    private LoggerAdapter logger;

    @Test
    void obtenerConvenios_deberiaRetornarListaDesdeDataStore() {
        Convenio expected = new Convenio("Test", "https://test.com", "Bogota", "https://test.com/img.jpg");
        when(dataStore.findAll()).thenReturn(List.of(expected));

        ConvenioService service = new ConvenioService(dataStore, logger);
        List<Convenio> result = service.obtenerConvenios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getNombre());
        assertEquals("Bogota", result.get(0).getCiudad());

        verify(logger, times(2)).info(anyString());
    }

    @Test
    void obtenerConvenios_usandoDataStoreReal_deberiaRetornar20() {
        ConvenioService service = new ConvenioService();
        List<Convenio> result = service.obtenerConvenios();

        assertNotNull(result);
        assertEquals(20, result.size(), "Deben existir exactamente 20 convenios hardcodeados");
    }
}
