package org.example;

import java.util.Scanner;

public class Menu {
    private int opcion;

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public void mostrar() {
        System.out.println("** GESTIÓN MUSICA MP3 **");
        System.out.println();
        System.out.println("1. Leer Info. MP3");
        System.out.println("2. Escribir info. MP3");
        System.out.println("3. Salir");

    }

    public int leerOpcion() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > 3);
        sc.nextLine();
        return opcion;
    }
}