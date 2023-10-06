package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static int leerOpcion () {
        int opcion = Leerentero();
        return opcion;

    }

    public static void mostrarOpciones () {
        System.out.println(" GESTIÓN MUSICA MP3");
        System.out.println("1. Insertar Película");
        System.out.println("2. Modificar Película");
        System.out.println("3. Eliminar Película");
        System.out.println("4. Visualizar Película");
        System.out.println("5. Salir");
    }
    public static int Leerentero(){
        int opcion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Opcion: ");
            try {
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Introduce un numero del 1 al 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero.");
                sc.nextLine();
                opcion = 0;
            }
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }
}