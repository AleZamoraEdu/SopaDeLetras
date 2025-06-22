/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import org.estructuras.ListaSimple;

/**
 * La clase `Vertice` representa un nodo en el grafo del tablero de la sopa de letras.
 * Cada vértice contiene una letra, sus coordenadas (fila y columna) en el tablero,
 * y una lista de vértices adyacentes a él.
 *
 * Los vértices son los componentes básicos que forman el grafo no dirigido
 * utilizado para buscar palabras en el tablero, donde la adyacencia se define
 * por la proximidad horizontal, vertical o diagonal.
 *
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class Vertice {
    /**
     * La letra que este vértice representa en el tablero de la sopa de letras.
     */
    private char letra;
    /**
     * La fila en la que se encuentra este vértice en el tablero (índice base 0).
     */
    private int fila;
    /**
     * La columna en la que se encuentra este vértice en el tablero (índice base 0).
     */
    private int col;
    /**
     * Una lista simple de objetos `Vertice` que son adyacentes a este vértice
     * en el tablero (vecinos horizontales, verticales o diagonales).
     */
    private ListaSimple<Vertice> adyacentes;

    /**
     * Constructor para la clase `Vertice`.
     * Inicializa un nuevo vértice con una letra específica y sus coordenadas en el tablero.
     * La lista de adyacentes se inicializa vacía y se llena posteriormente.
     *
     * @param letra La letra que representa este vértice.
     * @param fila La fila del vértice en el tablero.
     * @param col La columna del vértice en el tablero.
     */
    public Vertice(char letra, int fila, int col) {
        this.letra = letra;
        this.fila = fila;
        this.col = col;
        this.adyacentes = new ListaSimple<>();
    }
    
    /**
     * Agrega un vértice a la lista de adyacentes de este vértice.
     *
     * @param vecino El objeto `Vertice` que es adyacente a este vértice.
     */
    public void agregarAdyacente(Vertice vecino) {
        this.adyacentes.agregar(vecino);
    }

    /**
     * Obtiene la letra que representa este vértice.
     *
     * @return El carácter que es la letra de este vértice.
     */
    public char getLetra() {
        return letra;
    }

    /**
     * Obtiene la fila en la que se encuentra este vértice.
     *
     * @return Un entero que representa la fila del vértice.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtiene la columna en la que se encuentra este vértice.
     *
     * @return Un entero que representa la columna del vértice.
     */
    public int getCol() {
        return col;
    }

    /**
     * Obtiene la lista de vértices adyacentes a este vértice.
     *
     * @return Un objeto `ListaSimple` que contiene los vértices adyacentes.
     */
    public ListaSimple<Vertice> getAdyacentes() {
        return adyacentes;
    }

    /**
     * Sobrescribe el método `toString()` para proporcionar una representación
     * de cadena legible del vértice, incluyendo su letra y sus coordenadas.
     *
     * @return Una cadena en el formato "Letra (fila,columna)".
     */
    @Override
    public String toString() {
        return String.valueOf(letra) + " (" + fila + "," + col + ")";
    }
}