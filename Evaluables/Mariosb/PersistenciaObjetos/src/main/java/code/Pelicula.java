package code;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Pelicula implements Serializable {
    private String titulo;
    private List<String> actores;
    private List<String> directores;
    private LocalDate fechaSalida;
    private String formato;

    public Pelicula(String titulo, List<String> actores, List<String> directores, LocalDate fechaSalida, String formato) {
        this.titulo = titulo;
        this.actores = actores;
        this.directores = directores;
        this.fechaSalida = fechaSalida;
        this.formato = formato;
    }

    public Pelicula() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getActores() {
        return actores;
    }

    public void setActores(List<String> actores) {
        this.actores = actores;
    }

    public List<String> getDirectores() {
        return directores;
    }

    public void setDirectores(List<String> directores) {
        this.directores = directores;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String visualizar() {
        return "\nPelicula: " +
                "\nTitulo -> " + titulo +
                "\nActores -> " + actores +
                "\nDirectores -> " + directores +
                "\nFechaSalida -> " + fechaSalida +
                "\nFormato -> " + formato;
    }
}
