package org.example;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {


        //para crear el file con las rutas absolutas
        try {

            File rutaFileMusica = new File("src/main/resources/rutaFileMusica.txt");
            if (rutaFileMusica.exists()) {
                System.out.println("El file ya existe");
            } else {
                rutaFileMusica.createNewFile();
            }

            File rutaMP3 = new File("src/main/resources/musica");


            if (rutaMP3.exists() && rutaMP3.isDirectory()) {
                File[] listaRuta = rutaMP3.listFiles();

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaFileMusica));

                for (File file : listaRuta) {
                    String rutaAbsoluta = file.getAbsolutePath();
                    bufferedWriter.write(rutaAbsoluta + " ");
                }
                bufferedWriter.close();
            }

            String[] array = null;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaFileMusica));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                array = linea.split(" ");
            }

            System.out.println("A CONTINUACION ESTAN LAS RUTAS ABSOLUTAS PARA EL EJERCICIO.");
            // para obtener la ruta
            for (String s : array) {
                System.out.println(s);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        menu();


    }

    public static void menu() throws IOException {

        Scanner sc = new Scanner(System.in);
        String rutaFile;
        int opcion = 0;
        boolean repetir = false;

        do {
            System.out.println("** GESTION DE MUSICA MP3 **");
            System.out.println("1. Leer Info. MP3");
            System.out.println("2. Escribir info. MP·");
            System.out.println("3. Salir");


            do {
                System.out.println("Elige una opcion: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Debe introducir un numero");
                } else {
                    opcion = sc.nextInt();
                    if (opcion > 3 || opcion < 1) {
                        System.out.println("Debe introducir un numnero entre 1 y 3.");
                        repetir = true;
                    } else {
                        repetir = false;
                    }
                }
            } while (repetir == true);

            switch (opcion) {
                case 1:
                    System.out.println("Introduce el fichero donde se almacena la musica: ");
                    rutaFile = sc.next();
                    MP3.LeerInfoMP3(rutaFile);
                    break;
                case 2:
                    System.out.println("Introduce el fichero donde se almacena la música: ");
                    rutaFile = sc.next();
                    MP3.EscribirInfoMP(rutaFile);
                    break;
                case 3:
                    System.out.println("Salir.");
                    break;

            }
        } while (opcion!=3);
    }

}
