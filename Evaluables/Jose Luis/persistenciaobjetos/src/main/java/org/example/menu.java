package org.example;

import java.util.Scanner;

public class menu {

    private int opcion;

    public void mostrar() {
        System.out.println("** MI VIDEOCLUB **");
        System.out.println(" 1. Insertar Película ");
        System.out.println(" 2. Modificar Pelicula ");
        System.out.println(" 3. Eliminar Pelicula ");
        System.out.println(" 4. Visualizar Pelicula");
        System.out.println(" 5. FIN");
    }

    public int leer() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Introduzca opción: ");
            opcion = sc.nextInt();
        } while (opcion < 0 || opcion > 5);
        sc.nextLine(); //limpiar el intro
        return opcion;
    }
}


