package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Mi VideoClub");
        System.out.println("-------------");
        Menu.mostrarOpciones();
        switch (Menu.leerOpcion()){
            case 1:
                System.out.println("Introduce la cantidad de peliculas: ");
                Scanner sc = new Scanner(System.in);
                int repetir2 = sc.nextInt();
                for (int i = 0; i < repetir2; i++) {
                    insertarObjeto(leerDatosTeclado());
                }

               break;
            case 2:
                modificarPelicula();
                break;
            case 3:
                eliminarPelicula();
                break;
            case 4:
                List<Pelicula> pel =leerPeliculasFichero();
                String titulo;
                System.out.println("Introduce el titulo que quiere buscar: ");
                Scanner scc2 = new Scanner(System.in);
                titulo = scc2.nextLine();
                for (int i = 0; i <pel.size(); i++) {
                    if (pel.get(i).getTitulo().equalsIgnoreCase(titulo)){
                        System.out.println(pel.get(i));
                    }
                }
                break;
            case 5:
                System.out.println("Fin del programa");
                break;
        }

    }

    public static Pelicula leerDatosTeclado(){

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pelicula peli = new Pelicula();

        System.out.print("Introduzca el título: ");
        peli.setTitulo(sc.nextLine());

        System.out.print("Introduzca actores: ");
        peli.setActores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Introduzca directores: ");
        peli.setDirectores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Introduzca fecha de salida: ");
        peli.setFechaSalida(LocalDate.parse(sc.nextLine(), formatoFecha));

        System.out.print("Introduce formato: ");
        peli.setFormato(sc.nextLine());

        return peli;
    }

    public static void insertarObjeto(Pelicula newFilm) {
        List<Pelicula> listado=null;

        try{
            File archivo = new File("src/main/resources/peliculas.dat");
            if (!archivo.exists()){
                archivo.createNewFile();
            }else{
                listado = leerPeliculasFichero();
            }


            FileOutputStream escritura = new FileOutputStream(archivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(escritura);
            if(listado!=null){
                for (int i = 0; i < listado.size(); i++) {
                    objectOut.writeObject(listado.get(i));
                }
            }

            objectOut.writeObject(newFilm);
            objectOut.close();
        } catch (IOException ex){
            System.out.println("Error en fichero -> " + ex.getMessage());
        } catch (Exception e){
            System.out.println("Error genérico -> " + e.getMessage());
        }

    }

    public static List<Pelicula> leerPeliculasFichero() {

        List<Pelicula> listaPeliculas = null;
        try {
            listaPeliculas = new ArrayList<>();
            File archivo = new File("src/main/resources/peliculas.dat");
            FileInputStream lectura = new FileInputStream(archivo);
            ObjectInputStream objectInput = new ObjectInputStream(lectura);

            try{
                while (true){
                    Pelicula peli = (Pelicula) objectInput.readObject();
                    listaPeliculas.add(peli);
                }

            }  catch (EOFException eof){
                objectInput.close();
                System.out.println("Se ha leído correctamente.");
            }

        }catch (IOException ex){
            System.out.println("Error -> " + ex.getMessage());
            listaPeliculas = null;
        } catch (Exception e){
            System.out.println("Error -> " + e.getMessage());
            listaPeliculas = null;
        }

        return listaPeliculas;
    }

    public static void modificarPelicula() {
        System.out.print("Introduzca el título de la película a modificar: ");
        String titulo = sc.nextLine();

        System.out.print("Introduzca el nuevo formato: ");
        String nuevoFormato = sc.nextLine();


        List<Pelicula> listaPeliculas = leerPeliculasFichero();


        for (Pelicula peli : listaPeliculas) {
            if (peli.getTitulo().equalsIgnoreCase(titulo)) {
                peli.setFormato(nuevoFormato);
                System.out.println("Película modificada exitosamente.");

            }
        }


        try {
            File archivo = new File("src/main/resources/peliculas.dat");
            FileOutputStream escritura = new FileOutputStream(archivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(escritura);

            for (Pelicula peli : listaPeliculas) {
                objectOut.writeObject(peli);
            }

            objectOut.close();
        } catch (IOException ex) {
            System.out.println("Error en fichero -> " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error genérico -> " + e.getMessage());
        }
    }
    public static void eliminarPelicula() {

        System.out.print("Introduzca el título de la película a eliminar: ");
        String titulo = sc.nextLine();


        List<Pelicula> listaPeliculas = leerPeliculasFichero();


        for (int i = 0; i < listaPeliculas.size(); i++) {
            if (listaPeliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                listaPeliculas.remove(i);
                System.out.println("Película eliminada exitosamente.");
                break;
            }
        }


        try {
            File archivo = new File("src/main/resources/peliculas.dat");
            FileOutputStream escritura = new FileOutputStream(archivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(escritura);

            for (Pelicula peli : listaPeliculas) {
                objectOut.writeObject(peli);
            }

            objectOut.close();
        } catch (IOException ex) {
            System.out.println("Error en fichero -> " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error genérico -> " + e.getMessage());
        }
    }
}
