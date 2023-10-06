package org.example;

public interface RepoSitios {
    Sitio elemento(int id); // Busca el sitio dado su id
    void anyade(Sitio s);   // Añade un sitio
    int nuevo();            // Añade un sitio vacio y devuelve su id
    void borrar(int id);    // Elimina el sitio con el id indicado
    int tamanyo();          // Devuelve el número de elementos
    void actualiza(int id, Sitio s); // Reemplaza el sitio
}
