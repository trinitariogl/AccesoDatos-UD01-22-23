package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class mp3 {


    static Scanner sc=new Scanner(System.in);

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




    public static void EscribirInfoMP3(String nombreArchivo) {


        File archivo = new File("src/main/resources/" + nombreArchivo + ".txt");

        try (FileReader fileReader = new FileReader(archivo);

             BufferedReader buffer = new BufferedReader(fileReader)) {

            ArrayList<String> lineas = new ArrayList<>();
            String linea;
            while ((linea = buffer.readLine()) != null) {
                lineas.add(linea);
            }
            buffer.close();

            for (String line : lineas) {
                File fil = new File(line);

                System.out.print("Nuevo título: ");
                String nuevoTitulo = sc.nextLine();

                System.out.print("Nueva cabecera: ");
                String nuevaCabecera = sc.nextLine();

                System.out.print("Nuevo artista: ");
                String nuevoArtista = sc.nextLine();

                System.out.print("Nuevo álbum: ");
                String nuevoAlbum = sc.nextLine();

                System.out.print("Nuevo año: ");
                String nuevoAnyo = sc.nextLine();

                System.out.print("Nuevo comentario: ");
                String nuevoComentario = sc.nextLine();

                System.out.print("Nuevo número de pista: ");
                String nuevoNumeroPista = sc.nextLine();

                System.out.print("Nuevo género: ");
                String nuevoGenero = sc.nextLine();


                try (RandomAccessFile raf = new RandomAccessFile(fil, "rw")) {
                    long longitudArchivo = raf.length();
                    long posicionMetadatos = longitudArchivo - 128;
                    raf.seek(posicionMetadatos);

                    Charset charset=StandardCharsets.ISO_8859_1;

                    // Modificar los valores de los metadatos
                    byte[] nuevoTituloBytes = nuevoTitulo.getBytes(charset);
                    raf.write(nuevoTituloBytes, 0, Math.min(nuevoTituloBytes.length, 30));

                    byte[] nuevaCabeceraBytes = nuevaCabecera.getBytes(charset);
                    raf.seek(posicionMetadatos  + 3);
                    raf.write(nuevaCabeceraBytes, 0, Math.min(nuevaCabeceraBytes.length, 3));

                    byte[] nuevoArtistaBytes = nuevoArtista.getBytes(charset);
                    raf.seek(posicionMetadatos  + 33);
                    raf.write(nuevoArtistaBytes, 0, Math.min(nuevoArtistaBytes.length, 30));

                    byte[] nuevoAlbumBytes = nuevoAlbum.getBytes(charset);
                    raf.seek(posicionMetadatos  + 63);
                    raf.write(nuevoAlbumBytes, 0, Math.min(nuevoAlbumBytes.length, 30));

                    byte[] nuevoAnyoBytes = nuevoAnyo.getBytes(charset);
                    raf.seek(posicionMetadatos  + 93);
                    raf.write(nuevoAnyoBytes, 0, Math.min(nuevoAnyoBytes.length, 4));

                    byte[] nuevoComentarioBytes = nuevoComentario.getBytes(charset);
                    raf.seek(posicionMetadatos  + 97);
                    raf.write(nuevoComentarioBytes, 0, Math.min(nuevoComentarioBytes.length, 28));

                    byte[] nuevoNumeroPistaBytes = nuevoNumeroPista.getBytes(charset);
                    raf.seek(posicionMetadatos  + 125);
                    raf.write(nuevoNumeroPistaBytes, 0, Math.min(nuevoNumeroPistaBytes.length, 1));

                    byte[] nuevoGeneroBytes = nuevoGenero.getBytes(charset);
                    raf.seek(posicionMetadatos  + 127);
                    raf.write(nuevoGeneroBytes, 0, Math.min(nuevoGeneroBytes.length, 1));


                    raf.close();

                } catch (Exception ex) {
                    System.out.println("Error metadatos");
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }}