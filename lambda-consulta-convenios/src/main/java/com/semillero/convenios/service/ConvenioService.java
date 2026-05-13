package com.semillero.convenios.service;

import com.semillero.convenios.domain.Convenio;
import com.semillero.convenios.infrastructure.HardcodedDataStore;
import com.semillero.convenios.infrastructure.LoggerAdapter;

import java.util.List;

/**
 * Service layer for convenio operations.
 * Follows Single Responsibility Principle: only handles business logic for retrieving
 * the convenio list. Delegates data access to {@link HardcodedDataStore}.
 */
public class ConvenioService {

    private final HardcodedDataStore dataStore;
    private final LoggerAdapter logger;

    /** Default constructor wired with production dependencies. */
    public ConvenioService() {
        this.dataStore = new HardcodedDataStore();
        this.logger = new LoggerAdapter(ConvenioService.class);
    }

    /**
     * Constructor with explicit dependency injection for testability.
     *
     * @param dataStore Data source implementation (mock or real)
     * @param logger    Logger adapter for CloudWatch audit trail
     */
    public ConvenioService(HardcodedDataStore dataStore, LoggerAdapter logger) {
        this.dataStore = dataStore;
        this.logger = logger;
    }

    /**
     * Retrieves the full list of available convenios.
     * Delegates to {@code HardcodedDataStore.findAll()} and logs the operation
     * count for CloudWatch audit/monitoring purposes.
     *
     * @return List of all available Convenio objects (20 items for the current POC)
     */
    public List<Convenio> obtenerConvenios() {
        logger.info("Iniciando consulta de lista de convenios");
        List<Convenio> result = dataStore.findAll();
        logger.info("Consulta completada: " + result.size() + " convenios recuperados");
        return result;
    }
}
