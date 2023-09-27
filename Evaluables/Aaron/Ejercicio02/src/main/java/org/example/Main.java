package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        try {
            File rutaFileMusica = new File("src/main/resources/rutaFileMusica.txt");
            if(!rutaFileMusica.exists()){
                rutaFileMusica.createNewFile();
            }

            File rutaMP3 = new File("src/main/resources/musica");
            File [] listaRuta = rutaMP3.listFiles();


        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }


    }
}