package org.example;

import com.mycompany.libreria.Libreria;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static File fichero = new File("src/main/resources/fichero.dat");

    public static void main(String[] args) {
        int menu;
        do {
            System.out.println("--------------------------------------------------------------");
            menu = Libreria.createMenu("1. Insertar Pelicula", "2. Modificar Pelicula",
                    "3. Eliminar Pelicula", "4. Visualizar Pelicula", "5. Mostrar todas las peliculas", "6. Salir");
            System.out.println("--------------------------------------------------------------\n");
            switch (menu) {
                case 1:
                    insertarPelicula();
                    break;
                case 2:
                    modificarPelicula();
                    break;
                case 3:
                    eliminarPelicula();
                    break;
                case 4:
                    visualizarPeliculas();
                    break;
                case 5:
                    mostrarTodasLasPeliculas();
                    break;
                default:
                    System.out.println("Hasta luego");
            }
        } while (menu != 6);
    }

    private static void mostrarTodasLasPeliculas() {
        ArrayList<Pelicula> peliculas = leerFichero();

        if (peliculas.isEmpty()){
            System.out.println("No hay peliculas actualmente");
            return;
        }

        for (Pelicula peliActual : peliculas) {
            System.out.println(peliActual);
        }

    }

    private static void visualizarPeliculas() {
        ArrayList<Pelicula> peliculas = leerFichero();
        if (peliculas.isEmpty()){
            System.out.println("No hay peliculas actualmente");
            return;
        }

        String peliABuscar = Libreria.getLine("Titulo de peli a mostrar: ");

        Pelicula peli = buscarPelicula(peliculas, peliABuscar);
        if (peli == null) {
            System.out.println("No existe una peli con el título \"" + peliABuscar + "\".");
            return;
        }
        System.out.println(peli);


    }

    private static void eliminarPelicula() {
        ArrayList<Pelicula> peliculas = leerFichero();
        if (peliculas.isEmpty()){
            System.out.println("No hay peliculas actualmente");
            return;
        }

        String peliABuscar = Libreria.getLine("Titulo de peli a eliminar: ");

        Pelicula peli = buscarPelicula(peliculas, peliABuscar);

        if (peli == null) {
            System.out.println("No existe una peli con el título \"" + peliABuscar + "\".");
            return;
        }

        peliculas.remove(peli);
        System.out.println("Pelicula \"" + peli.getTitulo() + "\" borrada con éxito.");
        introducirPelicula(peliculas);

    }

    private static void modificarPelicula() {
        ArrayList<Pelicula> peliculas = leerFichero();
        if (peliculas.isEmpty()){
            System.out.println("No hay peliculas actualmente");
            return;
        }

        String peliABuscar = Libreria.getLine("Titulo de peli a buscar: ");

        Pelicula peli = buscarPelicula(peliculas, peliABuscar);

        if (peli == null) {
            System.out.println("No existe una peli con el título \"" + peliABuscar + "\".");
            return;
        }

        peli.setFormato(Libreria.getLine("Nuevo Formato: "));
        introducirPelicula(peliculas);

    }

    private static void insertarPelicula() {
        Pelicula peli = new Pelicula();
        ArrayList<Pelicula> peliculas = leerFichero();

        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        peli.setTitulo(Libreria.getLine("Titulo: "));
        peli.setActores(Arrays.asList(Libreria.getLine("Actores separados por ';': ").split(";")));
        peli.setDirectores(Arrays.asList(Libreria.getLine("Directores separados por ';': ").split(";")));
        peli.setFechaSalida(LocalDate.parse(Libreria.getLine("Fecha Salida: "), formatear));
        peli.setFormato(Libreria.getLine("Formato: "));

        peliculas.add(peli);
        introducirPelicula(peliculas);
    }

    /**
     * Se encarga de actualizar el fichero con el nuevo ArrayList
     * @param listaPelis el ArrayList a introducir en el fichero
     */
    private static void introducirPelicula(ArrayList<Pelicula> listaPelis) {
        try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(fichero))) {
            for (Pelicula peliActual : listaPelis) {
                OOS.writeObject(peliActual);
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Método que se encarga de actualizar en un ArrayList las peliculas guardas actualmente en el fichero
     * @return el ArrayList con las peliculas
     */
    private static ArrayList<Pelicula> leerFichero() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        if (!fichero.exists()) {
            try {
                if (fichero.createNewFile())
                    System.out.println("Fichero creado con exito.");
                return listaPeliculas;
            } catch (IOException e) {
                System.out.println("ERROR CREANDO ARCHIVO: " + e.getMessage());
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            Pelicula peliActual;
            while (true) {
                peliActual = (Pelicula) ois.readObject();
                listaPeliculas.add(peliActual);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        }


        return listaPeliculas;
    }

    /**
     * Un método para buscar una pelicula por el título
     * @param peliculas el ArrayList en el que buscará
     * @param peliABuscar el String dle titulo que buscará
     * @return La pelicula con el título, en null si no la encuentra
     */
    private static Pelicula buscarPelicula(ArrayList<Pelicula> peliculas, String peliABuscar) {
        for (Pelicula peliActual : peliculas) {
            if (peliActual.getTitulo().equalsIgnoreCase(peliABuscar)) {
                return peliActual;
            }
        }
        Pelicula peli = null;
        return peli;
    }

}