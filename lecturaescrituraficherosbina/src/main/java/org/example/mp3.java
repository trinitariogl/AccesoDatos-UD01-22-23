package org.example;

import java.io.*;
import java.util.ArrayList;

public class mp3 {


    public static void LeerInfoMP3(String nombreArchivo){
        try (FileReader fileReader = new FileReader(nombreArchivo);

             BufferedReader buffer= new BufferedReader(fileReader)) {

            ArrayList<String> lineas = new ArrayList<>();
            String linea;
            while ((linea = buffer.readLine()) != null) {
                lineas.add(linea);
            }

        }catch (Exception ex){
            System.out.println("Error"+ex.getMessage());
        }

    }


    public static void EscribirInfoMP3(String nombreArchivo){
        try {
            File rutaArchivo=new File("src/main/resources/"+nombreArchivo);
            System.out.println("Primera Cancion");
            DataOutputStream salida= new DataOutputStream(rutaArchivo);



        }catch (Exception ex){
            System.out.println("Error"+ex.getMessage());
        }

    }
}
