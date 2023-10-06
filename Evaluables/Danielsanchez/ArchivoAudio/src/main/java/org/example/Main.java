package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.spec.ECField;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
      /*  System.out.println("Introduce la url");
        Scanner sc = new Scanner(System.in);
        String lee = "C:/Users/AluTarde/IdeaProjects/AccesoDatos-UD01-22-23/Evaluables/Danielsanchez/ArchivoAudio/Musica";
        lee = sc.nextLine();
        */
        String lee = "";
        MP3.LeerInfoMP3();
        MP3.escribirInfo();
    }

}