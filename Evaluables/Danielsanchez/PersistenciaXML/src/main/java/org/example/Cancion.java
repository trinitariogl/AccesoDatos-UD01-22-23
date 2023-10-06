package org.example;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("cancion")

public class Cancion {
    private int numero;
    private String nombre;
            private String autor;
            private int  duracion;
            private int anyo;
            private boolean single;
            private  Discografia discografia;
            @XStreamImplicit
            private List<String> paises;
    public Cancion() {
    }

    public Cancion(int numero, String nombre, String autor, int duracion, int anyo, boolean single, Discografia discografia) {
        this.numero = numero;
        this.nombre = nombre;
        this.autor = autor;
        this.duracion = duracion;
        this.anyo = anyo;
        this.single = single;
        this.discografia = discografia;
        this.paises= new ArrayList<>();
    }

    public List<String> getPaises() {
        return paises;
    }

    public void setPaises(List<String> paises) {
        this.paises = paises;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public Discografia getDiscografia() {
        return discografia;
    }

    public void setDiscografia(Discografia discografia) {
        this.discografia = discografia;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", duracion=" + duracion +
                ", anyo=" + anyo +
                ", single=" + single +
                ", discografia=" + discografia +
                ", pais" + paises+
                '}';
    }
}
