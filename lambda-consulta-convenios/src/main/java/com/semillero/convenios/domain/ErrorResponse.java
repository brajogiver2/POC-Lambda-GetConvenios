package com.semillero.convenios.domain;

/**
 * Standard error response model returned to the HTTP client when a request fails.
 * Contains an HTTP status code and a human-readable error description.
 */
public class ErrorResponse {

    private int codigo;
    private String mensaje;

    /** Default no-arg constructor required for JSON deserialization frameworks. */
    public ErrorResponse() {
    }

    /**
     * Full constructor for error response payload.
     *
     * @param codigo  HTTP status code indicating the error type (e.g. 500)
     * @param mensaje Human-readable description of the error for the end user
     */
    public ErrorResponse(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    /** @return HTTP status code */
    public int getCodigo() {
        return codigo;
    }

    /** @param codigo HTTP status code */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /** @return Human-readable error description */
    public String getMensaje() {
        return mensaje;
    }

    /** @param mensaje Human-readable error description */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
