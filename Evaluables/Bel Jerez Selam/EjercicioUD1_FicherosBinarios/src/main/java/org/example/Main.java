package org.example;

import com.mycompany.libreria.Libreria;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int eleccion;
        do {
            eleccion = Libreria.createMenu("\n1. Leer Info MP3", "2. Escribir Info MP3", "3. Salir");
            switch (eleccion) {
                case 1:
                    leerInfo();
                    break;
                case 2:
                    escribirInfo();
                    break;
                default:
                    break;
            }
        } while (eleccion != 3);
        System.out.println("Un placer");

    }

    private static void escribirInfo() {
        File archivo = pedirArchivo();
        ArrayList<File> listaCancionesRuta = sacarCancionesDeLaRuta(archivo);

        for (File cancionActual : listaCancionesRuta) {
            try (RandomAccessFile raf = new RandomAccessFile(cancionActual, "rw")) {
                long fileSize = cancionActual.length();
                raf.seek((int) fileSize - 125);

                byte[] array = new byte[30];
                raf.read(array);
                System.out.println("Título: " + new String(array));

                raf.seek(raf.getFilePointer() + 60);


                int anyoUsuario = Libreria.getInt("Año: ");
                raf.writeInt(anyoUsuario);

                String comentarioUsuario = Libreria.getLine("Comentario: ");
                StringBuffer sb = new StringBuffer(comentarioUsuario);
                for (int i = comentarioUsuario.length(); i < 14; i++) {
                    sb.append(" ");
                }
                comentarioUsuario = sb.toString();
                raf.writeBytes(comentarioUsuario);

                int numPistasUsuario = Libreria.getInt("Numero de pistas: ");
                raf.writeInt(numPistasUsuario);

                array = new byte[1];
                raf.read(array);

                System.out.println("------------------------------------------------");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }

    private static File pedirArchivo() {
        File archivo;
        do {
            String ruta = Libreria.getLine("Dime la ruta del archivo con las rutas: \t");
            archivo = new File(ruta);
            if (archivo.exists())
                break;
            System.err.println("ERROR: ARCHIVO NO EXISTE");
            System.out.println();
        } while (true);
        return archivo;
    }

    private static void leerInfo() {

        File archivo = pedirArchivo();
        ArrayList<File> listaCancionesRuta = sacarCancionesDeLaRuta(archivo);
        ArrayList<Cancion> listaCanciones = convertirACancion(listaCancionesRuta);

        for (Cancion cancionActual : listaCanciones) {
            System.out.println("\n--------------------------------------");
            cancionActual.mostrarDatos();
        }


    }

    public static ArrayList<File> sacarCancionesDeLaRuta(File archivo) {
        ArrayList<File> listaRutas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String lineaActual = lector.readLine();
            while (lineaActual != null) {
                File rutaActual = new File(lineaActual);
                if (rutaActual.exists() && rutaActual.isFile()) {
                    listaRutas.add(rutaActual);
                }
                lineaActual = lector.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
            listaRutas = null;
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            listaRutas = null;
        }
        return listaRutas;
    }

    private static ArrayList<Cancion> convertirACancion(ArrayList<File> listaCancionesRuta) {
        ArrayList<Cancion> listaCanciones = new ArrayList<>();
        for (File cancionActual : listaCancionesRuta) {

            final long TAMANYOCANCIONACTUAL = cancionActual.length();
            try (RandomAccessFile lector = new RandomAccessFile(cancionActual, "r")) {
                lector.seek(TAMANYOCANCIONACTUAL - 128);

                char[] cabecera = new char[3];
                char[] titulo = new char[30];
                char[] artista = new char[30];
                char[] album = new char[30];
                char[] año = new char[4];
                char[] comentario = new char[28];
                boolean tieneNumeroPista;
                byte numeroPista, genero;

                for (int i = 0; i < cabecera.length; i++) {
                    cabecera[i] = (char) lector.read();
                }

                for (int i = 0; i < titulo.length; i++) {
                    titulo[i] = (char) lector.read();
                }

                for (int i = 0; i < artista.length; i++) {
                    artista[i] = (char) lector.read();
                }

                for (int i = 0; i < album.length; i++) {
                    album[i] = (char) lector.read();
                }

                for (int i = 0; i < año.length; i++) {
                    año[i] = (char) lector.read();
                }

                for (int i = 0; i < comentario.length; i++) {
                    comentario[i] = (char) lector.read();
                }

                tieneNumeroPista = lector.readBoolean();

                numeroPista = lector.readByte();

                genero = lector.readByte();

                String cabeceraS = new String(cabecera);
                String tituloS = new String(titulo);
                String artistaS = new String(artista);
                String albumS = new String(album);
                String añoS = new String(año);
                String comentarioS = new String(comentario);

                Cancion cancion = new Cancion(cabeceraS, tituloS, artistaS, albumS, añoS, comentarioS,
                        tieneNumeroPista, numeroPista, Cancion.GENERO.values()[Math.max(genero, 0)]);
                listaCanciones.add(cancion);

            } catch (EOFException e) {
                System.err.println("FINAL DEL FICHERO");
                listaCanciones = null;
            } catch (FileNotFoundException e) {
                System.err.println("ERROR: " + e.getMessage());
                listaCanciones = null;
            } catch (IOException e) {
                System.err.println("ERROR: " + e.getMessage());
                listaCanciones = null;
            }
        }
        return listaCanciones;
    }
}