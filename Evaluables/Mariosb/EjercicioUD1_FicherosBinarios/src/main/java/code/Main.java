package code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            opcion = menu();

            String nombreFichero = "";
            switch (opcion) {
                case 1:
                    do{
                        System.out.print("Nombre del fichero: ");
                        nombreFichero = sc.nextLine();

                        if (nombreFichero.isEmpty() || nombreFichero.isBlank()) {
                            System.err.println("El nombre del fichero no puede estar en blanco");
                        }

                    } while (nombreFichero.isBlank() || nombreFichero.isEmpty());

                    MP3.LeerInfoMP3(nombreFichero);
                    break;
                case 2:
                    do{
                        System.out.print("Nombre del fichero: ");
                        nombreFichero = sc.nextLine();

                        if (nombreFichero.isEmpty() || nombreFichero.isBlank()) {
                            System.err.println("El nombre del fichero no puede estar en blanco");
                        }

                    } while (nombreFichero.isBlank() || nombreFichero.isEmpty());

                    MP3.escribirInfoMP3(nombreFichero);
                case 3:
                    break;
                default:
                    break;
            }
        } while (opcion != 3);
    }

    public static int menu() {
        String elige = "Elige: ";

        System.out.println("\n** GESTIÓN MÚSICA MP3 **\n");
        System.out.println("1. Leer Información");
        System.out.println("2. Escribir Información");
        System.out.println("3. Salir\n");
        System.out.print(elige);

        int opcion = 0;
        while (true) {
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion >= 1 && opcion <= 3) {
                    System.out.println();
                    break;
                } else {
                    System.err.println("ERROR. Introduce un número entre el 1-3.");
                    System.out.println();
                    System.out.print(elige);
                    continue;
                }
            } catch (InputMismatchException ime) {
                System.err.println("ERROR. Introduce solo un número.");
                System.out.println();
                System.out.print(elige);
                sc.nextLine();
            }
        }

        return opcion;
    }
}