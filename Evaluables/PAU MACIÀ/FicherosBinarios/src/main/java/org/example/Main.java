package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
    static Scanner sc = new Scanner(System.in);
//En el método main llamamos al menú y desde ahí ya se ejecuta todo
    public static void main(String[] args) {
        menu();
    }
//Nos redirige al otro método
    public static void menu() {
        int eleccion = 0;
        do {
            System.out.println("1. Leer info. MP3");
            System.out.println("2. Escribir info. MP3");
            System.out.println("3. Salir");
            System.out.println("\nElige una opción: ");
            eleccion = sc.nextInt();
            if (eleccion == 1) {
                leerFichero(eleccion);
            } else if (eleccion == 2) {
                leerFichero(eleccion);
            } else if (eleccion == 3) {
                return;
            }
        } while (eleccion != 3);
    }
//Leemos el txt donde están los directorios y según el valor de N llamamos a un método de la clase u otro
    public static void leerFichero(int n) {
        int cont = 0;
        sc.nextLine();
        String directorio = "";
        System.out.println("Indica la ruta del .txt: ");
        directorio = sc.nextLine();
        try (BufferedReader lectura = new BufferedReader(new FileReader(directorio))) {
            String linea = null;
            do {
                linea = lectura.readLine();
                if (linea != null) {
                    if (n == 1) {
                        MP3.leerInfo(linea);
                    } else {
                        cont++;
                        System.out.println("Canción " + cont);
                        MP3.escribirInfo(linea);
                    }
                }
            } while (linea != null);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró en la ruta especificada.");
        } catch (IOException e) {
            System.out.println("Error de lectura/escritura en el archivo.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada incorrecta. Se esperaba un número.");
        }
    }
}