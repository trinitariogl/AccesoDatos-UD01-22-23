package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException{
        //Pelicula pelicula = leerDatos();
        //InsertarObjeto(pelicula);
        List<Pelicula> peliculas = leerPeliculasFichero();

        for(Pelicula peli :peliculas){
            System.out.println(peli);
        }

    }

    public static Pelicula leerDatos(){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Pelicula pelicula;
        String titulo;
        String formato;
        List<String> actores;
        List<String> directores;
        LocalDate fechaSalida;

        System.out.println("Introduce el titulo: ");
        titulo = sc.nextLine();
        System.out.println("Introduce los actores entre punto y coma: ");
        actores = Arrays.asList(sc.nextLine().split(";"));
        System.out.println("Introduce los directores entre punto y coma: ");
        directores=Arrays.asList(sc.nextLine().split(";"));
        System.out.println("Introduce la fecha de salida: ");
        fechaSalida = LocalDate.parse(sc.nextLine(), formater);
        System.out.println("Introduce el formato: ");
        formato = sc.nextLine();

        pelicula = new Pelicula(titulo, actores,directores,fechaSalida,formato);
        return pelicula;
    }
    public static void InsertarObjeto(Pelicula pelicula) {
        try{
            File archivo = new File("src/main/resources/peliculas.dat");

            if(!archivo.exists()){
                archivo.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pelicula);
            oos.close();
        }catch (IOException e){
            System.out.println("error fichero");
        }catch (Exception e){
            System.out.println("hubo problema");
        }
    }
    public static List<Pelicula> leerPeliculasFichero() throws IOException {
        List<Pelicula> listaPeli = null;
        try{
            listaPeli = new ArrayList<>();
            File archivo = new File("src/main/resources/peliculas.dat");
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try{
                while (true){
                    Pelicula pelicula = (Pelicula) ois.readObject();
                    listaPeli.add(pelicula);
                }
            }catch (EOFException eof){
                ois.close();
            }
        }catch (IOException io){
            System.out.println("error fichero");
            listaPeli=null;
        }catch (Exception e){
            System.out.println("problema");
            listaPeli=null;
        }

        return listaPeli;

    }
}