/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.persistencia;

import org.estructuras.ListaSimple;

/**
 * La clase `DatosCargados` es un objeto de valor (Value Object)
 * que se utiliza para encapsular y transportar los datos leídos de un archivo
 * de entrada. Contiene el diccionario de palabras y el tablero de letras
 * de la sopa de letras.
 *
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class DatosCargados {

    /**
     * Una {@link ListaSimple} de {@link String} que representa el diccionario
     * de palabras cargado desde el archivo.
     */
    private final ListaSimple<String> diccionario;
    /**
     * Una matriz de caracteres 4x4 que representa el tablero de la sopa de letras
     * cargado desde el archivo.
     */
    private final char[][] tablero;

    /**
     * Constructor de la clase `DatosCargados`.
     * Inicializa un nuevo objeto `DatosCargados` con el diccionario y el tablero proporcionados.
     *
     * @param diccionario La {@link ListaSimple} de {@link String} que contiene las palabras del diccionario.
     * @param tablero La matriz {@code char[][]} que contiene las letras del tablero.
     */
    public DatosCargados(ListaSimple<String> diccionario, char[][] tablero) {
        this.diccionario = diccionario;
        this.tablero = tablero;
    }

    /**
     * Obtiene la {@link ListaSimple} de {@link String} que representa el diccionario cargado.
     *
     * @return El diccionario de palabras.
     */
    public ListaSimple<String> getDiccionario() {
        return diccionario;
    }

    /**
     * Obtiene la matriz de caracteres que representa el tablero de la sopa de letras cargado.
     *
     * @return La matriz {@code char[][]} del tablero.
     */
    public char[][] getTablero() {
        return tablero;
    }
}