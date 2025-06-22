/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.visualizacion;

import org.estructuras.ListaSimple;
import org.logica.Vertice;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.Viewer.CloseFramePolicy;

import java.util.HashMap;

/**
 * La clase VisualizadorGrafo se encarga de la representación gráfica del tablero de letras
 * y de los caminos de las palabras encontradas, utilizando la biblioteca GraphStream.
 * Permite visualizar el grafo completo, el árbol de recorrido BFS y el camino de una palabra específica.
 */
public class VisualizadorGrafo {

    /**
     * Hoja de estilos CSS para GraphStream que define la apariencia de los nodos y aristas.
     * Incluye estilos para nodos normales, nodos de camino (verde amarillento) y el nodo raíz (azul claro).
     */
    private final String stylesheet
            = "graph{padding:40px;}"
            + "node{fill-color:white;stroke-mode:plain;stroke-color:black;size:40px,40px;text-size:20;text-alignment:center;}"
            + "node.camino{fill-color:yellowgreen;text-color:white;}"
            + "node.raiz{fill-color:#87CEEB;}" // Azul claro (SkyBlue)
            + "edge{fill-color:lightgray;size:2px;}"
            + "edge.camino{fill-color:yellowgreen;size:3px;}";

    /**
     * Representación bidimensional del tablero de letras como un arreglo de objetos Vertice.
     * Utilizado para construir el grafo inicial.
     */
    private Vertice[][] tableroCompleto;

    /**
     * Constructor de VisualizadorGrafo.
     * Inicializa el visualizador con la estructura completa del tablero de letras.
     *
     * @param tableroCompleto El arreglo bidimensional de Vertice que representa el tablero del juego.
     */
    public VisualizadorGrafo(Vertice[][] tableroCompleto) {
        this.tableroCompleto = tableroCompleto;
    }

    /**
     * Constructor por defecto de VisualizadorGrafo.
     * Permite crear una instancia sin inicializar el tablero,
     * pero se requerirá que 'tableroCompleto' se establezca antes de usar
     * métodos que dependan de él, como {@code mostrarArbolBFS}.
     */
    public VisualizadorGrafo() {
    }

