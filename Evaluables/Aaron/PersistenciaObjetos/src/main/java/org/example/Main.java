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
        //Pelicula pelicula = LeerDatosTeclado();
        //InsertarObjetos(pelicula);
        List<Pelicula> listaDePeliculas = LeerPeliculasFichero();

        for (Pelicula p : listaDePeliculas) {
            System.out.println(p.toString());
        }


        System.out.println("Mi VideoClub");
        System.out.println("- - - - - - - - ");
        do {
            do {

                System.out.println("Seleccione una opcion: ");
                opcion = sc.nextInt();
                if (!sc.hasNextInt()) {
                    System.out.println("debe introducir un numero.");
                    repetir = false;
                } else {
                    if (opcion < 1 || opcion > 5) {
                        System.out.println("El numero debe ser entre 1 y 5");
                        repetir = false;
                    } else {
                        repetir = true;
                    }
                }

            } while (!repetir);

            System.out.println("Menu: ");
            System.out.println("1. Insertar Pelicula");
            System.out.println("2. Modificar Pelicula");
            System.out.println("3. Eliminar Pelicula");
            System.out.println("4. Visualizar Pelicula");
            System.out.println("5. Salir");

            switch (opcion) {
                case 1:
                    InsertarObjetos(LeerDatosTeclado());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

        List<Pelicula> listaPeliculas = null;

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

        List<Pelicula> listaPelicula = new ArrayList<>();

        try {
            File archivo = new File("src/main/resources/peliculas.dat");

            if (archivo.exists()) {
                System.out.println("El archivo ya existe.");
            } else {
                archivo.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(archivo);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(nuevaPelicula);

            listaPelicula = LeerPeliculasFichero();

            listaPelicula.add(nuevaPelicula);

            objectOutputStream.close();
        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void modificarPelicula(){

        String nombre;
        System.out.println("Introduce el nombre de la pelicula");



    }


}