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
public class ResultadoBusqueda {

    private ListaSimple<String> palabrasEncontradas;
    private long tiempoTranscurrido;

    public ResultadoBusqueda(ListaSimple<String> palabrasEncontradas, long tiempoTranscurrido) {
        this.palabrasEncontradas = palabrasEncontradas;
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public ListaSimple<String> getPalabrasEncontradas() {
        return palabrasEncontradas;
    }

    public long getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }
}
