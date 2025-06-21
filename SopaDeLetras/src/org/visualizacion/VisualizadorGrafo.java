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

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class VisualizadorGrafo {

    private final String stylesheet
            = "graph{padding:40px;}"
            + "node{fill-color:white;stroke-mode:plain;stroke-color:black;size:40px,40px;text-size:20;text-alignment:center;}"
            + "node.camino{fill-color:yellowgreen;text-color:white;}"
            + "node.raiz{fill-color:orange;}"
            + "edge{fill-color:lightgray;size:2px;}"
            + "edge.camino{fill-color:yellowgreen;size:3px;}";

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
                if (i == 0) {
                    node.setAttribute("ui.class", "raiz");
                } else {
                    node.setAttribute("ui.class", "camino");
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
}
