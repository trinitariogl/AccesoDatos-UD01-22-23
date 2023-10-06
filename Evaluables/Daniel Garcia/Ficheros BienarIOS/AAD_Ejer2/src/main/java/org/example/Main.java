package org.example;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Menu menu = new Menu();

        int opcion;
        String nombreArchivo;

        do {

            menu.mostrar();

            opcion = menu.leerOpcion();

            System.out.print("Introduce el nombre fichero (txt) donde se almacena la música: ");
            nombreArchivo = sc.nextLine();

            switch (opcion){
                case 1:
                    MP3.LeerInfoMP3(nombreArchivo);
                    break;
                case 2:
                    MP3.EscribirMP3(nombreArchivo);
                    break;
                case 3:

                    break;
            }

        } while (opcion != 3);

    }
}