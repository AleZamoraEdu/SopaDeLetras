/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import org.estructuras.ListaSimple;

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class ResultadoBusquedaIndividual {

    private final boolean encontrada;
    private final ListaSimple<Vertice> camino; // Vacío si se usa DFS
    private final long tiempoTranscurridoNs;

    public ResultadoBusquedaIndividual(boolean encontrada, ListaSimple<Vertice> camino, long tiempoTranscurridoNs) {
        this.encontrada = encontrada;
        this.camino = camino;
        this.tiempoTranscurridoNs = tiempoTranscurridoNs;
    }

    public boolean isEncontrada() {
        return encontrada;
    }

    public ListaSimple<Vertice> getCamino() {
        return camino;
    }

    public long getTiempoTranscurridoNs() {
        return tiempoTranscurridoNs;
    }
}
