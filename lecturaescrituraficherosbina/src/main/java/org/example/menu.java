package org.example;

import java.util.Scanner;

public class menu {

    private int opcion;

    public void mostrar() {
        System.out.println("** GESTIÓN MUSICA MP3 **");
        System.out.println(" 1. LeerInfoMP3 ");
        System.out.println(" 2. EscribirInfoMP3 ");
        System.out.println(" 3. FIN");
    }

    public int leer() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Introduzca opción: ");
            opcion = sc.nextInt();
        } while (opcion < 0 || opcion > 7);
        sc.nextLine(); //limpiar el intro
        return opcion;
    }
}


