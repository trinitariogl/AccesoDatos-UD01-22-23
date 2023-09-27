package org.example;

import java.io.File;
import java.io.FileInputStream;

public class MP3 {
    private String titulo;
    private String artista;
    private String album;
    private byte numeroPista;
    private int anyo;
    private StringBuffer comentario = new StringBuffer(28);


    public static void LeerInfoMP3(String nombreArchivo) {
        File fichero = new File(nombreArchivo);

        try (FileInputStream lectura = new FileInputStream("musica.mp3")) {
            int dato = 0;

            while ((dato = lectura.read()) != -1) {
                System.out.println(dato);
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
