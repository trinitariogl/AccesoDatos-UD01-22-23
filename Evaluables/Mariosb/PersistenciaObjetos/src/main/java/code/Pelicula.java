package code;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pelicula implements Serializable {
    private String titulo;
    private ArrayList<String> actores;
    private ArrayList<String> directores;
    private LocalDate fechaSalida;
    private String formato;

    public Pelicula(String titulo, ArrayList<String> actores, ArrayList<String> directores, LocalDate fechaSalida, String formato) {
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

    public ArrayList<String> getActores() {
        return actores;
    }

    public void setActores(ArrayList<String> actores) {
        this.actores = actores;
    }

    public ArrayList<String> getDirectores() {
        return directores;
    }

    public void setDirectores(ArrayList<String> directores) {
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

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", actores=" + actores +
                ", directores=" + directores +
                ", fechaSalida=" + fechaSalida +
                ", formato='" + formato + '\'' +
                '}';
    }
}
