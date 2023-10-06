package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        boolean salir = true;
        opcion = PedirOpcion(opcion);

        while(salir){

            if(opcion < 1 || opcion > 5){
                System.out.println("Tienes que poner una opción entre el 1 y el 3");
            }

            else if(opcion == 1){
                Insertar();
                opcion = PedirOpcion(opcion);
            }

            else if(opcion == 2){
                Modificar();
                opcion = PedirOpcion(opcion);
            }

            else if(opcion == 3){
                eliminarPeliculaPorTitulo();

                opcion = PedirOpcion(opcion);
            }

            else if(opcion == 4){
                System.out.println("Introduzca el título: ");
                String archivo = sc.nextLine();

                opcion = PedirOpcion(opcion);
            }

            else{
                salir = false;
            }
        }
    }

    static int PedirOpcion(int opcion){

        Scanner sc = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("** Mi videoClub \n\n 1. Insertar Película \n 2. Modificar Película \n 3. Eliminar Película \n 4. Visualizar Película \n 5. Salir \n\n Elige una opción: ");
        opcion = sc.nextInt();

        return(opcion);
    }

    static void Insertar() throws IOException {

        File ficherodat =
                new File("src/main/resources/peliculas.dat");

        // Abre el archivo en modo "append" para añadir contenido sin borrar lo existente
        RandomAccessFile aleatorio =
                new RandomAccessFile(ficherodat, "rw");

        System.out.println(" ");

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca el título: ");
        String titulo = sc.nextLine();

        System.out.println("Introduzca los actores separados por coma y un espacio: ");
        String actores = sc.nextLine();
        String[] actoresArray = actores.split(", ");

        System.out.println("Introduzca los directores separados por coma y un espacio: ");
        String directores = sc.nextLine();
        String[] directoresArray = directores.split(", ");

        System.out.println("Introduzca la fecha de salida en formato 10-10-2023: ");
        String fechaTexto = sc.nextLine();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaTexto, formatoFecha);

        System.out.println("Introduzca el formato: ");
        String formato = sc.nextLine();

        Pelicula peli = new Pelicula(titulo, actoresArray, directoresArray, fecha, formato);

        // Mueve el puntero al final del archivo antes de escribir
        aleatorio.seek(aleatorio.length());

        // Escribe los datos de la película
        aleatorio.writeUTF(peli.visualizar());

        // Cierra el archivo
        aleatorio.close();
    }

    static void Modificar() throws IOException {
        File ficherodat = new File("src/main/resources/peliculas.dat");

        // Abre el archivo en modo "lectura/escritura"
        RandomAccessFile aleatorio = new RandomAccessFile(ficherodat, "rw");

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca el título de la película que desea modificar: ");
        String tituloBuscar = sc.nextLine();

        boolean peliculaEncontrada = false;
        long posicionInicio = 0;

        while (aleatorio.getFilePointer() < aleatorio.length()) {
            posicionInicio = aleatorio.getFilePointer();
            String linea = aleatorio.readLine();
            if (linea.startsWith("Título: ")) {
                String titulo = linea.substring(8);
                if (titulo.equals(tituloBuscar)) {
                    peliculaEncontrada = true;
                    break;
                }
            }
        }

        if (peliculaEncontrada) {
            // Lee los datos existentes de la película
            String actores = aleatorio.readLine().substring(9);
            String directores = aleatorio.readLine().substring(12);
            String fechaSalidaTexto = aleatorio.readLine().substring(16);
            String formato = aleatorio.readLine().substring(9);

            // Solicita los nuevos datos de la película
            System.out.println("Introduzca los nuevos actores separados por coma y un espacio: ");
            String nuevosActores = sc.nextLine();
            String[] nuevosActoresArray = nuevosActores.split(", ");

            System.out.println("Introduzca los nuevos directores separados por coma y un espacio: ");
            String nuevosDirectores = sc.nextLine();
            String[] nuevosDirectoresArray = nuevosDirectores.split(", ");

            System.out.println("Introduzca la nueva fecha de salida en formato 10-10-2023: ");
            String nuevaFechaTexto = sc.nextLine();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate nuevaFecha = LocalDate.parse(nuevaFechaTexto, formatoFecha);

            System.out.println("Introduzca el nuevo formato: ");
            String nuevoFormato = sc.nextLine();

            // Mueve el puntero al inicio de la película a modificar
            aleatorio.seek(posicionInicio);

            // Escribe los nuevos datos de la película
            aleatorio.writeBytes("Título: " + tituloBuscar + "\n");
            aleatorio.writeBytes("Actores: " + String.join(", ", nuevosActoresArray) + "\n");
            aleatorio.writeBytes("Directores: " + String.join(", ", nuevosDirectoresArray) + "\n");
            aleatorio.writeBytes("Fecha de Salida: " + nuevaFecha.format(formatoFecha) + "\n");
            aleatorio.writeBytes("Formato: " + nuevoFormato + "\n");

            System.out.println("Película modificada exitosamente.");
        } else {
            System.out.println("No se encontró una película con el título proporcionado.");
        }

        // Cierra el archivo
        aleatorio.close();
    }

    static void eliminarPeliculaPorTitulo() throws IOException {
        File ficherodat = new File("src/main/resources/peliculas.dat");

        // Abre el archivo en modo "lectura/escritura"
        RandomAccessFile aleatorio = new RandomAccessFile(ficherodat, "rw");

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca el título de la película que desea eliminar: ");
        String tituloEliminar = sc.nextLine();

        List<String> peliculasNoEliminadas = new ArrayList<>();

        // Lee cada película del archivo una por una
        while (aleatorio.getFilePointer() < aleatorio.length()) {
            String linea = aleatorio.readLine();
            if (linea.startsWith("Título: ")) {
                String titulo = linea.substring(8);
                // Si el título no coincide, agrega la película a la lista de películas no eliminadas
                if (titulo.equals(tituloEliminar)) {
                    peliculasNoEliminadas.add(linea);
                    // Lee y agrega las líneas de actores, directores, fecha y formato
                    for (int i = 0; i < 4; i++) {
                        peliculasNoEliminadas.add(aleatorio.readLine());
                    }
                }
            }
        }

        // Cierra el archivo original
        aleatorio.close();

        // Vuelve a abrir el archivo en modo de escritura
        aleatorio = new RandomAccessFile(ficherodat, "rw");

        // Escribe las películas no eliminadas de nuevo en el archivo
        for (String linea : peliculasNoEliminadas) {
            aleatorio.writeBytes(linea + "\n");
        }

        // Cierra el archivo nuevamente
        aleatorio.close();

        System.out.println("Película eliminada exitosamente.");
    }
}


