/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.estructuras.ListaSimple;
import org.persistencia.ControladoraPersistencia;
import org.persistencia.DatosCargados;
import org.visualizacion.VisualizadorGrafo;

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class Controladora {
    private final ControladoraPersistencia controlPersis;
    private final VisualizadorGrafo visualizador;
    private Grafo grafoTablero;
    private ListaSimple<String> diccionario;
    private char[][] letrasTablero;

    public Controladora() {
        this.controlPersis = new ControladoraPersistencia();
        this.visualizador = new VisualizadorGrafo();
    }

    public void cargarArchivo(File archivo) throws IOException {
        DatosCargados datos = controlPersis.leerArchivo(archivo);
        this.diccionario = datos.getDiccionario();
        this.letrasTablero = datos.getTablero();
        this.grafoTablero = new Grafo(this.letrasTablero);
    }

    public ResultadoBusqueda buscarTodasLasPalabras() {
        if (grafoTablero == null || diccionario == null) return new ResultadoBusqueda(new ListaSimple<>(), 0);
        
        ListaSimple<String> encontradas = new ListaSimple<>();
        long tiempoInicio = System.nanoTime();

        for (int i = 0; i < diccionario.tamano(); i++) {
            String palabra = diccionario.obtener(i);
            if (palabra.length() >= 3 && grafoTablero.buscarPalabraDFS(palabra)) {
                encontradas.agregar(palabra);
            }
        }
        long tiempoFin = System.nanoTime();
        long tiempoTotalNs = tiempoFin - tiempoInicio;
        
        return new ResultadoBusqueda(encontradas, tiempoTotalNs);
    }

    /**
     * Busca una palabra con BFS, mide el tiempo y la visualiza si la encuentra.
     * @param palabra La palabra a buscar.
     */
    public void buscarYVisualizarPalabra(String palabra) {
        if (grafoTablero == null) {
            JOptionPane.showMessageDialog(null, "El tablero no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        long tiempoInicio = System.nanoTime();
        ListaSimple<Vertice> camino = grafoTablero.buscarPalabraBFS(palabra);
        long tiempoFin = System.nanoTime();
        
        long tiempoTotalNs = tiempoFin - tiempoInicio;
        
        Vertice[][] tableroCompleto = grafoTablero.getVerticesDelTablero();
        visualizador.mostrarGrafoCompleto(palabra, tableroCompleto, camino, tiempoTotalNs); 
        
        if (camino.estaVacia()) {
            JOptionPane.showMessageDialog(null, "La palabra '" + palabra.toUpperCase() + "' NO fue encontrada.", "No Encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Busca una palabra con DFS y mide el tiempo.
     * @param palabra La palabra a buscar.
     * @return Un objeto ResultadoBusquedaIndividual con el resultado.
     */
    public ResultadoBusquedaIndividual buscarPalabraDFS(String palabra) {
        if (grafoTablero == null || palabra == null || palabra.length() < 3) {
            return new ResultadoBusquedaIndividual(false, new ListaSimple<>(), 0);
        }
        
        long tiempoInicio = System.nanoTime();
        boolean encontrada = grafoTablero.buscarPalabraDFS(palabra);
        long tiempoFin = System.nanoTime();
        
        long tiempoTotalNs = tiempoFin - tiempoInicio;
        
        return new ResultadoBusquedaIndividual(encontrada, new ListaSimple<>(), tiempoTotalNs);
    }

    public void guardarDiccionario(File archivoDestino) throws IOException {
        if (this.diccionario == null) throw new IOException("No hay un diccionario cargado para guardar.");
        controlPersis.guardarDiccionario(this.diccionario, archivoDestino);
    }
    
    public char[][] getLetrasTablero() { return letrasTablero; }
    public ListaSimple<String> getDiccionario() { return diccionario; }
}