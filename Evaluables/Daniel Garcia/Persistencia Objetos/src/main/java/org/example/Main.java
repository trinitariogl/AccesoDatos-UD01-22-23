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

         Pelicula p = leerDatosTeclado();
         insertarObjeto(p);




        //Mostrar las películas
        List<Pelicula> pelis =  leerPeliculasFichero();
        for (Pelicula peli: pelis){
            System.out.println(peli);
        }

    }

    public static Pelicula leerDatosTeclado(){

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pelicula peli = new Pelicula();

        System.out.print("Introduzca el título: ");
        String tit = sc.nextLine();
        peli.setTitulo(tit);

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

        try{
            File archivo = new File("src/main/resources/peliculas.dat");
            if (!archivo.exists()){
                archivo.createNewFile();
            }
            FileOutputStream escritura = new FileOutputStream(archivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(escritura);

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
}