/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import org.estructuras.ListaSimple;
import org.estructuras.ListaSimple;

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class Vertice {
    private char letra;
    private int fila;
    private int col;
    private ListaSimple<Vertice> adyacentes;

    public Vertice(char letra, int fila, int col) {
        this.letra = letra;
        this.fila = fila;
        this.col = col;
        this.adyacentes = new ListaSimple<>();
    }
    
    public void agregarAdyacente(Vertice vecino) {
        this.adyacentes.agregar(vecino);
    }

    public char getLetra() {
        return letra;
    }

    public int getFila() {
        return fila;
    }

    public int getCol() {
        return col;
    }

    public ListaSimple<Vertice> getAdyacentes() {
        return adyacentes;
    }

    @Override
    public String toString() {
        return String.valueOf(letra) + " (" + fila + "," + col + ")";
    }
}
