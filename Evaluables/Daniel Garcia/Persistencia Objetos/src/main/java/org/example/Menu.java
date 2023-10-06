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
        System.out.println("\nMenú:");
        System.out.println("1. Insertar Película");
        System.out.println("2. Modificar Película");
        System.out.println("3. Eliminar Película");
        System.out.println("4. Visualizar Película");
        System.out.println("5. Visualizar Todas las Películas");
        System.out.println("6. Salir");

    }

    public int leerOpcion() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > 6);
        sc.nextLine();
        return opcion;
    }
}