package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class MP3 {


    public static void LeerInfoMP3(String rutaFile) {
        try {

            int i;
            String titulo;
            String artista;
            String album;
            String anyo;
            String comentario;
            String TieneNumeroPista;
            String numeroDePita;
            String genero;
            RandomAccessFile randomAccessFile = new RandomAccessFile(rutaFile, "r");
            long tamanyoFichero = randomAccessFile.length();


            byte[] arrayBytes;
            randomAccessFile.seek((int) tamanyoFichero - 128);

            //cabecera
            arrayBytes = new byte[3];
            randomAccessFile.read(arrayBytes);
            //titulo
            arrayBytes = new byte[30];
            for (i = 0; i < 30; i++) {
                arrayBytes[i] = randomAccessFile.readByte();
            }
            titulo = new String(arrayBytes, StandardCharsets.UTF_8);
            //artista
            arrayBytes = new byte[30];
            for (i = 0; i < 30; i++) {
                arrayBytes[i] = randomAccessFile.readByte();
            }
            artista = new String(arrayBytes, StandardCharsets.UTF_8);
            //album
            arrayBytes = new byte[30];
            for (i = 0; i < 30; i++) {
                arrayBytes[i] = randomAccessFile.readByte();
            }
            album = new String(arrayBytes, "UTF-8");
            //Anyo
            arrayBytes = new byte[4];
            for (i = 0; i < 4; i++) {
                arrayBytes[i] = randomAccessFile.readByte();
            }
            anyo = new String(arrayBytes, StandardCharsets.UTF_8);
            //comentario
            arrayBytes = new byte[28];
            for (i = 0; i < 28; i++) {
                arrayBytes[i] = randomAccessFile.readByte();
            }
            comentario = new String(arrayBytes, "UTF-8");
            //Numero de pista
            arrayBytes = new byte[1];
            randomAccessFile.read(arrayBytes);
            numeroDePita = new String(arrayBytes, StandardCharsets.UTF_8);
            //Genero
            int numGenero = randomAccessFile.read();




            System.out.println("- - - - - - - - - - - - - - ");
            System.out.println("Titulo: " + titulo);
            System.out.println("Artista: " + artista);
            System.out.println("Album: " + album);
            System.out.println("Pista: " + numeroDePita);
            System.out.println("Anyo: " + anyo);
            System.out.println("Comentario: " + comentario);
            System.out.println("Genero: " + EnumGenero.values()[numGenero]);

            randomAccessFile.close();

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void EscribirInfoMP(String rutaFile) throws IOException {

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(rutaFile, "rw");
            Scanner sc = new Scanner(System.in);

            String titulo;
            String pista;
            String anyo;
            String comentario;

            byte[] arrayBytes;

            long longitud = randomAccessFile.length();

            randomAccessFile.seek( longitud - 128);
            arrayBytes = new byte[3];
            randomAccessFile.read(arrayBytes);
            arrayBytes = new byte[30];
            randomAccessFile.read(arrayBytes);
            titulo = new String(arrayBytes, StandardCharsets.UTF_8);
            System.out.println("Titulo : " + titulo);
            System.out.println("Pista: ");
            pista = sc.nextLine();
            System.out.println("Anyo: ");
            anyo = sc.nextLine();
            System.out.println("Comentario: ");
            comentario = sc.nextLine();
            //ignorar
            arrayBytes = new byte[60];
            randomAccessFile.read(arrayBytes);
            //anyo
            randomAccessFile.writeBytes(anyo);
            //comentario
            StringBuffer stringBuffer = new StringBuffer(comentario);
            for (int i = comentario.length();i <28;i++){
                stringBuffer.append(" ");
            }
            comentario = stringBuffer.toString();
            randomAccessFile.writeBytes(comentario);
            //numero de pista
            randomAccessFile.writeBytes(pista);

            randomAccessFile.close();


        }catch (IOException ioex){
            System.out.println("Error: " + ioex);

        }
        catch (Exception e) {
            System.out.println("Error: " + e);

        }
    }
}
