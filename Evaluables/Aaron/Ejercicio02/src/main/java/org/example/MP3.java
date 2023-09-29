package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MP3 {

    public static void LeerInfoMP3(String rutaFile) throws FileNotFoundException {
        try {
            int i;
            String titulo;
            String artista;
            String album;
            String anyo;
            RandomAccessFile randomAccessFile = new RandomAccessFile(rutaFile, "r");
            long tamanyoFichero = randomAccessFile.length();

            byte [] arrayBytes;
            randomAccessFile.seek((int)tamanyoFichero-128);

            System.out.println("- - - - - - - - - - - - - - - - ");
            //cabecera
            arrayBytes = new byte[3];
            randomAccessFile.read(arrayBytes);
            //titulo
            arrayBytes = new byte[30];
            for(i = 0;i<30;i++){
                arrayBytes[i]= randomAccessFile.readByte();
            }
            titulo = new String(arrayBytes, StandardCharsets.UTF_8);
            System.out.println("Titulo: " + titulo);
            //artista
            arrayBytes = new byte[30];
            for( i =0; i < 30;i++){
                arrayBytes[i] = randomAccessFile.readByte();
            }
            artista = new String(arrayBytes, StandardCharsets.UTF_8);
            System.out.println("Artista: " + artista);
            //album
            arrayBytes = new byte[30];
            for( i = 0; i < 30;i++){
                arrayBytes[i] = randomAccessFile.readByte();
            }
            album = new String(arrayBytes, "UTF-8");
            System.out.println("Album: " + album);
            //Anyo
            arrayBytes = new byte[4];
            for(i = 0; i < 4;i++){
                arrayBytes[i] = randomAccessFile.readByte();
            }
            anyo = new String(arrayBytes, StandardCharsets.UTF_8);






        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }
}
