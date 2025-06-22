/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import org.estructuras.ListaSimple;

/**
 * La clase `ResultadoBusqueda` es un objeto de valor (Value Object)
 * que encapsula los resultados de una operación de búsqueda de múltiples palabras
 * en el tablero de la sopa de letras. Contiene la lista de palabras encontradas
 * y el tiempo total que tomó realizar la búsqueda.
 *
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class ResultadoBusqueda {

    /**
     * Una {@link ListaSimple} de {@link String} que contiene todas las palabras
     * que fueron encontradas en el tablero durante la operación de búsqueda.
     */
    private ListaSimple<String> palabrasEncontradas;
    /**
     * El tiempo total transcurrido para la operación de búsqueda,
     * generalmente expresado en nanosegundos para alta precisión.
     */
    private long tiempoTranscurrido;

    /**
     * Constructor de la clase `ResultadoBusqueda`.
     * Inicializa el objeto con la lista de palabras encontradas y el tiempo transcurrido.
     *
     * @param palabrasEncontradas Una {@link ListaSimple} de {@link String} con las palabras halladas.
     * @param tiempoTranscurrido El tiempo total de la búsqueda en nanosegundos.
     */
    public ResultadoBusqueda(ListaSimple<String> palabrasEncontradas, long tiempoTranscurrido) {
        this.palabrasEncontradas = palabrasEncontradas;
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    /**
     * Obtiene la lista de palabras que fueron encontradas durante la búsqueda.
     *
     * @return La {@link ListaSimple} de {@link String} de palabras encontradas.
     */
    public ListaSimple<String> getPalabrasEncontradas() {
        return palabrasEncontradas;
    }

    /**
     * Obtiene el tiempo total que tomó la operación de búsqueda.
     *
     * @return El tiempo transcurrido en nanosegundos.
     */
    public long getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }
}