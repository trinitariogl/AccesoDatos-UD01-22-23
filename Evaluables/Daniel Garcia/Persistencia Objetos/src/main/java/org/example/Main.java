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
    static List<Pelicula> peliculas = new ArrayList<>();
    public static void main(String[] args) {

        Menu menu = new Menu();

        int opcion;
        System.out.println("*** Mi VideoClub ***");

        do {
            menu.mostrar();

            opcion = menu.leerOpcion();

            switch (opcion){
                case 1:
                    leerDatosTeclado();
                    break;
                case 2:
                    modificarPelicula();
                    break;
                case 3:
                    eliminarPelicula();
                    break;
                case 4:
                    visualizarPelicula();
                    break;
                case 5:
                    visualizarTodasLasPeliculas();
                    break;
                case 6:
                    System.out.println("FIN DEL PROGRAMA");
                    break;
            }

        } while (opcion != 6);

        /*
        Pelicula p = leerDatosTeclado();
        //insertarObjeto(p);

        peliculas =  leerPeliculasFichero();
        for (Pelicula peli: peliculas){
            System.out.println(peli);
        }*/

    }

    public static void leerDatosTeclado(){

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pelicula peli = new Pelicula();
        int num;
        peliculas = leerPeliculasFichero();

        do {
            System.out.print("Cuantas peliculas deseas almacenar? ");
            num = sc.nextInt();
        }while (num < 0);

        sc.nextLine();

        for (int i = 1; i <= num; i++) {
            System.out.println("Peliucula " + i + ":");
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
            peliculas.add(peli);
        }


        insertarObjetos();

    }

    public static void insertarObjetos() {

        try{
            File archivo = new File("src/main/resources/peliculas.dat");
            if (!archivo.exists()){
                archivo.createNewFile();
            }
            FileOutputStream escritura = new FileOutputStream(archivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(escritura);



            for (Pelicula newFilm: peliculas){
                objectOut.writeObject(newFilm);
            }

            objectOut.close();
        } catch (IOException ex){
            System.out.println("Error en fichero -> " + ex.getMessage());
        } catch (Exception e){
            System.out.println("Error genérico -> " + e.getMessage());
        }

    }

    public static List<Pelicula> leerPeliculasFichero() {

        List<Pelicula> listaPeliculas;
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

    public static void modificarPelicula(){
        String tit, formato;
        boolean existe = false;

        System.out.print("Introduce el titulo de la pelicula que quieras modificar el formato: ");
        tit = sc.nextLine();

        peliculas = leerPeliculasFichero();

        for (Pelicula p: peliculas){
            if(p.getTitulo().equalsIgnoreCase(tit)){
                System.out.print("Introduce el nuevo formato de la pelicula: ");
                formato = sc.nextLine();
                p.setFormato(formato);
                existe = true;
            }
        }

        if (!existe){
            System.out.println("La pelicula con titulo \"" + tit + "\" no existe.");
        }

        insertarObjetos();
    }

    public static void eliminarPelicula(){
        String tit;
        boolean existe = false;

        System.out.print("Introduce el titulo de la pelicula que quieras eliminar: ");
        tit = sc.nextLine();

        peliculas = leerPeliculasFichero();

        for (Pelicula p: peliculas){
            if(p.getTitulo().equalsIgnoreCase(tit)){
                peliculas.remove(p);
                existe = true;
                System.out.println("La pelicula \"" + tit + "\" se ha eliminado correctamente");
            }
        }

        if (!existe){
            System.out.println("La pelicula con titulo \"" + tit + "\" no existe.");
        }

        insertarObjetos();
    }

    public static void visualizarPelicula(){
        String tit;
        boolean existe = false;

        System.out.print("Introduce el titulo de la pelicula que quieras visualizar: ");
        tit = sc.nextLine();

        peliculas = leerPeliculasFichero();

        for (Pelicula p: peliculas) {
            if (p.getTitulo().equalsIgnoreCase(tit)) {
                System.out.println(p);
                existe = true;
            }
        }

        if (!existe){
            System.out.println("La pelicula con titulo \"" + tit + "\" no existe.");
        }


    }

    public static void visualizarTodasLasPeliculas(){
        System.out.println("Listado con todas las peliculas de nuestra base de datos.");
        peliculas =  leerPeliculasFichero();
        for (Pelicula peli: peliculas){
            System.out.println(peli);
        }
    }
}