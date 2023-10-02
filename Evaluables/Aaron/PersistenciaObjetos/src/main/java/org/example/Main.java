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
        boolean repetir = false;
        int opcion = 0;
        String titulo;

        //Pelicula pelicula = LeerDatosTeclado();
        //InsertarObjetos(pelicula);
//        List<Pelicula> listaDePeliculas = LeerPeliculasFichero();
//
//        for (Pelicula p : listaDePeliculas) {
//            System.out.println(p.toString());
//        }


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
                    InsertarObjetos(LeerDatosTeclado());
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

        List<Pelicula> listaPeliculas = new ArrayList<>();

        try {
            listaPeliculas = new ArrayList<>();

            File archivo = new File("src/main/resources/peliculas.dat");
            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                while (true) {
                    Pelicula pelicula = (Pelicula) objectInputStream.readObject();
                    listaPeliculas.add(pelicula);
                }
            } catch (EOFException eofex) {
                objectInputStream.close();
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

    public static void InsertarObjetos(Pelicula nuevaPelicula) throws IOException {


        try {
            File archivo = new File("src/main/resources/peliculas.dat");
            List<Pelicula> listaPelicula = new ArrayList<>();

            if (!archivo.exists()) {
                archivo.createNewFile();
            }else{
                listaPelicula = LeerPeliculasFichero();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            listaPelicula.add(nuevaPelicula);

            objectOutputStream.writeObject(listaPelicula);
            objectOutputStream.close();

        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void modificarPelicula(String tituloPelicula) throws IOException{

        try {
            Scanner sc = new Scanner(System.in);
            String nuevoFormato;
            boolean buscarPelicula = false;

            File archivo = new File("src/main/resources/peliculas.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            List<Pelicula> listaPelicula = new ArrayList<>();

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

            if (buscarPelicula != true) {
                System.out.println("La pelicula no existe.");
            }

            objectOutputStream.close();

        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception ex) {
            System.out.println("ERror: " + ex.getMessage());
        }
    }

    public static void eliminarPelicula (String tituloPelicula){

        try {
            Scanner sc = new Scanner(System.in);
            boolean buscarPelicula = false;

            File archivo = new File("src/main/resources/peliculas.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            List<Pelicula> listaPelicula = new ArrayList<>();

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

            if (buscarPelicula != true) {
                System.out.println("La pelicula no existe.");
            }

            objectOutputStream.close();

        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception ex) {
            System.out.println("ERror: " + ex.getMessage());
        }

    }

    public static void VisualizarPelicula (String tituloPelicula) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            boolean buscarPelicula = false;

            File archivo = new File("src/main/resources/peliculas.dat");
            List<Pelicula> listaPelicula = new ArrayList<>();

            listaPelicula = LeerPeliculasFichero();

            for (Pelicula pelicula : listaPelicula) {
                if (pelicula.getTitulo().equalsIgnoreCase(tituloPelicula)) {
                    System.out.println(pelicula.toString());
                    buscarPelicula= true;
                    break;
                }
            }

            if (buscarPelicula != true) {
                System.out.println("La pelicula no existe.");
            }

        } catch (Exception ex) {
            System.out.println("ERror: " + ex.getMessage());
        }

    }


}