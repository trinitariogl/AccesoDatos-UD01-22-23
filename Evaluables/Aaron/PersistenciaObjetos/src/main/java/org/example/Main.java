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

        //Pelicula pelicula = LeerDatosTeclado();
        //InsertarObjetos(pelicula);
        List<Pelicula> listaDePeliculas = LeerPeliculasFichero();

        for (Pelicula p: listaDePeliculas) {
            System.out.println(p.toString());
        }


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

    public static void InsertarObjetos(Pelicula nuevaPelicula) throws IOException {

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

            objectOutputStream.close();
        } catch (IOException ioex) {
            System.out.println("Error: " + ioex.getMessage());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

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

            }

        } catch (IOException ioex){
            System.out.println("Error: " + ioex.getMessage());
            listaPeliculas = null;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            listaPeliculas = null;
        }
        return  listaPeliculas;

    }
}