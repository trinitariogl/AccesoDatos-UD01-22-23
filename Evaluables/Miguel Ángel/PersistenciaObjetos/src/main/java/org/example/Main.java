package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static int opcion = 0;
    static Scanner sc = new Scanner(System.in);
    static File archivo = new File("src/main/resources/peliculas.dat");

    public static void main(String[] args) throws IOException {

        System.out.println("\nMi Videoclub");
        System.out.println("---------------");
        do {
            try {

                System.out.println("Menú: ");
                System.out.println("1. Insertar Pelicula");
                System.out.println("2. Modificar Pelicula");
                System.out.println("3. Eliminar Pelicula");
                System.out.println("4. Visualizar Pelicula");
                System.out.println("5. Salir");

                System.out.print("\nSeleccion una opción: ");
                opcion = sc.nextInt();

                if (opcion < 1 || opcion > 5) {
                    System.out.println("Un número del 1 al 5 por favor");
                    continue;
                }

                sc.nextLine();

                switch (opcion) {
                    case 1:
                        Pelicula pelicula = leerDatosTeclado();
                        insertarObjeto(pelicula);
                        break;
                    case 2:
                        Pelicula peliculaElegidaModificar = elegirTituloPelicula("De que película quieres modificar el formato");
                        modificarPelicula(peliculaElegidaModificar);
                        break;
                    case 3:
                        Pelicula peliculaElegida = elegirTituloPelicula("Que película quieres borrar:");
                        eliminarPelicula(peliculaElegida);
                        break;
                    case 4:
                        visualizarPelicula();
                        break;
                    case 5:
                        System.out.println("Finalizando el programa-");
                        break;
                }

            } catch (InputMismatchException err) {
                sc.nextLine();
                System.err.println("Un valor numérico porfa");
            } catch (Exception err2) {
                System.out.println("Simplemente error " + err2.getMessage());
            }
        } while (opcion != 5);
    }

    /**
     * @return Este método nos devuelve un objeto Película que creamos nosotros mediante la clase Scanner
     */
    public static Pelicula leerDatosTeclado() {

        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pelicula pelicula = new Pelicula();

        System.out.println("Introduce el título: ");
        pelicula.setTitulo(sc.nextLine());

        System.out.println("Introduce los actores: ");
        pelicula.setActores(Arrays.asList(sc.nextLine().split(";")));

        System.out.println("Introduce los directores: ");
        pelicula.setDirectores(Arrays.asList(sc.nextLine().split(";")));

        System.out.println("Introduce la fecha: ");
        pelicula.setFechaSalida(LocalDate.parse(sc.nextLine(), formatear));

        System.out.println("Introduce el formato: ");
        pelicula.setFormato(sc.nextLine());

        return pelicula;

    }

    /**
     * Con este método sacamos la Lista del fichero binario y le añadimos la
     * película nueva
     *
     * @param PeliculaNueva Objeto película pasado por parámetro
     */
    public static void insertarObjeto(Pelicula PeliculaNueva) {

        List<Pelicula> listaPeliculas = leerPeliculasFichero();
        listaPeliculas.add(PeliculaNueva);

        try (FileOutputStream escritura = new FileOutputStream(archivo);
             ObjectOutputStream objeto = new ObjectOutputStream(escritura)) {

            objeto.writeObject(listaPeliculas);

        } catch (Exception e2) {
            System.out.println("Error sin más");
        }

        System.out.println("La película " + PeliculaNueva.getTitulo() + " ha sido añadadida al fichero binario");
    }

    /**
     *Método que modifica el formato de un objeto Película
     *
     * @param peliculaElegida Objeto Pelicula que será el título que queremos encontrar
     */
    public static void modificarPelicula(Pelicula peliculaElegida) {
        String opcion = "";
        do {
            System.out.println("Formato actual -> " + peliculaElegida.getFormato());
            System.out.print("\nFormato nuevo: ");
            opcion = sc.nextLine();

        } while (opcion.isEmpty() || opcion.isEmpty());

        List<Pelicula> listaPeliculas = leerPeliculasFichero();
        for (Pelicula peli : listaPeliculas) {
            if (peli.getTitulo().equalsIgnoreCase(peliculaElegida.getTitulo())) {
                peli.setFormato(opcion);
                break;
            }
        }

        try (FileOutputStream escritura = new FileOutputStream(archivo);
        ObjectOutputStream object = new ObjectOutputStream(escritura)){

            object.writeObject(listaPeliculas);

        } catch (NullPointerException e1) {
            System.out.println("\nNo existe en el fichero una película con el título seleccionado");
        } catch (Exception e2) {
            System.out.println("Error " + e2.getMessage());
        }
    }

    /**
     * Método que elimina una película de la lista del fichero
     *
     * @param peliculaElegida Objeto Pelicula que será el título que queremos encontrar
     */
    public static void eliminarPelicula(Pelicula peliculaElegida) {

        List<Pelicula> listaPeliculas = leerPeliculasFichero();

        if (listaPeliculas.isEmpty()) {
            System.out.println("\nEl fichero no tiene películas");
            return;
        }

        for (Pelicula peli : listaPeliculas) {
            if (peli.getTitulo().equalsIgnoreCase(peliculaElegida.getTitulo())) {
                listaPeliculas.remove(peli);
                break;
            }
        }

        try (FileOutputStream escritura = new FileOutputStream(archivo);
             ObjectOutputStream object = new ObjectOutputStream(escritura)) {

            object.writeObject(listaPeliculas);

            System.out.println("La película " + peliculaElegida.getTitulo() + " ha sido eliminada");

        } catch (NullPointerException e1) {
            System.out.println("\nNo existe en el fichero una película con el título seleccionado");
        } catch (Exception e2) {
            System.out.println("Error " + e2.getMessage());
        }
    }

    /**
     * Método que mediante una búsqueda del título que deseamos nos visualiza todos
     * sus atributos en la consola
     */
    public static void visualizarPelicula() {
        try {
            Pelicula peliculaElegida = elegirTituloPelicula("Que película quieres visualizar: ");
            System.out.println("\nVisualizando la película " + peliculaElegida.getTitulo() + peliculaElegida.Visualizar());

        } catch (NullPointerException e1) {
            System.out.println("\nNo existe en el fichero una película con el título seleccionado");
        } catch (Exception e2) {
            System.out.println("Error " + e2.getMessage());
        }
    }


    /**
     * Este método és el que me encuentra el título seleccionado por el usuario
     *
     * @return Este método devuelve un objeto Película que deseamos encontrar mediante el título
     * o null si no encontramos nada en el array almacenado
     */
    public static Pelicula elegirTituloPelicula(String mensaje) {
        List<Pelicula> listaPeliculas = leerPeliculasFichero();
        int contador = 1;
        Pelicula peliculaElegida = null;
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
                peliculaElegida = peli;
                existe = true;
                break;
            }
        }

        if (!existe) {
            return null;
        } else {
            return peliculaElegida;
        }

    }

    /**
     * @return Este método nos devuelve la lista almacenada en el fichero
     */
    public static List<Pelicula> leerPeliculasFichero() {
        List<Pelicula> listaPeliculas = new ArrayList<>();

        try (FileInputStream lectura = new FileInputStream(archivo);
             ObjectInputStream objtInp = new ObjectInputStream(lectura)) {

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            listaPeliculas = (List<Pelicula>) objtInp.readObject();

        } catch (EOFException eof) {
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }

        return listaPeliculas;
    }
}