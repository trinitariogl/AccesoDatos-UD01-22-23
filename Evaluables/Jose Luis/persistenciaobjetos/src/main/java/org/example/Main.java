package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner sc =new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //Peliculas pelicula=leerDatosTeclado();
        //InsertarObjeto(pelicula);
        List<Peliculas>peliculass=LeerPeliculasFichero();
        for (Peliculas pel:peliculass){
            System.out.println(pel.toString());
        }
              {

        }
    }
    public static Peliculas leerDatosTeclado(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Peliculas pelicula=new Peliculas();
        System.out.println("Introduce el titulo: ");
        pelicula.setTitulo(sc.nextLine());
        System.out.println("Introduce los actores: ");
        pelicula.setActores(Arrays.asList(sc.nextLine().split(";")));
        System.out.println("Introduce los directores: ");
        pelicula.setDirectores(Arrays.asList(sc.nextLine().split(";")));
        System.out.println("Introduce la fecha: ");
        pelicula.setFechaSalida(LocalDate.parse(sc.nextLine(),formatter));
        System.out.println("Introduce formato: ");
        pelicula.setFormato(sc.nextLine());

        return pelicula;

    }
    public static void InsertarObjeto(Peliculas nuevaPelicula) throws IOException {
        try{
        File archivo=new File("src/main/resources/peliculas.dat");

        if(!archivo.exists()){
            archivo.createNewFile();
        }
        FileOutputStream fileout=new FileOutputStream(archivo);
        ObjectOutputStream objectout=new ObjectOutputStream(fileout);

        objectout.writeObject(nuevaPelicula);

        objectout.close();
    }catch (Exception ex){
            System.out.println("Error");
        }
}

public static List<Peliculas> LeerPeliculasFichero() throws IOException {
        List<Peliculas>ListaPeliculas=null;
        try {
            ListaPeliculas = new ArrayList<>();
            File archivo = new File("src/main/resources/peliculas.dat");
            FileInputStream fileInput = new FileInputStream(archivo);
            ObjectInputStream objectinput = new ObjectInputStream(fileInput);

            try {
                while (true) {
                    Peliculas pelicula = (Peliculas) objectinput.readObject();
                    ListaPeliculas.add(pelicula);
                }
            } catch (EOFException eof) {
                objectinput.close();
            }
        }catch (IOException io) {
            System.out.println("error de fichero");
            ListaPeliculas=null;
        }

        catch (Exception ex){
            System.out.println("error generico");
            ListaPeliculas=null;
        }
    return ListaPeliculas;
}

}
