package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MP3 {
    public static Scanner sc = new Scanner(System.in);

    public static void leerInfoMP3(String nombreArchivo) {

        //Para leer el archivo con las canciones
        try (BufferedReader lectura = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = null;
            String titulo = null, artista = null, album = null, pista = null, anyo = null, comentario = null;
            int genero = 0;
            do {
                linea = lectura.readLine();

                if (linea != null && !linea.isBlank() && !linea.isEmpty()) {

                    //Esta parte del codigo se encarga de buscar los ultimos 128 bytes
                    File archivoMP3 = new File(linea);
                    try (RandomAccessFile raf = new RandomAccessFile(archivoMP3, "r")) {
                        long tamanyo = raf.length();
                        int posicion = (int) tamanyo - 128;

                        raf.seek(posicion);
                        byte[] bytes = null;

                        //cabecera
                        bytes = new byte[3];
                        raf.read(bytes);

                        //titulo
                        bytes = new byte[30];
                        raf.read(bytes);
                        titulo = new String(bytes);

                        //artista
                        bytes = new byte[30];
                        raf.read(bytes);
                        artista = new String(bytes);


                        //album
                        bytes = new byte[30];
                        raf.read(bytes);
                        album = new String(bytes);

                        //anyo
                        bytes = new byte[4];
                        raf.read(bytes);
                        anyo = new String(bytes);


                        //comentario
                        bytes = new byte[28];
                        raf.read(bytes);
                        comentario = new String(bytes);

                        //numero pista
                        bytes = new byte[1];
                        raf.read(bytes);
                        pista = new String(bytes);

                        //genero
                        genero = raf.read();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("-------------------------");
                    System.out.println("Titulo: " + titulo);
                    System.out.println("Artista: " + artista);
                    System.out.println("Album: " + album);
                    System.out.println("Pista numero: " + pista);
                    System.out.println("Año: " + anyo);
                    System.out.println("Comentario: " + comentario);
                    System.out.println("Genero: " + Generos.values()[genero] + "\n");


                }
            } while (linea != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirInfoMP3(String nombreArchivo) {

        //Para leer el archivo con las canciones
        try (BufferedReader lectura = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = null;

            do {
                linea = lectura.readLine();
                String titulo;

                if (linea != null && !linea.isBlank() && !linea.isEmpty()) {

                    //Esta parte del codigo se encarga de buscar los ultimos 128 bytes
                    File archivoMP3 = new File(linea);
                    try (RandomAccessFile raf = new RandomAccessFile(archivoMP3, "rw")) {
                        long tamanyo = raf.length();
                        int posicion = (int) tamanyo - 128;

                        raf.seek(posicion);
                        byte[] bytes;
                        //cabecera
                        bytes = new byte[3];
                        raf.read(bytes);
                        System.out.println("----------------------------");
                        //titulo
                        bytes = new byte[30];
                        raf.read(bytes);
                        titulo = new String(bytes, "UTF-8");
                        System.out.println("Titulo: " + titulo);

                        bytes = new byte[60];
                        raf.read(bytes);
                        //anyo
                        raf.writeBytes(leerAnyo());
                        raf.writeBytes(leerComentario());
                        raf.writeBytes(leerPistas());
                        raf.writeByte(leerGenero());


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } while (linea != null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String leerAnyo() {
        int anyo = 0;
        do {
            System.out.print("Año: ");
            try {
                anyo = sc.nextInt();
                if (anyo < 0 || anyo > 2023) {
                    System.out.println("Solo numeros entre 0 y 2023");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Solo numeros");
                anyo = 0;
            }
        } while (anyo < 0 || anyo > 2023);

        return String.valueOf(anyo);
    }

    public static String leerComentario() {
        String comentario = null;
        do {
            System.out.print("Comentario: ");
            comentario = sc.next();
            if (comentario.length() > 28) {
                System.out.println("El comentario no puede tener mas de 28 caracteres");
                sc.nextLine();
            }
        } while (comentario.length() > 28);
        sc.nextLine();
        StringBuffer sb = new StringBuffer(comentario);

        for (int i = comentario.length(); i < 28; i++) {
            sb.append(" ");
        }
        comentario = sb.toString();
        return comentario;
    }

    public static String leerPistas() {
        int pista = 0;
        do {
            System.out.print("Numero pista: ");
            try {
                pista = sc.nextInt();
                if (pista < 0) {
                    System.out.println("Solo numeros mayores que 0");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Solo numeros");
                pista = 0;
            }
        } while (pista < 0);

        return String.valueOf(pista);
    }

    public static String leerNombreArchivo() {
        String nombre;
        File archivo = null;
        do {
            System.out.print("Introduce el fichero donde se almacena la musica: ");
            nombre = sc.next();
            archivo = new File(nombre);
            if (!archivo.exists()) {
                System.out.println("El archivo introducido no existe");
                sc.nextLine();
            }
        } while (!archivo.exists());
        sc.nextLine();

        return nombre;
    }

    public static int leerGenero() {
        int genero = 1;
        do {
            System.out.print("Genero (0-79): ");
            try {
                genero = sc.nextInt();
                if (genero < 0 || genero > 79) {
                    System.out.println("Solo numeros mayores que 0 o menores que 79");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Solo numeros");
                genero = 0;
            }
        } while (genero < 0 || genero > 79);

        return genero;
    }
}
