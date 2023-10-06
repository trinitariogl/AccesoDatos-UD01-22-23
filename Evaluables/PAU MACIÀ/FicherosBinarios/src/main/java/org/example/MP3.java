package org.example;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MP3 {

    //Muestra la info de cada uno de los ficheros
    public static void leerInfo(String direccion) {
        File cancion = new File(direccion);
        try (RandomAccessFile raf = new RandomAccessFile(cancion, "r")) {
            raf.seek(raf.length() - 128);

            byte[] bytes = new byte[3];
            raf.read(bytes);
            System.out.println();
            bytes = new byte[30];
            raf.read(bytes);
            System.out.println("Titulo: " + new String(bytes));
            bytes = new byte[30];
            raf.read(bytes);
            System.out.println("Artista: " + new String(bytes));
            bytes = new byte[30];
            raf.read(bytes);
            System.out.println("Album: " + new String(bytes));
            bytes = new byte[4];
            raf.read(bytes);
            System.out.println("Año: " + new String(bytes));
            bytes = new byte[28];
            raf.read(bytes);
            System.out.println("Comentario: " + new String(bytes));
            bytes = new byte[1];
            raf.read(bytes);
            System.out.println("Tiene numero pista: " + new String(bytes));
            bytes = new byte[1];
            raf.read(bytes);
            System.out.println("Número pista: " + new String(bytes));
            bytes = new byte[1];
            raf.read(bytes);
            System.out.println("Género: " + new String(bytes));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró en la ruta especificada.");
        } catch (IOException e) {
            System.out.println("Error de lectura en el archivo.");
        } catch (Exception e) {
            System.out.println("Se produjo un problema al leer la información del archivo.");
        }
    }
    //Nos obliga a meter información en número de pista, año y comentarios
    public static void escribirInfo(String direccion) {
        Scanner sc = new Scanner(System.in);
        String comentario = "";
        int anyo = 0;
        int pista = 0;
        File cancion = new File(direccion);
        try (RandomAccessFile raf = new RandomAccessFile(cancion, "rw")) {
            do {
                System.out.println("Escribe el número de pista: ");
                pista = sc.nextInt();
            } while (pista < 1 || pista > 9);
            sc.nextLine();
            do {
                System.out.println("Escribe el año de lanzamiento: ");
                anyo = sc.nextInt();
            } while (anyo < 0 || anyo > 9999);
            sc.nextLine();
            do {
                System.out.println("Escribe un comentario: ");
                comentario = sc.nextLine();
            } while (comentario.length() > 28);

            raf.seek(raf.length() - 2);
            raf.writeBytes(String.valueOf(pista));

            raf.seek(raf.length() - 35);
            raf.writeBytes(String.valueOf(anyo));

            raf.seek(raf.length() - 31);
            raf.writeBytes(comentario);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró en la ruta especificada.");
        } catch (IOException e) {
            System.out.println("Error de escritura en el archivo.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada incorrecta. Se esperaba un número.");
        } catch (Exception e) {
            System.out.println("Se produjo un problema al escribir la información en el archivo.");
        }
    }
}