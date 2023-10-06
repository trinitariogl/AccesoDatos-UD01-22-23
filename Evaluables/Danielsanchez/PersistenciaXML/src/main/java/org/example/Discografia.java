package org.example;


import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Discografia")
public class Discografia {
    private String nombre;
    private String cif;
    private String direccion;
    private double presupuesto;

    public Discografia() {
    }

    public Discografia(String nombre, String cif, String direccion, double presupuesto) {
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
        this.presupuesto = presupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "Discografia{" +
                "nombre='" + nombre + '\'' +
                ", cif='" + cif + '\'' +
                ", direccion='" + direccion + '\'' +
                ", presupuesto=" + presupuesto +
                '}';
    }
}
