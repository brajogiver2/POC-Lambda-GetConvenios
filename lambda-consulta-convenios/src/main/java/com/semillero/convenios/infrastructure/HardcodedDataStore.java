package com.semillero.convenios.infrastructure;

import com.semillero.convenios.domain.Convenio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Hardcoded data source containing 20 pre-defined convenios for the POC.
 * In a production scenario this would be replaced by a database client,
 * external API integration, or a DynamoDB table.
 *
 * <p>The list is wrapped in {@code Collections.unmodifiableList} to guarantee
 * immutability at runtime.
 */
public class HardcodedDataStore {

    private static final List<Convenio> CONVENIOS = Collections.unmodifiableList(Arrays.asList(
        new Convenio("Gimnasio BodyTech",   "https://www.bodytech.com.co/convenios/empresa",       "Bogota",       "https://picsum.photos/seed/bodytech/400/300"),
        new Convenio("Cine Colombia",       "https://www.cinecolombia.com/corporativo",            "Medellin",     "https://picsum.photos/seed/cinecolombia/400/300"),
        new Convenio("SmartFit",            "https://www.smartfit.com.co/empresas",                "Cali",         "https://picsum.photos/seed/smartfit/400/300"),
        new Convenio("Almacenes Exito",     "https://www.exito.com.co/bienestar",                  "Barranquilla", "https://picsum.photos/seed/exito/400/300"),
        new Convenio("Falabella",           "https://www.falabella.com.co/convenios",              "Cartagena",    "https://picsum.photos/seed/falabella/400/300"),
        new Convenio("D1",                  "https://www.tiendasd1.com/convenios",                 "Bucaramanga",  "https://picsum.photos/seed/d1/400/300"),
        new Convenio("Colsubsidio",         "https://www.colsubsidio.com/convenios",               "Bogota",       "https://picsum.photos/seed/colsubsidio/400/300"),
        new Convenio("Compensar",           "https://www.compensar.com/convenios",                 "Bogota",       "https://picsum.photos/seed/compensar/400/300"),
        new Convenio("Cafam",               "https://www.cafam.com.co/convenios",                  "Bogota",       "https://picsum.photos/seed/cafam/400/300"),
        new Convenio("Sodexo",              "https://www.sodexo.com.co/convenios",                 "Medellin",     "https://picsum.photos/seed/sodexo/400/300"),
        new Convenio("Totto",               "https://www.totto.com/empresas",                      "Bogota",       "https://picsum.photos/seed/totto/400/300"),
        new Convenio("Arturo Calle",        "https://www.arturocalle.com/convenios",               "Cali",         "https://picsum.photos/seed/arturocalle/400/300"),
        new Convenio("Ktronix",             "https://www.ktronix.com/convenios",                   "Medellin",     "https://picsum.photos/seed/ktronix/400/300"),
        new Convenio("Alkosto",             "https://www.alkosto.com/convenios",                   "Bogota",       "https://picsum.photos/seed/alkosto/400/300"),
        new Convenio("Homecenter",          "https://www.homecenter.com.co/convenios",             "Bogota",       "https://picsum.photos/seed/homecenter/400/300"),
        new Convenio("MercadoLibre",        "https://www.mercadolibre.com.co/convenios",           "Bogota",       "https://picsum.photos/seed/mercadolibre/400/300"),
        new Convenio("Rappi",               "https://www.rappi.com/empresas",                      "Medellin",     "https://picsum.photos/seed/rappi/400/300"),
        new Convenio("Uber",                "https://www.uber.com/co/empresas",                    "Bogota",       "https://picsum.photos/seed/uber/400/300"),
        new Convenio("Avianca",             "https://www.avianca.com/convenios",                   "Bogota",       "https://picsum.photos/seed/avianca/400/300"),
        new Convenio("Decameron",           "https://www.decameron.com/convenios",                 "Cartagena",    "https://picsum.photos/seed/decameron/400/300")
    ));

    /**
     * Returns the full hardcoded list of convenios (20 items for the POC).
     *
     * @return Unmodifiable list containing all Convenio objects
     */
    public List<Convenio> findAll() {
        return CONVENIOS;
    }
}
