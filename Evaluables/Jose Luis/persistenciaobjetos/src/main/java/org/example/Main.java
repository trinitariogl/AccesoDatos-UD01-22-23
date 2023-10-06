package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner sc =new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //Peliculas pelicula=leerDatosTeclado();
        //InsertarObjeto(pelicula);
        menu menu = new menu();
        int opcion;
        do {
            menu.mostrar();
            opcion = menu.leer();

            switch (opcion) {
                case 1:
                    System.out.println("Numero de peliculas: ");
                    int numero=sc.nextInt();
                    for (int i=0; i<=numero; i++) {
                        InsertarObjeto(leerDatosTeclado());
                    }
                    break;
                case 2:
                    ModificarPelicula();
                    break;
                case 3:
                    EliminarPelicula();
                    break;
                case 4:
                    List<Peliculas>peliculass=LeerPeliculasFichero();
                    for (Peliculas pel:peliculass){
                        System.out.println(pel.toString());
                    }
                    break;
            }
        } while (opcion != 0);




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


    private static void ModificarPelicula() throws IOException {
        List<Peliculas> peliculas = LeerPeliculasFichero();
        if (peliculas != null && !peliculas.isEmpty()) {
            Scanner input = new Scanner(System.in);

            System.out.println("Introduce el título de la película que deseas modificar: ");
            String tituloBusqueda = input.nextLine();

            boolean encontrada = false;

            for (int i = 0; i < peliculas.size(); i++) {
                Peliculas pelicula = peliculas.get(i);
                if (pelicula.getTitulo().equalsIgnoreCase(tituloBusqueda)) {
                    Peliculas nuevaPelicula = leerDatosTeclado();
                    peliculas.set(i, nuevaPelicula);
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("Película no encontrada.");
            } else {
                try {
                    FileOutputStream fileout = new FileOutputStream("src/main/resources/peliculas.dat");
                    ObjectOutputStream objectout = new ObjectOutputStream(fileout);
                    for (Peliculas pelicula : peliculas) {
                        objectout.writeObject(pelicula);
                    }
                    objectout.close();
                    System.out.println("Película modificada con éxito.");
                } catch (IOException ex) {
                    System.out.println("Error al escribir en el archivo.");
                }
            }
        } else {
            System.out.println("No hay películas en la lista.");
        }
    }

    private static void EliminarPelicula() throws IOException {
        List<Peliculas> peliculas = LeerPeliculasFichero();
        if (peliculas != null && !peliculas.isEmpty()) {
            Scanner input = new Scanner(System.in);

            System.out.println("Introduce el título de la película que deseas eliminar: ");
            String tituloBusqueda = input.nextLine();

            boolean encontrada = false;

            Iterator<Peliculas> iterator = peliculas.iterator();
            while (iterator.hasNext()) {
                Peliculas pelicula = iterator.next();
                if (pelicula.getTitulo().equalsIgnoreCase(tituloBusqueda)) {
                    iterator.remove();
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("Película no encontrada.");
            } else {
                // Actualizar el archivo sin la película eliminada
                try {
                    FileOutputStream fileout = new FileOutputStream("src/main/resources/peliculas.dat");
                    ObjectOutputStream objectout = new ObjectOutputStream(fileout);
                    for (Peliculas pelicula : peliculas) {
                        objectout.writeObject(pelicula);
                    }
                    objectout.close();
                    System.out.println("Película eliminada con éxito.");
                } catch (IOException ex) {
                    System.out.println("Error al escribir en el archivo.");
                }
            }
        } else {
            System.out.println("No hay películas en la lista.");
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
