package org.example;

public enum TipoSitio {
    OTROS ("Otros", 0),
    RESTAURANTE ("Restaurante", 1),
    HOTEL ("Hotel", 2),
    ESPECTACULO ("Espectáculo", 3),
    NATURALEZA ("Naturaleza", 4);

    private final String texto;
    private final int recurso;

    TipoSitio(String texto, int recurso) {
        this.texto = texto;
        this.recurso = recurso;
    }

    public String getTexto() {return texto;}
    public int getRecurso() {return recurso;}
    }