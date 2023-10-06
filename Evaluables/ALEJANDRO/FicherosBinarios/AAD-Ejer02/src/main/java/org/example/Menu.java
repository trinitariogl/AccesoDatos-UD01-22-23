package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static int leerOpcion () {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.print("Opcion: ");
            try {
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Introduce un numero del 1 al 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero.");
                sc.nextLine();
                opcion = 0;
            }
        } while (opcion < 1 || opcion > 3);
        return opcion;
    }

    public static void mostrarOpciones () {
        System.out.println("\n**GESTIÓN MUSICA MP3**");
        System.out.println("1. Leer Info. MP3");
        System.out.println("2. Escribir Info. MP3");
        System.out.println("3. Salir");
    }
}
