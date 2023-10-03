package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class MP3 {
    static Scanner sc = new Scanner(System.in);
    public static void LeerInfoMP3(String nombreArchivo) {
        try {

            File f;

            if (nombreArchivo.endsWith(".txt")) {
                f = new File("src/main/resources/" + nombreArchivo);
            } else {
                f = new File("src/main/resources/" + nombreArchivo + ".txt");
            }

            BufferedReader lecturaFichero = new BufferedReader(new FileReader(f));

            ArrayList<String> lineas = new ArrayList<>();
            String linea;

            do {
                linea = lecturaFichero.readLine();
                if (linea != null && !linea.isBlank() && !linea.isEmpty()) {
                    lineas.add(linea);
                }
            } while (linea != null);

            lecturaFichero.close();

            System.out.println("\n--------------------------------------------------");

             Charset charset = StandardCharsets.ISO_8859_1;

            for (String l: lineas){
                File f2 = new File(l);
                RandomAccessFile raf = new RandomAccessFile(f2, "r");

                long posicion = raf.length();
                long puntero = posicion - 128;
                raf.seek(puntero + 3); //El "+3" es porque queremos leer a partir del titulo,
                                           // por lo que los 3 bytes de la cabecera, los saltamos.

                byte[] titulo = new byte[30];
                raf.read(titulo);
                String tituloT = new String(titulo);

                byte[] artista = new byte[30];
                raf.read(artista);
                String artist = new String(artista);

                byte[] album = new byte[30];
                raf.read(album);
                String alb = new String(album);

                byte[] anyo = new byte[4];
                raf.read(anyo);
                String year = new String(anyo);

                byte[] comentario = new byte[28];
                raf.read(comentario);
                String comment = new String(comentario);

                byte [] tieneNumeroPista = new byte[1];
                raf.read(tieneNumeroPista);

                byte[] numPista = new byte[1];
                raf.read(numPista);
                String numeroPista = new String(numPista);

                byte[] genero = new byte[1];
                raf.read(genero);
                String gener = new String(genero);

                System.out.println("Titulo -> " + tituloT);
                System.out.println("Artista -> " + artist);
                System.out.println("Álbum -> " + alb);
                System.out.println("Numero Pista -> " + numeroPista);
                System.out.println("Año -> " + year);
                System.out.println("Comentario -> " + comment);
                System.out.println("Género -> " + seleccionGenero(gener));
                System.out.println("--------------------------------------------------");

                raf.close();
            }

            System.out.println("\n");

        }catch (FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error -> " + e.getMessage());
        }

    }
    public static void EscribirMP3(String nombreArchivo){
        try {
            File f;

            if (nombreArchivo.endsWith(".txt")) {
                f = new File("src/main/resources/" + nombreArchivo);
            } else {
                f = new File("src/main/resources/" + nombreArchivo + ".txt");
            }

            BufferedReader lecturaFichero = new BufferedReader(new FileReader(f));

            ArrayList<String> lineas = new ArrayList<>();
            String linea;

            do {
                linea = lecturaFichero.readLine();
                if (linea != null && !linea.isBlank() && !linea.isEmpty()) {
                    lineas.add(linea);
                }
            } while (linea != null);

            lecturaFichero.close();

            System.out.println("\n--------------------------------------------------");

            for (String l: lineas){
                File f2 = new File(l);
                RandomAccessFile raf = new RandomAccessFile(f2, "rw");

                String titulo, anyo, numPista, comentario;

                //Titulo
                System.out.print("Título -> ");
                titulo = sc.nextLine();

                //Numero Pista
                System.out.print("\tNúmero de pista -> ");
                numPista = sc.nextLine();

                //Año
                System.out.print("\tAño -> ");
                anyo = sc.nextLine();

                //Comentario
                System.out.print("\tComentario -> ");
                comentario = sc.nextLine();

                System.out.println("--------------------------------------------------");

                long posicion = raf.length();
                long puntero = posicion - 128;
                raf.seek(puntero + 3); //El "+3" es porque queremos leer a partir del titulo,
                // por lo que los 3 bytes de la cabecera, los saltamos.

                byte[] title;
                if(titulo.length() < 30){
                    while(titulo.length() < 30){
                        titulo = titulo + " ";
                    }
                }
                title = titulo.getBytes();
                raf.write(title);

                raf.seek(puntero + 93);//Para posicionarnos en el byte 93, y empezar a leer en el 94
                byte[] year;
                if(anyo.length() < 4){
                    while(anyo.length() < 4){
                        anyo += " ";
                    }
                }
                year = anyo.getBytes();
                raf.write(year);

                byte[] comment;
                if(comentario.length() < 28){
                    while(comentario.length() < 28){
                        comentario += " ";
                    }
                }
                comment = comentario.getBytes();
                raf.write(comment);

                raf.seek(puntero+126);//Para posicionarnos en el byte 126, y leer el 127
                byte[] trackNumber;
                trackNumber = numPista.getBytes();
                raf.write(trackNumber);

                raf.close();
            }

            System.out.println("\n");

        } catch (FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
        } catch (IOException ioex){
            System.out.println("Error -> " + ioex.getMessage());
        }
    }

    public static String seleccionGenero(String g){
        String genero;
        switch (g){
            case "0":
                    genero = "Blues";
                    break;
            case "1":
                genero = "Clasic rock";
                break;
            case "2":
                genero = "Country";
                break;
            case "3":
                genero = "Dance";
                break;
            case "4":
                genero = "Disco";
                break;
            case "5":
                genero = "Funk";
                break;
            case "6":
                genero = "Grunge";
                break;
            case "7":
                genero = "Hip-Hop";
                break;
            case "8":
                genero = "Jazz";
                break;
            case "9":
                genero = "Metal";
                break;
            case "10":
                genero = "New age";
                break;
            case "11":
                genero = "Oldies";
                break;
            case "12":
                genero = "Other";
                break;
            case "13":
                genero = "Pop";
                break;
            case "14":
                genero = "Rhythm and blues";
                break;
            case "15":
                genero = "Rap";
                break;
            case "16":
                genero = "Reggae";
                break;
            case "17":
                genero = "Rock";
                break;
            case "18":
                genero = "Techno";
                break;
            case "19":
                genero = "Industrial";
                break;
            case "20":
                genero = "Alternative";
                break;
            default:
                genero = "sin genero";
                break;
        }
        return genero;
    }
}