package code;

import com.mycompany.libreria.Libreria;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int opcion;
        do {
            menu();
            opcion = Libreria.getInt("Seleccione una opción: ", 1, 5);

            switch (opcion) {
                case 1:
                    Pelicula pelicula = insertarPelicula();
                    insertarObjeto(pelicula);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    List<Pelicula> peliculas = leerPeliculasFichero();
                    for (Pelicula pel : peliculas) {
                        System.out.println(pel.toString());
                    }
                default:
                    break;
            }
        } while (opcion != 5);
    }

    public static void menu() {
        System.out.println("Menú:");
        System.out.println("1. Insertar Película");
        System.out.println("2. Modificar Película");
        System.out.println("3. Eliminar Película");
        System.out.println("4. Visualizar Película");
        System.out.println("5. Salir");
        System.out.println();
    }

    public static Pelicula insertarPelicula() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner teclado = new Scanner(System.in);
        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo(Libreria.getLine("Introduce el título: "));
        pelicula.setActores(new ArrayList<>(List.of(Libreria.getLine("Introduce los actores: ").split(";"))));
        pelicula.setDirectores(new ArrayList<>(List.of(Libreria.getLine("Introduce los directores: ").split(";"))));
        pelicula.setFechaSalida(LocalDate.parse(Libreria.getLine("Introduce la fecha: "), formatter));
        pelicula.setFormato(Libreria.getLine("Introduce formato: "));

        return pelicula;
    }

    public static void insertarObjeto(Pelicula nuevaPelicula) throws IOException {
        try {
            File archivo = new File("src/main/resources/peliculas.dat");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(nuevaPelicula);

            objectOutputStream.close();
        } catch (IOException ioe) {
            System.out.println("Error error en fichero: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error genérico: " + e.getMessage());
        }
    }

    public static List<Pelicula> leerPeliculasFichero() throws IOException {
        List<Pelicula> listaPeliculas = null;

        try {
            File archivo = new File("src/main/resources/peliculas.dat");
            FileInputStream fileInput = new FileInputStream(archivo);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            try {
                while (true) {
                    Pelicula pelicula = (Pelicula) objectInput.readObject();
                    listaPeliculas.add(pelicula);
                }
            } catch (EOFException eofe) {}
        } catch (IOException ioe) {
            System.out.println("ERROR fichero: " + ioe.getMessage());
            listaPeliculas = null;
        } catch (Exception e) {
            System.out.println("ERROR general: " + e.getMessage());
            listaPeliculas = null;
        }

        return listaPeliculas;
    }
}