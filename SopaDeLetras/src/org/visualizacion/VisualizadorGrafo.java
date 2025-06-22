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

public class VisualizadorGrafo {

    private final String stylesheet
            = "graph{padding:40px;}"
            + "node{fill-color:white;stroke-mode:plain;stroke-color:black;size:40px,40px;text-size:20;text-alignment:center;}"
            + "node.camino{fill-color:yellowgreen;text-color:white;}"
            + "node.raiz{fill-color:#87CEEB;}" // Aquí se cambió el color a un azul más claro (SkyBlue)
            + "edge{fill-color:lightgray;size:2px;}"
            + "edge.camino{fill-color:yellowgreen;size:3px;}";

    private Vertice[][] tableroCompleto;

    public VisualizadorGrafo(Vertice[][] tableroCompleto) {
        this.tableroCompleto = tableroCompleto;
    }

    public VisualizadorGrafo() {
    }

    public void mostrarGrafoCompleto(String palabraBuscada,
            Vertice[][] tableroCompleto,
            ListaSimple<Vertice> caminoSolucion,
            long tiempoNs) {

        System.setProperty("org.graphstream.ui", "swing");

        String titulo = String.format("Resultado para '%s' (Tiempo: %,d ns)", palabraBuscada, tiempoNs);
        Graph graph = new SingleGraph(titulo);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vertice v = tableroCompleto[i][j];
                String idNodo = v.getFila() + "," + v.getCol();
                Node node = graph.addNode(idNodo);
                node.setAttribute("ui.label", String.valueOf(v.getLetra()));
                node.setAttribute("x", j);
                node.setAttribute("y", -i);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vertice origen = tableroCompleto[i][j];
                ListaSimple<Vertice> adyacentes = origen.getAdyacentes();
                for (int k = 0; k < adyacentes.tamano(); k++) {
                    Vertice destino = adyacentes.obtener(k);
                    String idOrigen = origen.getFila() + "," + origen.getCol();
                    String idDestino = destino.getFila() + "," + destino.getCol();
                    String idArista = idOrigen + "-" + idDestino;
                    if (graph.getEdge(idArista) == null && graph.getEdge(idDestino + "-" + idOrigen) == null) {
                        graph.addEdge(idArista, idOrigen, idDestino);
                    }
                }
            }
        }

        if (!caminoSolucion.estaVacia()) {
            for (int i = 0; i < caminoSolucion.tamano(); i++) {
                Vertice v = caminoSolucion.obtener(i);
                String idNodo = v.getFila() + "," + v.getCol();
                Node node = graph.getNode(idNodo);
                if (node != null) {
                    if (i == 0) {
                        node.setAttribute("ui.class", "raiz");
                    } else {
                        node.setAttribute("ui.class", "camino");
                    }
                }
            }

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

    public void mostrarArbolBFS(String palabraBuscada, Vertice raiz,
                                HashMap<Vertice, Vertice> arbolBFS,
                                ListaSimple<Vertice> caminoPalabra) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Árbol de Recorrido BFS para '" + palabraBuscada + "'");
        graph.setAttribute("ui.stylesheet", stylesheet);

        if (this.tableroCompleto != null) {
            for (int i = 0; i < tableroCompleto.length; i++) {
                for (int j = 0; j < tableroCompleto[0].length; j++) {
                    Vertice v = tableroCompleto[i][j];
                    String idNodo = v.getFila() + "," + v.getCol();
                    if (graph.getNode(idNodo) == null) {
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

                        if (graph.getEdge(idArista) == null && graph.getEdge(idDestino + "-" + idOrigen) == null) {
                             graph.addEdge(idArista, idOrigen, idDestino);
                        }
                    }
                }
            }
        }

        if (!caminoPalabra.estaVacia()) {
            for (int i = 0; i < caminoPalabra.tamano(); i++) {
                Vertice v = caminoPalabra.obtener(i);
                String idNodo = v.getFila() + "," + v.getCol();
                Node node = graph.getNode(idNodo);
                if (node != null) {
                    node.setAttribute("ui.class", "camino");
                }
            }

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
        
        if (raiz != null) {
            String idRaiz = raiz.getFila() + "," + raiz.getCol();
            Node rootNode = graph.getNode(idRaiz);
            if (rootNode != null) {
                rootNode.setAttribute("ui.class", "raiz");
            }
        }


        graph.setAttribute("ui.stylesheet", stylesheet);
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(CloseFramePolicy.CLOSE_VIEWER);
    }

    public void mostrarCaminoPalabraBFS(String palabraBuscada, ListaSimple<Vertice> caminoPalabra) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Camino de Palabra BFS: '" + palabraBuscada + "'");
        graph.setAttribute("ui.stylesheet", stylesheet);

        if (caminoPalabra.estaVacia()) {
            System.out.println("No hay camino para mostrar.");
            return;
        }

        for (int i = 0; i < caminoPalabra.tamano(); i++) {
            Vertice v = caminoPalabra.obtener(i);
            String idNodo = v.getFila() + "," + v.getCol();
            Node node = graph.addNode(idNodo);
            node.setAttribute("ui.label", String.valueOf(v.getLetra()));
            node.setAttribute("x", i * 2);
            node.setAttribute("y", 0);

            if (i == 0) {
                node.setAttribute("ui.class", "raiz");
            } else {
                node.setAttribute("ui.class", "camino");
            }
        }

        for (int i = 0; i < caminoPalabra.tamano() - 1; i++) {
            Vertice v1 = caminoPalabra.obtener(i);
            Vertice v2 = caminoPalabra.obtener(i + 1);
            String id1 = v1.getFila() + "," + v1.getCol();
            String id2 = v2.getFila() + "," + v2.getCol();
            String edgeId = id1 + "->" + id2;
            Edge edge = graph.addEdge(edgeId, id1, id2, true);
            if (edge != null) {
                edge.setAttribute("ui.class", "camino");
            }
        }

        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(CloseFramePolicy.CLOSE_VIEWER);
    }

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