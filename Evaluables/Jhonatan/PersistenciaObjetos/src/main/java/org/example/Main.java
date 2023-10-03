package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String ruta = "src/main/resources/peliculas.dat";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String titulo;

        System.out.println("Mi VideoClub");
        System.out.println("------------");

        do{
            opcion = menu();
            switch (opcion){
                case 1:
                    int cuantas;
                    System.out.print("Cuantas películas va a introducir: ");
                    cuantas = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < cuantas; i++) {
                        Pelicula pelicula = leerDatosTeclado();
                        insertarObjeto(pelicula);
                    }
                    break;
                case 2:
                    System.out.print("Introduzca el título de la película: ");
                    titulo = sc.nextLine();
                    modificarPelicula(titulo);
                    break;
                case 3:
                    System.out.print("Introduzca el título de la película: ");
                    titulo = sc.nextLine();
                    eliminarPelicula(titulo);
                    break;
                case 4:
                    System.out.print("Introduzca el título de la película: ");
                    titulo = sc.nextLine();
                    List<Pelicula> peliculas = leerPeliculasFichero();
                    for(Pelicula peli: peliculas){
                        if(peli.getTitulo().equalsIgnoreCase(titulo)){
                            peli.visualizar();
                        }
                    }
                    break;
                default:
            }
        }while (opcion != 5);

    }

    public static Pelicula leerDatosTeclado(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Pelicula pelicula = new Pelicula();

        System.out.print("Introduzca el título: ");
        pelicula.setTitulo(sc.nextLine());

        System.out.print("Introduzca actores: ");
        pelicula.setActores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Introduzca directores: ");
        pelicula.setDirectores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Introduzca fecha de salida: ");
        pelicula.setFechaSalida(LocalDate.parse(sc.nextLine(), formatter));

        System.out.print("Introduzca formato: ");
        pelicula.setFormato(sc.nextLine());

        return pelicula;
    }

    public static void insertarObjeto(Pelicula nuevaPelicula){
        boolean existe = false;
        List<Pelicula> peliculas = null;
        try {
            File archivo = new File(ruta);

            if (!archivo.exists()) {
               archivo.createNewFile();
            }else{
                existe = true;
                peliculas = leerPeliculasFichero();
            }

            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream salida = new ObjectOutputStream(fos);

            if(existe){
                for (Pelicula pelicula: peliculas){
                    salida.writeObject(pelicula);
                }
            }

            salida.writeObject(nuevaPelicula);
            fos.close();
            salida.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static List<Pelicula> leerPeliculasFichero(){
        List<Pelicula> listaPeliculas = null;
        File archivo = new File(ruta);

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream entrada = new ObjectInputStream(fis)){
            listaPeliculas = new ArrayList<>();

            while(true){
                Pelicula pelicula = (Pelicula) entrada.readObject();
                listaPeliculas.add(pelicula);
            }

        }catch (EOFException e){
            System.out.print("");
        }catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
            listaPeliculas = null;
        }

        return listaPeliculas;
    }

    public static void modificarPelicula(String titulo){
        Scanner sc = new Scanner(System.in);
        List<Pelicula> peliculas = null;
        File archivo = new File(ruta);
        String formato;

        if (!archivo.exists()) {
            System.out.println("El fichero no existe");
            return;
        }
        peliculas = leerPeliculasFichero();
        for(Pelicula pelicula: peliculas){
            if(pelicula.getTitulo().equalsIgnoreCase(titulo)){
                System.out.print("Introduce el nuevo formato: ");
                formato = sc.nextLine();
                pelicula.setFormato(formato);
                break;
            }
        }


        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream salida = new ObjectOutputStream(fos);) {

            for(Pelicula pelicula: peliculas){
                salida.writeObject(pelicula);
            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void eliminarPelicula(String titulo){
        boolean existe = false;
        List<Pelicula> peliculas = null;
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("El fichero no existe");
            return;
        }
        peliculas = leerPeliculasFichero();
        for(Pelicula pelicula: peliculas){
            if(pelicula.getTitulo().equalsIgnoreCase(titulo)){
                peliculas.remove(pelicula);
                break;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream salida = new ObjectOutputStream(fos);) {

            for(Pelicula pelicula: peliculas){
                salida.writeObject(pelicula);
            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

    public static int menu(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println("Menú:");
        System.out.println("1. Insertar Película");
        System.out.println("2. Modificar Película");
        System.out.println("3. Eliminar Película");
        System.out.println("4. Visualizar Película");
        System.out.println("5. Salir");
        System.out.println();
        do {
            System.out.print("Seleccione una opcion: ");
            while (!sc.hasNextInt()) {
                System.out.println("Valor no válido");
                sc.next();
                System.out.print("Seleccione una opcion: ");
            }
            opcion = sc.nextInt();
        }while(opcion < 1 || opcion > 5);
        return opcion;
    }

}