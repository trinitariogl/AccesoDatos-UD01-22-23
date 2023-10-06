package org.example;


import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ListaCanciones lista= new ListaCanciones();
        lista.add(GetCancion1());
        lista.add(GetCancion2());
        GenerateXML(lista);
        System.out.println("XML generado correctamente");
        ReadXML();
    }
    public static Cancion GetCancion1(){
        Discografia discografia = new Discografia("MTV","1234","America",20000);
        Cancion cancion = new Cancion(1,"Silver Hand","Jhonny",5,2077,false,discografia);

       List<String> pais = List.of("España", "Francia","Portugal");
       cancion.setPaises(pais);
        return cancion;

    }
    public static Cancion GetCancion2(){
        Discografia discografia = new Discografia("VTM","1234","America",20000);
        Cancion cancion = new Cancion(2,"Dnah revlis","Jhonny",5,2077,false,discografia);
        return cancion;

    }
    public static void GenerateXML(ListaCanciones lista) throws FileNotFoundException {
        XStream xStream = new XStream();

        xStream.processAnnotations(Cancion.class);
        xStream.processAnnotations(Discografia.class);
        xStream.processAnnotations(ListaCanciones.class);

        xStream.addImplicitCollection(ListaCanciones.class,"lista");

        xStream.toXML(lista, new FileOutputStream("src/main/resources/canciones.xml"));

    }
    public static void ReadXML() throws FileNotFoundException {
        XStream xStream = new XStream();

        xStream.processAnnotations(Cancion.class);
        xStream.processAnnotations(Discografia.class);
        xStream.processAnnotations(ListaCanciones.class);

        xStream.addImplicitCollection(ListaCanciones.class,"lista");

        xStream.allowTypes(new Class[]{
                org.example.Cancion.class,
                org.example.Discografia.class,
                org.example.ListaCanciones.class,
        });

        ListaCanciones lista =(ListaCanciones) xStream.fromXML(
                new FileInputStream("src/main/resources/canciones.xml"));

        for (Cancion cancion: lista.getCancion()){
            System.out.println(cancion);
        }
    }
}