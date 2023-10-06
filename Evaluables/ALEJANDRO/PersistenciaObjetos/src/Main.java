import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //insertarObjetos(leerDatos());
        List<Pelicula> peliculas = leerArchivoObjetos();
        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula);
        }

    }

    public static Pelicula leerDatos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Pelicula pelicula = new Pelicula();

        System.out.print("Introduce el titulo: ");
        pelicula.setTitulo(sc.nextLine());

        System.out.print("Introduce los actores: ");
        pelicula.setActores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Introduce los directores: ");
        pelicula.setDirectores(Arrays.asList(sc.nextLine().split(";")));

        System.out.print("Introduce la fecha de salida: ");
        pelicula.setFechaSalida(LocalDate.parse(sc.nextLine(), formatter));

        System.out.print("Introduce el formato: ");
        pelicula.setFormato(sc.nextLine());

        return pelicula;
    }

    public static void insertarObjetos(Pelicula newMovie) {
        try {
            File archivo = new File("src/peliculas.dat");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            FileOutputStream fileOutput = new FileOutputStream(archivo);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(newMovie);
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Pelicula> leerArchivoObjetos() {
        List<Pelicula> listaPeliculas = null;
        try {
            listaPeliculas = new ArrayList<>();
            File archivo = new File("src/peliculas.dat");
            FileInputStream fileInput = new FileInputStream(archivo);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            try {
                while (true) {
                    Pelicula pelicula = (Pelicula) objectInput.readObject();
                    listaPeliculas.add(pelicula);
                }
            } catch (EOFException e) {
                objectInput.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            listaPeliculas = null;
        } catch (Exception e) {
            e.printStackTrace();
            listaPeliculas = null;
        }
        return listaPeliculas;
    }
}