/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import org.estructuras.Cola;
import org.estructuras.ListaSimple;

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class Grafo {

    private final Vertice[][] tablero;
    private final int FILAS = 4;
    private final int COLUMNAS = 4;

    public Grafo(char[][] letras) {
        this.tablero = new Vertice[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = new Vertice(letras[i][j], i, j);
            }
        }
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                conectarVecinos(i, j);
            }
        }
    }

    private void conectarVecinos(int fila, int col) {
        Vertice actual = tablero[fila][col];
        for (int i = -1; i <= 1; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int vecinoFila = fila + i;
                int vecinoCol = col + j;
                if (vecinoFila >= 0 && vecinoFila < FILAS && vecinoCol >= 0 && vecinoCol < COLUMNAS) {
                    actual.agregarAdyacente(tablero[vecinoFila][vecinoCol]);
                }
            }
        }
    }

    /**
     * Devuelve la matriz de vértices que representa el tablero completo. Es
     * necesario para el visualizador del grafo.
     *
     * @return una matriz 4x4 de objetos Vertice.
     */
    public Vertice[][] getVerticesDelTablero() { // <-- NUEVO MÉTODO AÑADIDO
        return this.tablero;
    }

    public boolean buscarPalabraDFS(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }
        palabra = palabra.toUpperCase();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j].getLetra() == palabra.charAt(0)) {
                    boolean[][] visitado = new boolean[FILAS][COLUMNAS];
                    if (dfsRecursivo(tablero[i][j], palabra, 0, visitado)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfsRecursivo(Vertice actual, String palabra, int indice, boolean[][] visitado) {
        if (visitado[actual.getFila()][actual.getCol()] || actual.getLetra() != palabra.charAt(indice)) {
            return false;
        }
        visitado[actual.getFila()][actual.getCol()] = true;
        if (indice == palabra.length() - 1) {
            return true;
        }
        for (int i = 0; i < actual.getAdyacentes().tamano(); i++) {
            if (dfsRecursivo(actual.getAdyacentes().obtener(i), palabra, indice + 1, visitado)) {
                return true;
            }
        }
        visitado[actual.getFila()][actual.getCol()] = false;
        return false;
    }

    private class NodoBusqueda {

        Vertice vertice;
        ListaSimple<Vertice> camino;

        NodoBusqueda(Vertice vertice, ListaSimple<Vertice> camino) {
            this.vertice = vertice;
            this.camino = camino;
        }
    }

    public ListaSimple<Vertice> buscarPalabraBFS(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return new ListaSimple<>();
        }
        palabra = palabra.toUpperCase();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j].getLetra() == palabra.charAt(0)) {
                    ListaSimple<Vertice> resultado = bfsDesde(tablero[i][j], palabra);
                    if (!resultado.estaVacia()) {
                        return resultado;
                    }
                }
            }
        }
        return new ListaSimple<>();
    }

    private ListaSimple<Vertice> bfsDesde(Vertice inicio, String palabra) {
        Cola<NodoBusqueda> cola = new Cola<>();
        ListaSimple<Vertice> caminoInicial = new ListaSimple<>();
        caminoInicial.agregar(inicio);
        cola.encolar(new NodoBusqueda(inicio, caminoInicial));

        while (!cola.estaVacia()) {
            NodoBusqueda actual = cola.desencolar();
            int indiceActual = actual.camino.tamano() - 1;

            if (indiceActual == palabra.length() - 1) {
                return actual.camino;
            }

            ListaSimple<Vertice> adyacentes = actual.vertice.getAdyacentes();
            for (int i = 0; i < adyacentes.tamano(); i++) {
                Vertice vecino = adyacentes.obtener(i);
                boolean visitadoEnEsteCamino = false;
                for (int k = 0; k < actual.camino.tamano(); k++) {
                    if (actual.camino.obtener(k) == vecino) {
                        visitadoEnEsteCamino = true;
                        break;
                    }
                }

                if (!visitadoEnEsteCamino && vecino.getLetra() == palabra.charAt(indiceActual + 1)) {
                    ListaSimple<Vertice> nuevoCamino = new ListaSimple<>();
                    for (int k = 0; k < actual.camino.tamano(); k++) {
                        nuevoCamino.agregar(actual.camino.obtener(k));
                    }
                    nuevoCamino.agregar(vecino);
                    cola.encolar(new NodoBusqueda(vecino, nuevoCamino));
                }
            }
        }
        return new ListaSimple<>();
    }
}
