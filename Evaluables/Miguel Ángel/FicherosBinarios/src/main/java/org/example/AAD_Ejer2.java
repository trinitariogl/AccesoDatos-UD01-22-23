package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AAD_Ejer2 {
    static int opcion = 0;
    static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) throws FileNotFoundException {
        do{
            try{
                System.out.println("\n** GESTIÓN MÚSICA MP3 **");
                System.out.println("\n1. Leer Info. MP3");
                System.out.println("2. Escribir info. MP3");
                System.out.println("3. Salir");
                System.out.print("Opción: ");

                opcion = sc.nextInt();

                while (opcion < 1 || opcion > 3) {
                    System.out.println("\nElige un número del 1 al 3");
                    System.out.print("Opción: ");
                    opcion = sc.nextInt();
                }

                sc.nextLine();

                switch (opcion) {
                    case 1:
                        String nombreFichero = "";
                        do{
                            System.out.println("\nNombre del fichero que deseas mostrar su información: ");
                            System.out.print("Nombre: ");
                            nombreFichero = sc.nextLine();

                                if (nombreFichero.isEmpty() || nombreFichero.isBlank()) {
                                    System.err.println("El nombre del fichero no puede estar en blanco");
                                }

                        } while (nombreFichero.isBlank() || nombreFichero.isEmpty());

                        MP3.LeerInfoMP3(nombreFichero);

                        break;
                    case 2:
                        
                        String ficheroNombre = "";
                        do{
                            System.out.println("\nNombre del fichero que deseas mostrar su información: ");
                            System.out.print("Nombre: ");
                            ficheroNombre = sc.nextLine();

                            if (ficheroNombre.isEmpty() || ficheroNombre.isBlank()) {
                                System.err.println("El nombre del fichero no puede estar en blanco");
                            }

                        } while (ficheroNombre.isBlank() || ficheroNombre.isEmpty());

                        break;
                    case 3:
                        System.out.println("Fin del programa");
                        break;
                }

            } catch (InputMismatchException e2) {
                System.err.println("El valor debe ser númerico\n");
                sc.next();
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }

        } while(opcion != 3);
    }
}
