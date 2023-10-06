package org.example;

public class Main {
    public static void main(String[] args) {
        //Menu
        int opcion;

        Menu.mostrarOpciones();
        opcion = Menu.leerOpcion();
        switch (opcion) {
            case 1:
                MP3.leerInfoMP3(MP3.leerNombreArchivo());
                break;
            case 2:
                MP3.escribirInfoMP3(MP3.leerNombreArchivo());
                break;
        }

        System.out.println("\nFIN DEL PROGRAMA");
    }
}