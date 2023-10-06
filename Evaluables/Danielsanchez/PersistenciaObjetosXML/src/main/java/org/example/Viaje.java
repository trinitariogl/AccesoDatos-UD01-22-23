package org.example;

import java.time.LocalDate;
import java.util.List;

public class Viaje {
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private List<Etapa> etapa;
    private Lugar salida;

    public Viaje() {
    }

    public Viaje(LocalDate fechaSalida, LocalDate fechaLlegada, List<Etapa> etapa, Lugar salida) {
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.etapa = etapa;
        this.salida = salida;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public List<Etapa> getEtapa() {
        return etapa;
    }

    public void setEtapa(List<Etapa> etapa) {
        this.etapa = etapa;
    }

    public Lugar getSalida() {
        return salida;
    }

    public void setSalida(Lugar salida) {
        this.salida = salida;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "fechaSalida=" + fechaSalida +
                ", fechaLlegada=" + fechaLlegada +
                ", etapa=" + etapa +
                ", salida=" + salida +
                '}';
    }
}
