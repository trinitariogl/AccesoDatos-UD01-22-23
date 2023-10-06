package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Coordenadas coor = new Coordenadas(5, 6);

        Sitio sitio = new Sitio("direccion", "", 1, 2, "foto", "url", "comentario", 3, coor,TipoSitio.HOTEL);
        System.out.println(sitio);

        RepoSitios sitios = new ListaSitios();

        for (int i=0; i<sitios.tamanyo(); i++) {
            System.out.println(sitios.elemento(i).toString());
        }
    }
}