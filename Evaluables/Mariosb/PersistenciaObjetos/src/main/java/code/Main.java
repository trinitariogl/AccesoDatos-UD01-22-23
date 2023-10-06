package code;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static File archivo = new File("src/main/resources/peliculas.dat");

    public static void main(String[] args) throws IOException {
        int opcion;
        System.out.println("\nMi videoclub");
        System.out.println("------------");
        do {
            menu();
            System.out.print("\nSelecciona una opción: ");
            opcion = sc.nextInt();

            if (opcion < 1 || opcion > 5) {
                System.out.print("Número entre el 1 y el 5");
                continue;
            }

            sc.nextLine();

            switch (opcion) {
                case 1:
                    Pelicula pelicula = leerDatosTeclado();
                    insertarObjeto(pelicula);
                    break;
                case 2:
                    Pelicula peliculaSeleccionadaModificar = elegirTituloPelicula("De que película quieres modificar el formato");
                    modificarPelicula(peliculaSeleccionadaModificar);
                    break;
                case 3:
                    Pelicula peliculaSeleccionada = elegirTituloPelicula("Que película quieres borrar:");
                    eliminarPelicula(peliculaSeleccionada);
                    break;
                case 4:
                    visualizarPelicula();
                    break;
                case 5:
                    System.out.println("Finalizando el programa-");
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

    public static void modificarPelicula(Pelicula peliculaSeleccionada) throws IOException {
        String opcion = "";
        do {
            System.out.println("Formato actual -> " + peliculaSeleccionada.getFormato());
            System.out.print("\nFormato nuevo: ");
            opcion = sc.nextLine();

        } while (opcion.isEmpty() || opcion.isEmpty());

        List<Pelicula> listaPeliculas = leerPeliculasFichero();
        for (Pelicula peli : listaPeliculas) {
            if (peli.getTitulo().equalsIgnoreCase(peliculaSeleccionada.getTitulo())) {
                peli.setFormato(opcion);
                break;
            }
        }

        try (FileOutputStream fileOutput = new FileOutputStream(archivo);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){

            objectOutput.writeObject(listaPeliculas);

        } catch (NullPointerException npe) {
            System.out.println("\nNo existe en el fichero una película con el título seleccionado");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void eliminarPelicula(Pelicula peliculaSeleccionada) throws IOException {

        List<Pelicula> listaPeliculas = leerPeliculasFichero();

        if (listaPeliculas.isEmpty()) {
            System.out.println("\nEl fichero no tiene películas");
            return;
        }

        for (Pelicula peli : listaPeliculas) {
            if (peli.getTitulo().equalsIgnoreCase(peliculaSeleccionada.getTitulo())) {
                listaPeliculas.remove(peli);
                break;
            }
        }

        try (FileOutputStream fileOutput = new FileOutputStream(archivo);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {

            objectOutput.writeObject(listaPeliculas);

            System.out.println("La película " + peliculaSeleccionada.getTitulo() + " ha sido eliminada");

        } catch (NullPointerException npe) {
            System.out.println("\nNo existe en el fichero una película con el título seleccionado");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void insertarObjeto(Pelicula nuevaPelicula) throws IOException {
        List<Pelicula> listaPeliculas = leerPeliculasFichero();
        listaPeliculas.add(nuevaPelicula);

        if (!archivo.exists()) {
            archivo.createNewFile();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(archivo);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(nuevaPelicula);
        } catch (IOException ioe) {
            System.out.println("Error error en fichero: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error genérico: " + e.getMessage());
        }
    }

    public static List<Pelicula> leerPeliculasFichero() throws IOException {
        List<Pelicula> listaPeliculas = new ArrayList<>();

        try (FileInputStream fileInput = new FileInputStream(archivo);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            listaPeliculas = (List<Pelicula>) objectInput.readObject();
        } catch (EOFException eofe) {
        } catch (IOException ioe) {
            System.out.println("ERROR fichero: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR general: " + e.getMessage());
        }

        return listaPeliculas;
    }

    public static void visualizarPelicula() {
        try {
            Pelicula peliculaSeleccionada = elegirTituloPelicula("Que película quieres visualizar: ");
            System.out.println("\nVisualizando la película " + peliculaSeleccionada.getTitulo() + peliculaSeleccionada.visualizar());

        } catch (NullPointerException npe) {
            System.out.println("\nNo existe en el fichero una película con el título seleccionado");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static Pelicula elegirTituloPelicula(String mensaje) throws IOException {
        List<Pelicula> listaPeliculas = leerPeliculasFichero();
        int contador = 1;
        Pelicula peliculaSeleccionada = null;
        String titulo = "";
        boolean existe = false;

        do {
            System.out.println("\n" + mensaje);
            for (Pelicula peli : listaPeliculas) {
                System.out.println("Película número " + (contador++) + " -> " + peli.getTitulo());
            }
            System.out.print("Título: ");
            titulo = sc.nextLine();

        } while (titulo.isBlank() || titulo.isEmpty());


        for (Pelicula peli : listaPeliculas) {
            if (titulo.equalsIgnoreCase(peli.getTitulo())) {
                peliculaSeleccionada = peli;
                existe = true;
                break;
            }
        }

        if (!existe) {
            return null;
        } else {
            return peliculaSeleccionada;
        }
    }

    public static Pelicula leerDatosTeclado() {

        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pelicula pelicula = new Pelicula();

        System.out.print("Título: ");
        pelicula.setTitulo(sc.nextLine());

        System.out.print("Actores: ");
        pelicula.setActores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Directores: ");
        pelicula.setDirectores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Fecha: ");
        pelicula.setFechaSalida(LocalDate.parse(sc.nextLine(), formatear));

        System.out.print("Formato: ");
        pelicula.setFormato(sc.nextLine());

        return pelicula;

    }
}