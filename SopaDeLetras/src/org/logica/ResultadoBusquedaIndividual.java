/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import org.estructuras.ListaSimple;

/**
 * La clase `ResultadoBusquedaIndividual` es un objeto de valor (Value Object)
 * que encapsula los resultados de una operación de búsqueda de una única palabra
 * en el tablero de la sopa de letras.
 * Incluye un indicador de si la palabra fue encontrada, el camino de vértices
 * recorrido (si aplica, como en BFS) y el tiempo que tomó la búsqueda.
 *
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class ResultadoBusquedaIndividual {

    /**
     * Indicador booleano que es `true` si la palabra fue encontrada en el tablero,
     * y `false` en caso contrario.
     */
    private final boolean encontrada;
    /**
     * Una {@link ListaSimple} de objetos {@link Vertice} que representa el camino
     * que formó la palabra en el tablero. Puede estar vacía si el algoritmo de
     * búsqueda no retorna el camino (e.g., DFS simple) o si la palabra no fue encontrada.
     */
    private final ListaSimple<Vertice> camino; // Vacío si se usa DFS
    /**
     * El tiempo transcurrido para la búsqueda de la palabra, expresado en nanosegundos.
     */
    private final long tiempoTranscurridoNs;

    /**
     * Constructor de la clase `ResultadoBusquedaIndividual`.
     *
     * @param encontrada       `true` si la palabra fue encontrada, `false` en caso contrario.
     * @param camino           La {@link ListaSimple} de {@link Vertice} que forma la palabra encontrada.
     * Puede ser una lista vacía si el camino no es relevante o la palabra no se encontró.
     * @param tiempoTranscurridoNs El tiempo en nanosegundos que tomó la búsqueda.
     */
    public ResultadoBusquedaIndividual(boolean encontrada, ListaSimple<Vertice> camino, long tiempoTranscurridoNs) {
        this.encontrada = encontrada;
        this.camino = camino;
        this.tiempoTranscurridoNs = tiempoTranscurridoNs;
    }

    /**
     * Obtiene el estado de si la palabra fue encontrada.
     *
     * @return `true` si la palabra fue encontrada, `false` en caso contrario.
     */
    public boolean isEncontrada() {
        return encontrada;
    }

    /**
     * Obtiene el camino de vértices que formaron la palabra encontrada.
     *
     * @return La {@link ListaSimple} de {@link Vertice} que representa el camino.
     * Retorna una lista vacía si no hay un camino asociado (ej. para DFS) o si la palabra no se encontró.
     */
    public ListaSimple<Vertice> getCamino() {
        return camino;
    }

    /**
     * Obtiene el tiempo transcurrido en nanosegundos para la operación de búsqueda.
     *
     * @return El tiempo en nanosegundos.
     */
    public long getTiempoTranscurridoNs() {
        return tiempoTranscurridoNs;
    }
}