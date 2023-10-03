package code;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class MP3 {
    private String cabecera;
    private String titulo;
    private String artista;
    private String album;
    private int anyo;
    private String comentario;
    private boolean tieneNumeroPista;
    private byte numeroPista;
    private byte genero;

    public MP3(String cabecera, String titulo, String artista, String album, int anyo, String comentario, boolean tieneNumeroPista, byte numeroPista, byte genero) {
        this.cabecera = cabecera;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anyo = anyo;
        this.comentario = comentario;
        this.tieneNumeroPista = tieneNumeroPista;
        this.numeroPista = numeroPista;
        this.genero = genero;
    }

    public MP3() {
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isTieneNumeroPista() {
        return tieneNumeroPista;
    }

    public void setTieneNumeroPista(boolean tieneNumeroPista) {
        this.tieneNumeroPista = tieneNumeroPista;
    }

    public byte getNumeroPista() {
        return numeroPista;
    }

    public void setNumeroPista(byte numeroPista) {
        this.numeroPista = numeroPista;
    }

    public byte getGenero() {
        return genero;
    }

    public void setGenero(byte genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "MP3{" +
                "cabecera='" + cabecera + '\'' +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", album='" + album + '\'' +
                ", anyo=" + anyo +
                ", comentario='" + comentario + '\'' +
                ", tieneNumeroPista=" + tieneNumeroPista +
                ", numeroPista=" + numeroPista +
                ", genero=" + genero +
                '}';
    }

    public static void LeerInfoMP3(String nombreArchivo) {
        File fichero = new File(nombreArchivo);
        ArrayList<MP3> canciones = new ArrayList<>();

        if (!fichero.exists()) {
            System.out.println("El fichero con nombre " + nombreArchivo + " no existe");
            return;
        }

        try (BufferedReader lectura = new BufferedReader(new FileReader(fichero))) {

            String linea = null;

            while ((linea = lectura.readLine()) != null) {
                File ficheroMP3 = new File(linea);
                crearMP3(ficheroMP3);
            }

        } catch (EOFException e2) {
        } catch (Exception e) {
            System.out.println("ERROR. " + e.getMessage());
        }
    }

    public static void crearMP3(File fichero) throws IOException {
        try {
            RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

            MP3 cancion = new MP3();

            raf.seek(raf.length() - 128);

            String cabecera;
            String titulo;
            String artista;
            String album;
            int anyo;
            String comentario;
            boolean tieneNumeroPista;
            byte numeroPista;
            byte genero;

            byte[] array = new byte[3];
            raf.readFully(array);
            String cabacera = new String(array, StandardCharsets.US_ASCII);
            cancion.setCabecera(cabacera);

            array = new byte[30];
            raf.readFully(array);
            titulo = new String(array, StandardCharsets.US_ASCII);
            cancion.setTitulo(titulo);

            array = new byte[30];
            raf.readFully(array);
            artista = new String(array, StandardCharsets.US_ASCII);
            cancion.setArtista(artista);

            array = new byte[30];
            raf.readFully(array);
            album = new String(array, StandardCharsets.US_ASCII);
            cancion.setAlbum(album);

            anyo = raf.readInt();;
            cancion.setAnyo(anyo);

            array = new byte[28];
            raf.readFully(array);
            comentario = new String(array, StandardCharsets.US_ASCII);
            cancion.setComentario(comentario);

            tieneNumeroPista = raf.readBoolean();
            cancion.setTieneNumeroPista(tieneNumeroPista);

            numeroPista = raf.readByte();
            cancion.setNumeroPista(numeroPista);

            genero = raf.readByte();
            cancion.setGenero(genero);

            cancion.visualizar();
        } catch (IOException ioe) {
            System.out.println("ERROR. " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR. " + e.getMessage());
        }
    }

    public static void escribirInfoMP3(String nombreArchivo) {
        Scanner sc = new Scanner(System.in);
        File fichero = new File(nombreArchivo);
        String linea = null;

        try (BufferedReader lectura = new BufferedReader(new FileReader(fichero))) {
            while ((linea = lectura.readLine()) != null) {
                if (!linea.isBlank() && !linea.isEmpty()) {
                    File archivo = new File(linea);
                    RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
                    long fileSize = archivo.length();

                    raf.seek((int) fileSize - 125);

                    byte[] array = new byte[30];
                    raf.read(array);
                    System.out.println("Título: " + new String(array));

                    raf.seek(raf.getFilePointer() + 60);


                    System.out.print("Año: ");
                    int anyoUsuario = sc.nextInt();
                    sc.nextLine();
                    raf.writeInt(anyoUsuario);


                    System.out.print("Comentario: ");
                    String comentarioUsuario = sc.nextLine();
                    StringBuffer sb = new StringBuffer(comentarioUsuario);
                    for (int i = comentarioUsuario.length(); i < 14; i++) {
                        sb.append(" ");
                    }
                    comentarioUsuario = sb.toString();
                    raf.writeBytes(comentarioUsuario);

                    System.out.print("Numero de pistas: ");
                    int numPistasUsuario = sc.nextInt();
                    sc.nextLine();
                    raf.writeInt(numPistasUsuario);

                    array = new byte[1];
                    raf.read(array);

                    System.out.println("------------------------------------------------");
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("ERROR. " + fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println("ERROR. " + ioe.getMessage());
        }
    }

    public void visualizar() {
        String generoMensaje = "Género: ";
        if (!(this.genero < 0)) {
            GENERO genero = GENERO.values()[this.genero];
            generoMensaje += genero;
        }

        System.out.println("\n----------------------------");
        System.out.println("Título: " + this.titulo);
        System.out.println("Artista: " + this.artista);
        System.out.println("Álbum: " + this.album);
        System.out.println("Pista Número: " + this.numeroPista);
        System.out.println("Año: " + this.anyo);
        System.out.println("Comentario: " + this.comentario);
        System.out.println(generoMensaje);
        System.out.println(this.tieneNumeroPista ? "Tiene Número de Pista" : "No tiene Número de Pista");
    }
}
