package org.example;
import java.io.*;
import java.awt.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {



        menu menu = new menu();
        int opcion;
        do {
            menu.mostrar();
            opcion = menu.leer();
            String nombreArchivo;
            System.out.print ("Introduce la ruta del archivo: ");
            nombreArchivo=sc.nextLine();
            switch (opcion) {
                case 1:
                   mp3.LeerInfoMP3(nombreArchivo);
                    break;
                case 2:
                    mp3.EscribirInfoMP3(nombreArchivo);
                    break;
                case 3:
                    System.out.println("fin del programa");
                    break;
            }
        } while (opcion != 0);
    }



}