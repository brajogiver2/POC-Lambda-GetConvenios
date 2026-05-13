package com.semillero.convenios.domain;

import java.util.Objects;

/**
 * Domain model representing a convenio (agreement/benefit).
 * Contains display information including name, website URL, applicable city, and an image thumbnail URL.
 */
public class Convenio {

    private String nombre;
    private String url;
    private String ciudad;
    private String imagen;

    /** Default no-arg constructor required for JSON deserialization frameworks. */
    public Convenio() {
    }

    /**
     * Full constructor for creating a Convenio instance with all fields.
     *
     * @param nombre  Display name of the convenio (e.g. "Gimnasio BodyTech")
     * @param url     Website URL with benefit details or enrollment information
     * @param ciudad  Primary city where this convenio applies
     * @param imagen  URL of the representative image for the card thumbnail
     */
    public Convenio(String nombre, String url, String ciudad, String imagen) {
        this.nombre = nombre;
        this.url = url;
        this.ciudad = ciudad;
        this.imagen = imagen;
    }

    /** @return Display name of the convenio */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre Display name of the convenio */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return Website URL with benefit details */
    public String getUrl() {
        return url;
    }

    /** @param url Website URL with benefit details */
    public void setUrl(String url) {
        this.url = url;
    }

    /** @return Primary applicable city */
    public String getCiudad() {
        return ciudad;
    }

    /** @param ciudad Primary applicable city */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /** @return URL of the representative image */
    public String getImagen() {
        return imagen;
    }

    /** @param imagen URL of the representative image */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Compares this Convenio to another object based on all four fields.
     *
     * @param o Object to compare against
     * @return true if all fields match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convenio convenio = (Convenio) o;
        return Objects.equals(nombre, convenio.nombre)
            && Objects.equals(url, convenio.url)
            && Objects.equals(ciudad, convenio.ciudad)
            && Objects.equals(imagen, convenio.imagen);
    }

    /**
     * Generates a hash code using all four fields for consistent hashing.
     *
     * @return Hash code based on nombre, url, ciudad, and imagen
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, url, ciudad, imagen);
    }
}
