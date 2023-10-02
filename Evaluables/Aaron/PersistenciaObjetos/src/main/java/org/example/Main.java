package org.example;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        boolean repetir;
        int opcion = 0;
        String titulo;

        File archivo = new File("src/main/resources/peliculas.dat");
        if(!archivo.exists()){
            archivo.createNewFile();
        }

        System.out.println("Mi VideoClub");
        System.out.println("- - - - - - - - ");
        do {
            System.out.println("Menu: ");
            System.out.println("1. Insertar Pelicula");
            System.out.println("2. Modificar Pelicula");
            System.out.println("3. Eliminar Pelicula");
            System.out.println("4. Visualizar Pelicula");
            System.out.println("5. Salir");

            do {
                System.out.println("Seleccione una opcion: ");
                if (!sc.hasNextInt()) {
                    System.out.println("debe introducir un numero.");
                    repetir = false;
                } else {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion < 1 || opcion > 5) {
                        System.out.println("El numero debe ser entre 1 y 5");
                        repetir = false;
                    } else {
                        repetir = true;
                    }
                }
            } while (!repetir);


            switch (opcion) {
                case 1:
                    InsertarObjetos();
                    break;
                case 2:
                    System.out.println("Introduce el titulo de la pelicula: ");
                    titulo = sc.nextLine();
                    modificarPelicula(titulo);
                    break;
                case 3:
                    System.out.println("Introduce el titulo de la pelicula");
                    titulo = sc.nextLine();
                    eliminarPelicula(titulo);
                    break;
                case 4:
                    System.out.println("Introduce el titulo de la pelicula: ");
                    titulo = sc.nextLine();
                    VisualizarPelicula(titulo);
                    break;
                case 5:
                    System.out.println("Salir.");
                    break;
            }

        } while (opcion != 5);


    }

    public static Pelicula LeerDatosTeclado() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Pelicula pelicula = new Pelicula();

        System.out.println("Introduce el titulo: ");
        pelicula.setTitulo(sc.nextLine());

        System.out.println("Introduce los actores(separados por  " + " ;)" + ": ");
        pelicula.setActores(Arrays.asList(sc.nextLine().split(";")));

        System.out.println("Introduce los directores(separados por " + ";) " + ":");
        pelicula.setDirectores(Arrays.asList(sc.nextLine().split(";")));

        System.out.println("Introduce la fecha: ");
        pelicula.setFechaSalida(LocalDate.parse(sc.nextLine(), formatter));

        System.out.println("Introduce formato: ");
        pelicula.setFormato(sc.nextLine());


        return pelicula;
    }

    public static List<Pelicula> LeerPeliculasFichero() throws IOException {

        List<Pelicula> listaPeliculas;

        try {
            listaPeliculas = new ArrayList<>();

            File archivo = new File("src/main/resources/peliculas.dat");
            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while (true) {
                try {
                    Pelicula pelicula = (Pelicula) objectInputStream.readObject();
                    listaPeliculas.add(pelicula);
                } catch (EOFException eofex) {
                    objectInputStream.close();
                    break;
                }
            }

        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());
            listaPeliculas = null;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            listaPeliculas = null;
        }
        return listaPeliculas;

    }

    public static void InsertarObjetos(){

        List<Pelicula> listaPelicula;
        try {
            File archivo = new File("src/main/resources/peliculas.dat");

            listaPelicula = LeerPeliculasFichero();

            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            //nueva pelicula
            Pelicula pelicula = LeerDatosTeclado();
            listaPelicula.add(pelicula);

            objectOutputStream.writeObject(listaPelicula);
            objectOutputStream.close();

        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void modificarPelicula(String tituloPelicula){

        List<Pelicula> listaPelicula;
        try {
            Scanner sc = new Scanner(System.in);
            String nuevoFormato;
            boolean buscarPelicula = false;

            File archivo = new File("src/main/resources/peliculas.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            listaPelicula = LeerPeliculasFichero();

            for (Pelicula pelicula : listaPelicula) {
                if (pelicula.getTitulo().equalsIgnoreCase(tituloPelicula)) {
                    System.out.println("Introduce el nuevo formato: ");
                    nuevoFormato = sc.nextLine();
                    pelicula.setFormato(nuevoFormato);
                    buscarPelicula = true;
                    break;
                }
            }
            //escribir nueva lista
            objectOutputStream.writeObject(listaPelicula);

            if (!buscarPelicula) {
                System.out.println("La pelicula no existe.");
            }

            objectOutputStream.close();

        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception ex) {
            System.out.println("ERror: " + ex.getMessage());
        }
    }

    public static void eliminarPelicula(String tituloPelicula) {

        List<Pelicula> listaPelicula;
        try {
            boolean buscarPelicula = false;

            File archivo = new File("src/main/resources/peliculas.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            listaPelicula = LeerPeliculasFichero();

            for (Pelicula pelicula : listaPelicula) {
                if (pelicula.getTitulo().equalsIgnoreCase(tituloPelicula)) {
                    listaPelicula.remove(pelicula);
                    buscarPelicula = true;
                    break;
                }
            }

            //escribir nueva lista
            objectOutputStream.writeObject(listaPelicula);

            if (!buscarPelicula) {
                System.out.println("La pelicula no existe.");
            }

            objectOutputStream.close();

        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception ex) {
            System.out.println("ERror: " + ex.getMessage());
        }

    }

    public static void VisualizarPelicula(String tituloPelicula){

        List<Pelicula> listaPelicula;
        try {
            boolean buscarPelicula = false;


            listaPelicula = LeerPeliculasFichero();

            for (Pelicula pelicula : listaPelicula) {
                if (pelicula.getTitulo().equalsIgnoreCase(tituloPelicula)) {
                    System.out.println(pelicula);
                    buscarPelicula = true;
                    break;
                }
            }

            if (!buscarPelicula) {
                System.out.println("La pelicula no existe.");
            }

        } catch (Exception ex) {
            System.out.println("ERror: " + ex.getMessage());
        }

    }


}