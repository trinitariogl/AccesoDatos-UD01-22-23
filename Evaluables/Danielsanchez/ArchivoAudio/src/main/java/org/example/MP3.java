package org.example;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;


public class MP3 {
    public static void LeerInfoMP3() {
        String url = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la url: ");
        url = sc.nextLine();
        ArrayList<String> canciones = new ArrayList<>();
        File file = new File(url);
        try (Scanner entrada = new Scanner(file)) {
            while (entrada.hasNextLine()) {
                canciones.add(entrada.nextLine());
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        for (String cancion : canciones) {

            try (RandomAccessFile aleatorio = new RandomAccessFile(cancion, "r")) {
                Long posicion = aleatorio.length();
                long puntero = posicion-128;
                aleatorio.seek(puntero);


                byte[] cabezera = new byte[3];
                aleatorio.read(cabezera);
                String cabeceraString = new String(cabezera);


                byte[] titulo = new byte[30];
                aleatorio.read(titulo);
                String tituloString = new String(titulo);


                byte[] artista = new byte[30];
                aleatorio.read(artista);
                String artistaString = new String(artista);

                byte[] album = new byte[30];
                aleatorio.read(album);
                String albumn = new String(album,"UTF-8");


                byte[] ano = new byte[4];
                aleatorio.read(ano);
                String anoString = new String(ano);


                byte[] comentario = new byte[28];
                aleatorio.read(comentario);
                String comentarioString = new String(comentario);

                byte[] tieneP = new byte[1];
                aleatorio.read(comentario);
                String tienePString = new String(tieneP);

                byte[] numP = new byte[1];
                aleatorio.read(numP);
                String numPString = new String(numP);

                byte[] generos = new byte[1];
                aleatorio.read(generos);
                String genero = new String(generos);

                System.out.println("Título: " + tituloString);
                System.out.println("Artista: " + artistaString);
                System.out.println("Album: " + albumn);
                System.out.println("Número de Pista: " + numPString);
                System.out.println("año: "+ anoString);
                System.out.println("Comentario: " + comentarioString);
                System.out.println("Género: " + genero);
                System.out.println("------------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void escribirInfo(){
        String url = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la url: ");
        url = sc.nextLine();
        ArrayList<String> canciones = new ArrayList<>();
        File file = new File(url);
        try (Scanner entrada = new Scanner(file)) {
            while (entrada.hasNextLine()) {
                canciones.add(entrada.nextLine());
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        for (String cancion : canciones) {

            try (RandomAccessFile aleatorio = new RandomAccessFile(cancion, "rw")) {
                long posicion = aleatorio.length();
                int puntero = (int) posicion-128;
                aleatorio.seek(puntero);
                byte[] global ;

                 global= new byte[3];
                aleatorio.read(global);


                global = new byte[30];
                aleatorio.read(global);


                global= new byte[60];
                aleatorio.read(global);

                System.out.println("Poner el año: ");
                Scanner scc = new Scanner(System.in);
                String ano = scc.nextLine();
                aleatorio.writeBytes(ano);


                System.out.println("Poner comentario: ");
                String comentario = scc.nextLine();
                StringBuffer sb = new StringBuffer(comentario);
                for (int i = comentario.length(); i < 28; i++) {
                         sb.append(" ");
                }
                comentario = sb.toString();
                aleatorio.writeBytes(comentario);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


