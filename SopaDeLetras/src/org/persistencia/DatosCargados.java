/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.persistencia;

import org.estructuras.ListaSimple;

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class DatosCargados {

    private final ListaSimple<String> diccionario;
    private final char[][] tablero;

    public DatosCargados(ListaSimple<String> diccionario, char[][] tablero) {
        this.diccionario = diccionario;
        this.tablero = tablero;
    }

    public ListaSimple<String> getDiccionario() {
        return diccionario;
    }

    public char[][] getTablero() {
        return tablero;
    }
}
