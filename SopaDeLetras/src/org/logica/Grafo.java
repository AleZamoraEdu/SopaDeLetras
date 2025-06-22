/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import org.estructuras.Cola;
import org.estructuras.ListaSimple;
import java.util.HashMap;

/**
 * La clase `Grafo` representa el tablero de la sopa de letras como una estructura de grafo.
 * Cada celda del tablero es un {@link Vertice} en el grafo, y las celdas adyacentes
 * (horizontal, vertical y diagonalmente) están conectadas como vecinos.
 * Esta clase proporciona métodos para construir el grafo y para realizar búsquedas
 * de palabras utilizando algoritmos como DFS (Depth First Search) y BFS (Breadth First Search).
 *
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class Grafo {

    private final Vertice[][] tablero; // Matriz de vértices que representa el tablero 4x4.
    private final int FILAS = 4;       // Número de filas del tablero.
    private final int COLUMNAS = 4;    // Número de columnas del tablero.

    /**
     * Constructor de la clase `Grafo`.
     * Inicializa el tablero de vértices a partir de una matriz de caracteres
     * y luego establece las conexiones de adyacencia entre todos los vértices.
     *
     * @param letras La matriz 4x4 de caracteres que forma la sopa de letras.
     */
    public Grafo(char[][] letras) {
        this.tablero = new Vertice[FILAS][COLUMNAS];
        // 1. Inicializar cada celda del tablero como un objeto Vertice.
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = new Vertice(letras[i][j], i, j);
            }
        }
        // 2. Conectar cada vértice con sus vecinos adyacentes.
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                conectarVecinos(i, j);
            }
        }
    }

    /**
     * Conecta el vértice en la posición `(fila, col)` con todos sus vértices vecinos adyacentes
     * (incluyendo adyacencia horizontal, vertical y diagonal).
     *
     * @param fila La fila del vértice actual.
     * @param col La columna del vértice actual.
     */
    private void conectarVecinos(int fila, int col) {
        Vertice actual = tablero[fila][col];
        // Itera a través de las 8 posibles direcciones alrededor del vértice actual.
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Excluye el propio vértice (0,0)
                if (i == 0 && j == 0) {
                    continue;
                }
                int vecinoFila = fila + i;
                int vecinoCol = col + j;
                // Verifica que las coordenadas del vecino estén dentro de los límites del tablero.
                if (vecinoFila >= 0 && vecinoFila < FILAS && vecinoCol >= 0 && vecinoCol < COLUMNAS) {
                    actual.agregarAdyacente(tablero[vecinoFila][vecinoCol]);
                }
            }
        }
    }

    /**
     * Devuelve la matriz de vértices que representa el tablero completo del grafo.
     * Es útil para componentes de visualización externa que necesitan acceder a la estructura del tablero.
     *
     * @return Una matriz 4x4 de objetos {@link Vertice}.
     */
    public Vertice[][] getVerticesDelTablero() {
        return this.tablero;
    }

    /**
     * Busca una palabra específica en el tablero utilizando el algoritmo de Búsqueda en Profundidad (DFS).
     * Itera sobre cada celda del tablero para intentar iniciar la palabra si su letra coincide
     * con la primera letra de la palabra buscada.
     *
     * @param palabra La {@link String} que se desea buscar. Se convierte a mayúsculas para la búsqueda.
     * @return `true` si la palabra es encontrada en el tablero, `false` en caso contrario.
     */
    public boolean buscarPalabraDFS(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }
        palabra = palabra.toUpperCase(); // Normaliza la palabra a buscar a mayúsculas.

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                // Si la letra del vértice actual coincide con la primera letra de la palabra.
                if (tablero[i][j].getLetra() == palabra.charAt(0)) {
                    boolean[][] visitado = new boolean[FILAS][COLUMNAS]; // Matriz para registrar vértices visitados en el camino actual.
                    // Inicia la búsqueda recursiva DFS desde este vértice.
                    if (dfsRecursivo(tablero[i][j], palabra, 0, visitado)) {
                        return true; // Palabra encontrada.
                    }
                }
            }
        }
        return false; // La palabra no fue encontrada en ninguna parte del tablero.
    }

    /**
     * Método auxiliar recursivo para la Búsqueda en Profundidad (DFS).
     * Explora caminos posibles en el grafo para encontrar la palabra.
     *
     * @param actual El {@link Vertice} actual en la búsqueda.
     * @param palabra La {@link String} que se está buscando.
     * @param indice El índice actual de la letra de la palabra que se espera encontrar.
     * @param visitado Una matriz booleana que registra los vértices visitados en el camino actual
     * para evitar ciclos y reutilizar celdas en la misma palabra.
     * @return `true` si la palabra completa se encuentra a partir de este punto, `false` en caso contrario.
     */
    private boolean dfsRecursivo(Vertice actual, String palabra, int indice, boolean[][] visitado) {
        // Condiciones de base para la recursión:
        // 1. Si el vértice ya fue visitado en este camino o su letra no coincide con la esperada.
        if (visitado[actual.getFila()][actual.getCol()] || actual.getLetra() != palabra.charAt(indice)) {
            return false;
        }

        // Marcar el vértice actual como visitado para este camino.
        visitado[actual.getFila()][actual.getCol()] = true;

        // Si se encontró la última letra de la palabra, la palabra completa ha sido hallada.
        if (indice == palabra.length() - 1) {
            return true;
        }

        // Explorar los vértices adyacentes al actual.
        ListaSimple<Vertice> adyacentes = actual.getAdyacentes();
        for (int i = 0; i < adyacentes.tamano(); i++) {
            if (dfsRecursivo(adyacentes.obtener(i), palabra, indice + 1, visitado)) {
                return true; // Si se encuentra la palabra en un camino adyacente, retornar true.
            }
        }

        // Desmarcar el vértice actual como visitado (backtracking), permitiendo que otros caminos
        // lo usen si forman parte de una palabra diferente o una parte anterior de la misma palabra
        // en otra rama de búsqueda.
        visitado[actual.getFila()][actual.getCol()] = false;
        return false;
    }

    /**
     * Clase interna privada `NodoBusqueda` utilizada para encapsular un vértice
     * y el camino que llevó a ese vértice en las búsquedas BFS.
     */
    private class NodoBusqueda {
        Vertice vertice;             // El vértice actual en el nodo.
        ListaSimple<Vertice> camino; // La secuencia de vértices desde el inicio hasta este vértice.

        /**
         * Constructor para `NodoBusqueda`.
         * @param vertice El {@link Vertice} de este nodo.
         * @param camino La {@link ListaSimple} de {@link Vertice} que representa el camino.
         */
        NodoBusqueda(Vertice vertice, ListaSimple<Vertice> camino) {
            this.vertice = vertice;
            this.camino = camino;
        }
    }

    /**
     * Busca una palabra específica en el tablero utilizando el algoritmo de Búsqueda en Amplitud (BFS).
     * Devuelve el camino de vértices si la palabra es encontrada.
     *
     * @param palabra La {@link String} que se desea buscar. Se convierte a mayúsculas para la búsqueda.
     * @return Una {@link ListaSimple} de {@link Vertice} que representa el camino de la palabra encontrada.
     * Si la palabra no es encontrada, retorna una lista vacía.
     */
    public ListaSimple<Vertice> buscarPalabraBFS(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return new ListaSimple<>();
        }
        palabra = palabra.toUpperCase(); // Normaliza la palabra a buscar a mayúsculas.

        // Itera sobre cada celda del tablero para intentar iniciar la búsqueda BFS.
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                // Si la letra del vértice coincide con la primera letra de la palabra.
                if (tablero[i][j].getLetra() == palabra.charAt(0)) {
                    ListaSimple<Vertice> resultado = bfsDesde(tablero[i][j], palabra);
                    if (!resultado.estaVacia()) {
                        return resultado; // La palabra fue encontrada, retorna el camino.
                    }
                }
            }
        }
        return new ListaSimple<>(); // La palabra no fue encontrada.
    }

    /**
     * Realiza una Búsqueda en Amplitud (BFS) para encontrar una palabra a partir de un vértice de inicio.
     * Mantiene un registro del camino recorrido para reconstruir la palabra si es encontrada.
     *
     * @param inicio El {@link Vertice} desde el cual iniciar la búsqueda.
     * @param palabra La {@link String} que se está buscando.
     * @return Una {@link ListaSimple} de {@link Vertice} que representa el camino de la palabra encontrada.
     * Si la palabra no se puede formar desde este inicio, retorna una lista vacía.
     */
    private ListaSimple<Vertice> bfsDesde(Vertice inicio, String palabra) {
        Cola<NodoBusqueda> cola = new Cola<>();
        ListaSimple<Vertice> caminoInicial = new ListaSimple<>();
        caminoInicial.agregar(inicio);
        cola.encolar(new NodoBusqueda(inicio, caminoInicial));

        while (!cola.estaVacia()) {
            NodoBusqueda actual = cola.desencolar();
            int indiceActual = actual.camino.tamano() - 1; // Índice de la última letra del camino actual.

            // Si el camino actual ya forma la palabra completa, se ha encontrado.
            if (indiceActual == palabra.length() - 1) {
                return actual.camino;
            }

            // Explora los vértices adyacentes al actual.
            ListaSimple<Vertice> adyacentes = actual.vertice.getAdyacentes();
            for (int i = 0; i < adyacentes.tamano(); i++) {
                Vertice vecino = adyacentes.obtener(i);
                boolean visitadoEnEsteCamino = false;
                // Verifica si el vecino ya está en el camino actual para evitar ciclos en la misma palabra.
                for (int k = 0; k < actual.camino.tamano(); k++) {
                    if (actual.camino.obtener(k) == vecino) {
                        visitadoEnEsteCamino = true;
                        break;
                    }
                }

                // Si el vecino no ha sido visitado en este camino y su letra coincide con la siguiente
                // letra esperada de la palabra.
                if (!visitadoEnEsteCamino && vecino.getLetra() == palabra.charAt(indiceActual + 1)) {
                    // Crea un nuevo camino extendiendo el actual.
                    ListaSimple<Vertice> nuevoCamino = new ListaSimple<>();
                    for (int k = 0; k < actual.camino.tamano(); k++) {
                        nuevoCamino.agregar(actual.camino.obtener(k));
                    }
                    nuevoCamino.agregar(vecino); // Agrega el vecino al nuevo camino.
                    cola.encolar(new NodoBusqueda(vecino, nuevoCamino)); // Encola el nuevo nodo de búsqueda.
                }
            }
        }
        return new ListaSimple<>(); // No se encontró la palabra a partir de este inicio.
    }

    /**
     * Obtiene el árbol de Búsqueda en Amplitud (BFS) a partir de un vértice de inicio.
     * Este método construye un mapa donde cada vértice (excepto la raíz) se mapea a su vértice padre
     * en el árbol BFS, lo que permite reconstruir los caminos más cortos desde la raíz.
     *
     * @param inicio El {@link Vertice} desde el cual se construye el árbol BFS.
     * @return Un {@link HashMap} donde la clave es un {@link Vertice} (hijo) y el valor es su {@link Vertice} padre.
     * La raíz del árbol tiene un padre `null`.
     */
    public HashMap<Vertice, Vertice> obtenerArbolBFS(Vertice inicio) {
        Cola<Vertice> cola = new Cola<>();
        HashMap<Vertice, Vertice> padres = new HashMap<>(); // Almacenará hijo -> padre.
        boolean[][] visitado = new boolean[FILAS][COLUMNAS]; // Matriz para controlar los vértices visitados.

        cola.encolar(inicio);
        visitado[inicio.getFila()][inicio.getCol()] = true;
        padres.put(inicio, null); // La raíz del árbol no tiene padre.

        while (!cola.estaVacia()) {
            Vertice actual = cola.desencolar();

            ListaSimple<Vertice> adyacentes = actual.getAdyacentes();
            for (int i = 0; i < adyacentes.tamano(); i++) {
                Vertice vecino = adyacentes.obtener(i);

                if (!visitado[vecino.getFila()][vecino.getCol()]) {
                    visitado[vecino.getFila()][vecino.getCol()] = true;
                    cola.encolar(vecino);
                    padres.put(vecino, actual); // El vecino es hijo de 'actual'.
                }
            }
        }
        return padres;
    }
}