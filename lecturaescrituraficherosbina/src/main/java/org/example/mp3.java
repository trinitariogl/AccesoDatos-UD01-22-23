package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class mp3 {


    public static void LeerInfoMP3(String nombreArchivo) {
        File archivo=new File("src/main/resources/"+nombreArchivo+".txt");

        try (FileReader fileReader = new FileReader(archivo);

             BufferedReader buffer = new BufferedReader(fileReader)) {

            ArrayList<String> lineas = new ArrayList<>();
            String linea;
            while ((linea = buffer.readLine()) != null) {
                lineas.add(linea);
            }
    buffer.close();

for (String line : lineas) {
File fil=new File(line);

    try (RandomAccessFile raf = new RandomAccessFile(fil, "r")) {
        long longitudArchivo = raf.length();
        long posicionMetadatos =  longitudArchivo - 128;
        raf.seek(posicionMetadatos);

// Leer los metadatos

        byte[] cabecera = new byte[3];
        raf.read(cabecera);
        String cabec = new String(cabecera);

        byte[] titulo = new byte[30];
        raf.read(titulo);
        String titu = new String(titulo);

        byte[] artista = new byte[30];
        raf.read(artista);
        String arti = new String(artista);

        byte[] album = new byte[30];
        raf.read(album);
        String alb = new String(album);

        byte[] anyo = new byte[4];
        raf.read(anyo);
        String any = new String(anyo);

        byte[] comentario = new byte[28];
        raf.read(comentario);
        String come = new String(comentario);

        byte[] tienenp = new byte[1];
        raf.read(tienenp);
        String tnp = new String(tienenp);

        byte[] numpista = new byte[1];
        raf.read(numpista);
        String nump = new String(numpista);

        byte[] genero = new byte[4];
        raf.read(genero);
        String gene = new String(genero);


           /*     String titulo = new String(bufer, 3, 30, "UTF-8").trim();
                String artista = new String(bufer, 33, 30, "UTF-8").trim();
                String album = new String(bufer, 63, 30, "UTF-8").trim();
                String año = new String(bufer, 93, 4, "UTF-8").trim();
                String comentario = new String(bufer, 97, 28, "UTF-8").trim(); */
        System.out.println(titu.toString());
        System.out.println(cabec.toString());
        System.out.println(arti.toString());
        System.out.println(alb.toString());
        System.out.println(anyo.toString());
        System.out.println(come.toString());
        System.out.println(nump.toString());
        System.out.println(gene.toString());

    } catch (Exception ex) {
        System.out.println("Error metadatos");
    }
}





            }catch(Exception ex){
                System.out.println("Error" + ex.getMessage());
            }

        }


    public static void contarBytesYAlmacenar(String nombreArchivoMP3) throws IOException {


        File archivo = new File(nombreArchivoMP3);


        if (archivo.exists()) {
            long tamanoBytes = archivo.length();
            System.out.println("Archivo MP3: " + nombreArchivoMP3);
            System.out.println("Tamaño en bytes: " + tamanoBytes);

            // Aquí puedes almacenar el tamaño en bytes o realizar otras operaciones según tus necesidades.
        } else {
            System.out.println("El archivo MP3 no existe: " + nombreArchivoMP3);
        }

    }


    public static void EscribirInfoMP3(String nombreArchivo){
        try {
            File rutaArchivo=new File("src/main/resources/"+nombreArchivo);
            System.out.println("Primera Cancion");
            FileOutputStream fileout=new FileOutputStream(rutaArchivo);
            DataOutputStream salida= new DataOutputStream(fileout);



        }catch (Exception ex){
            System.out.println("Error"+ex.getMessage());
        }

    }
}
