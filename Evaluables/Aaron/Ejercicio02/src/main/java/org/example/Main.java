package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        try {
            File rutaFileMusica = new File("src/main/resources/rutaFileMusica.txt");
            if(!rutaFileMusica.exists()){
                rutaFileMusica.createNewFile();
            }

            File rutaMP3 = new File("src/main/resources/musica");


            if(rutaMP3.exists() && rutaMP3.isDirectory()){
                File [] listaRuta = rutaMP3.listFiles();

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaFileMusica));

                for (File file : listaRuta) {
                    String rutaRelativa = rutaMP3.toURI().relativize(file.toURI()).getPath();
                    bufferedWriter.write(rutaRelativa + " ");
                }
                bufferedWriter.close();
            }
            String [] array= null;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaFileMusica));
            String linea;
            while((linea = bufferedReader.readLine()) != null){
                  array = linea.split(" ");
            }

            for(String s : array){
                System.out.println(s);
            }


        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }


    }
}