package org.example;

import java.util.ArrayList;
import java.util.List;

public class ListaSitios implements RepoSitios {

    protected List lst;

    public ListaSitios() {
        lst = new ArrayList<>();
        inicializa();
    }

    public Sitio elemento(int id) {
        return (Sitio) lst.get(id);
    }

    public void anyade(Sitio s) {
        lst.add(s);
    }

    public int nuevo() {
        lst.add(new Sitio());
        return lst.size()-1;
    }

    public void borrar(int id) {
        lst.remove(id);
    }

    public int tamanyo() {
        return lst.size();
    }

    public void actualiza(int id, Sitio s) {
        lst.set(id, s);
    }

    public void inicializa() {
        Coordenadas coor = new Coordenadas(5, 6);
        anyade(new Sitio("direccion", "", 1, 2, "foto", "url", "comentario", 3, coor,TipoSitio.HOTEL));

        // Crea otros sitios...

    }

}