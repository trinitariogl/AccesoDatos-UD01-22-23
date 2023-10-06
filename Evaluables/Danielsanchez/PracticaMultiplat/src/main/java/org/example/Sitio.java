package org.example;

public class Sitio {
    private String nombre;
    private String direccion;
    private int telefono;
    private long fecha; // Milisegundos desde 1970.
    private String foto;
    private String url;
    private String comentario;
    private float valoracion;
    private Coordenadas posicion;
    private TipoSitio tipo;

    public Sitio(String nombre, String direccion, int telefono, long fecha, String foto, String url, String comentario, float valoracion, Coordenadas posicion, TipoSitio tipo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha = fecha;
        this.foto = foto;
        this.url = url;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.posicion = posicion;
        this.tipo = tipo;
    }

    public Sitio() {
        this.fecha =System.currentTimeMillis();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public Coordenadas getPosicion() {
        return posicion;
    }

    public void setPosicion(Coordenadas posicion) {
        this.posicion = posicion;
    }

    public TipoSitio getTipo() {
        return tipo;
    }

    public void setTipo(TipoSitio tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Sitio{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                ", fecha=" + fecha +
                ", foto='" + foto + '\'' +
                ", url='" + url + '\'' +
                ", comentario='" + comentario + '\'' +
                ", valoracion=" + valoracion +
                ", posicion=" + posicion +
                ", tipo=" + tipo +
                '}';
    }
}