    /**
     * Muestra una representación completa del grafo del tablero, resaltando
     * el camino de una palabra encontrada si se proporciona.
     *
     * @param palabraBuscada La palabra que se intentó buscar.
     * @param tableroCompleto El arreglo bidimensional de Vertice que representa el tablero del juego.
     * @param caminoSolucion Una ListaSimple de Vertice que representa el camino de la palabra encontrada.
     * Puede estar vacía si la palabra no fue encontrada.
     * @param tiempoNs El tiempo en nanosegundos que tomó la búsqueda de la palabra.
     */
    public void mostrarGrafoCompleto(String palabraBuscada,
            Vertice[][] tableroCompleto,
            ListaSimple<Vertice> caminoSolucion,
            long tiempoNs) {

        System.setProperty("org.graphstream.ui", "swing");

        String titulo = String.format("Resultado para '%s' (Tiempo: %,d ns)", palabraBuscada, tiempoNs);
        Graph graph = new SingleGraph(titulo);

        // Añadir nodos al grafo
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vertice v = tableroCompleto[i][j];
                String idNodo = v.getFila() + "," + v.getCol();
                Node node = graph.addNode(idNodo);
                node.setAttribute("ui.label", String.valueOf(v.getLetra()));
                node.setAttribute("x", j); // Posición X para visualización
                node.setAttribute("y", -i); // Posición Y para visualización (negativo para invertir el eje Y)
            }
        }

        // Añadir aristas entre nodos adyacentes
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vertice origen = tableroCompleto[i][j];
                ListaSimple<Vertice> adyacentes = origen.getAdyacentes();
                for (int k = 0; k < adyacentes.tamano(); k++) {
                    Vertice destino = adyacentes.obtener(k);
                    String idOrigen = origen.getFila() + "," + origen.getCol();
                    String idDestino = destino.getFila() + "," + destino.getCol();
                    String idArista = idOrigen + "-" + idDestino;
                    // Evitar añadir aristas duplicadas (bidireccionalidad)
                    if (graph.getEdge(idArista) == null && graph.getEdge(idDestino + "-" + idOrigen) == null) {
                        graph.addEdge(idArista, idOrigen, idDestino);
                    }
                }
            }
        }

        // Resaltar el camino de la palabra encontrada
        if (!caminoSolucion.estaVacia()) {
            for (int i = 0; i < caminoSolucion.tamano(); i++) {
                Vertice v = caminoSolucion.obtener(i);
                String idNodo = v.getFila() + "," + v.getCol();
                Node node = graph.getNode(idNodo);
                if (node != null) {
                    if (i == 0) { // La primera letra del camino es la raíz
                        node.setAttribute("ui.class", "raiz");
                    } else { // El resto de letras del camino
                        node.setAttribute("ui.class", "camino");
                    }
                }
            }

            // Resaltar las aristas del camino
            for (int i = 0; i < caminoSolucion.tamano() - 1; i++) {
                Vertice v1 = caminoSolucion.obtener(i);
                Vertice v2 = caminoSolucion.obtener(i + 1);
                String id1 = v1.getFila() + "," + v1.getCol();
                String id2 = v2.getFila() + "," + v2.getCol();
                Edge edge = graph.getEdge(id1 + "-" + id2);
                if (edge == null) {
                    edge = graph.getEdge(id2 + "-" + id1);
                }
                if (edge != null) {
                    edge.setAttribute("ui.class", "camino");
                }
            }
        }

        graph.setAttribute("ui.stylesheet", stylesheet);
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(CloseFramePolicy.CLOSE_VIEWER);
    }

    /**
     * Muestra una representación del árbol de recorrido BFS para la palabra buscada,
     * resaltando la raíz del árbol y el camino de la palabra si fue encontrada.
     *
     * @param palabraBuscada La palabra para la cual se generó el árbol BFS.
     * @param raiz El vértice que actúa como raíz del árbol BFS (la primera letra de la palabra encontrada).
     * @param arbolBFS Un HashMap que representa el árbol BFS, mapeando cada vértice a su predecesor en el recorrido.
     * (Aunque este parámetro se pasa, la visualización actual se centra más en el camino directo).
     * @param caminoPalabra Una ListaSimple de Vertice que representa el camino de la palabra encontrada en el tablero.
     */
    public void mostrarArbolBFS(String palabraBuscada, Vertice raiz,
                                HashMap<Vertice, Vertice> arbolBFS,
                                ListaSimple<Vertice> caminoPalabra) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Árbol de Recorrido BFS para '" + palabraBuscada + "'");
        graph.setAttribute("ui.stylesheet", stylesheet);

        // Añadir todos los nodos del tablero completo al grafo para tener el contexto visual
        if (this.tableroCompleto != null) {
            for (int i = 0; i < tableroCompleto.length; i++) {
                for (int j = 0; j < tableroCompleto[0].length; j++) {
                    Vertice v = tableroCompleto[i][j];
                    String idNodo = v.getFila() + "," + v.getCol();
                    if (graph.getNode(idNodo) == null) { // Asegurarse de no añadir duplicados si ya existen
                        Node node = graph.addNode(idNodo);
                        node.setAttribute("ui.label", String.valueOf(v.getLetra()));
                        node.setAttribute("x", j);
                        node.setAttribute("y", -i);
                    }
                }
            }
        } else {
            System.err.println("Advertencia: tableroCompleto no está inicializado en VisualizadorGrafo. Algunas conexiones o nodos podrían no mostrarse.");
        }

        // Añadir todas las aristas entre los nodos adyacentes del tablero
        if (this.tableroCompleto != null) {
            for (int i = 0; i < tableroCompleto.length; i++) {
                for (int j = 0; j < tableroCompleto[0].length; j++) {
                    Vertice origen = tableroCompleto[i][j];
                    String idOrigen = origen.getFila() + "," + origen.getCol();
                    ListaSimple<Vertice> adyacentes = origen.getAdyacentes();
                    for (int k = 0; k < adyacentes.tamano(); k++) {
                        Vertice destino = adyacentes.obtener(k);
                        String idDestino = destino.getFila() + "," + destino.getCol();
                        String idArista = idOrigen + "-" + idDestino;

                        // Añadir arista si no existe ya para evitar duplicados en grafos no dirigidos
                        if (graph.getEdge(idArista) == null && graph.getEdge(idDestino + "-" + idOrigen) == null) {
                             graph.addEdge(idArista, idOrigen, idDestino);
                        }
                    }
                }
            }
        }

        // 1. Primero, aplica los estilos del camino (amarillo/verde) a todos los nodos del camino.
        // Esto incluye la raíz, que será sobrescrita por el estilo "raiz" posteriormente.
        if (!caminoPalabra.estaVacia()) {
            for (int i = 0; i < caminoPalabra.tamano(); i++) {
                Vertice v = caminoPalabra.obtener(i);
                String idNodo = v.getFila() + "," + v.getCol();
                Node node = graph.getNode(idNodo);
                if (node != null) {
                    node.setAttribute("ui.class", "camino"); // Marcar todos los nodos del camino en verde
                }
            }

            // Marcar las aristas del camino
            for (int i = 0; i < caminoPalabra.tamano() - 1; i++) {
                Vertice v1 = caminoPalabra.obtener(i);
                Vertice v2 = caminoPalabra.obtener(i + 1);
                String id1 = v1.getFila() + "," + v1.getCol();
                String id2 = v2.getFila() + "," + v2.getCol();

                Edge edgeToHighlight = graph.getEdge(id1 + "-" + id2);
                if (edgeToHighlight == null) {
                    edgeToHighlight = graph.getEdge(id2 + "-" + id1);
                }

                if (edgeToHighlight != null) {
                    edgeToHighlight.setAttribute("ui.class", "camino");
                } else {
                    System.err.println("NO SE ENCONTRÓ LA ARISTA PARA RESALTAR entre " + id1 + " y " + id2);
                }
            }
        }
        
        // 2. Luego, y MUY IMPORTANTE, aplica la clase "raiz" al nodo raíz AL FINAL.
        // Esto asegura que tenga la mayor prioridad de estilo y se muestre en azul claro.
        if (raiz != null) {
            String idRaiz = raiz.getFila() + "," + raiz.getCol();
            Node rootNode = graph.getNode(idRaiz);
            if (rootNode != null) {
                rootNode.setAttribute("ui.class", "raiz"); // Aplica la clase "raiz" (azul claro)
            }
        }

        graph.setAttribute("ui.stylesheet", stylesheet);
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(CloseFramePolicy.CLOSE_VIEWER);
    }

    /**
     * Muestra el camino de una palabra encontrada utilizando el recorrido BFS en un grafo lineal simplificado.
     * Cada letra de la palabra se representa como un nodo, y las conexiones directas entre ellas.
     * (Este método puede ser útil para una visualización más simple del camino, diferente a {@code mostrarArbolBFS}).
     *
     * @param palabraBuscada La palabra cuyo camino se va a visualizar.
     * @param caminoPalabra Una ListaSimple de Vertice que representa el camino de la palabra encontrada.
     */
    public void mostrarCaminoPalabraBFS(String palabraBuscada, ListaSimple<Vertice> caminoPalabra) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Camino de Palabra BFS: '" + palabraBuscada + "'");
        graph.setAttribute("ui.stylesheet", stylesheet);

        if (caminoPalabra.estaVacia()) {
            System.out.println("No hay camino para mostrar.");
            return;
        }

        // Añadir nodos para cada letra del camino
        for (int i = 0; i < caminoPalabra.tamano(); i++) {
            Vertice v = caminoPalabra.obtener(i);
            String idNodo = v.getFila() + "," + v.getCol(); // Usa las coordenadas para el ID del nodo
            Node node = graph.addNode(idNodo);
            node.setAttribute("ui.label", String.valueOf(v.getLetra()));
            node.setAttribute("x", i * 2); // Espaciar nodos horizontalmente
            node.setAttribute("y", 0);

            if (i == 0) { // La primera letra es la raíz
                node.setAttribute("ui.class", "raiz");
            } else { // El resto del camino
                node.setAttribute("ui.class", "camino");
            }
        }

        // Añadir aristas entre nodos consecutivos en el camino
        for (int i = 0; i < caminoPalabra.tamano() - 1; i++) {
            Vertice v1 = caminoPalabra.obtener(i);
            Vertice v2 = caminoPalabra.obtener(i + 1);
            String id1 = v1.getFila() + "," + v1.getCol();
            String id2 = v2.getFila() + "," + v2.getCol();
            String edgeId = id1 + "->" + id2;
            Edge edge = graph.addEdge(edgeId, id1, id2, true); // Añadir arista dirigida
            if (edge != null) {
                edge.setAttribute("ui.class", "camino");
            }
        }

        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(CloseFramePolicy.CLOSE_VIEWER);
    }

    /**
     * Este método auxiliar intenta obtener un objeto Vertice a partir de su ID de nodo (formato "fila,columna").
     * Actualmente no se utiliza en el código proporcionado.
     *
     * @param idNodo El ID del nodo en formato "fila,columna".
     * @return El objeto Vertice correspondiente, o null si no se encuentra o hay un error.
     */
    private Vertice obtenerVerticeDesdeIdNodo(String idNodo) {
        if (this.tableroCompleto == null) {
            System.err.println("Error: tableroCompleto no está inicializado en VisualizadorGrafo. No se puede obtener Vertice.");
            return null;
        }
        String[] coordenadas = idNodo.split(",");
        try {
            int fila = Integer.parseInt(coordenadas[0]);
            int col = Integer.parseInt(coordenadas[1]);
            if (fila >= 0 && fila < tableroCompleto.length &&
                col >= 0 && col < tableroCompleto[0].length) {
                return tableroCompleto[fila][col];
            } else {
                System.err.println("Error: Coordenadas de nodo fuera de los límites del tablero: " + idNodo);
                return null;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear ID de nodo: " + idNodo + " - " + e.getMessage());
            return null;
        }
    }
}