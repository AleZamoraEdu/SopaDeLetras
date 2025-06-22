/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.logica;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.estructuras.ListaSimple;
import org.persistencia.ControladoraPersistencia;
import org.persistencia.DatosCargados;
import org.visualizacion.VisualizadorGrafo;

public class Controladora {
    private final ControladoraPersistencia controlPersis;
    private VisualizadorGrafo visualizador;
    private Grafo grafoTablero;
    private ListaSimple<String> diccionario;
    private char[][] letrasTablero;

    public Controladora() {
        this.controlPersis = new ControladoraPersistencia();
    }

    public void cargarArchivo(File archivo) throws IOException {
        DatosCargados datos = controlPersis.leerArchivo(archivo);
        this.diccionario = datos.getDiccionario();
        this.letrasTablero = datos.getTablero();
        this.grafoTablero = new Grafo(this.letrasTablero);

        this.visualizador = new VisualizadorGrafo(this.grafoTablero.getVerticesDelTablero());
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

    public void buscarYVisualizarPalabra(String palabra) {
        if (grafoTablero == null) {
            JOptionPane.showMessageDialog(null, "El tablero no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (palabra == null || palabra.isEmpty() || palabra.length() < 3) {
            JOptionPane.showMessageDialog(null, "La palabra debe tener al menos 3 letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String palabraUpper = palabra.toUpperCase();
        
        long tiempoInicioBusquedaPalabra = System.nanoTime();
        ListaSimple<Vertice> caminoPalabraEncontrada = grafoTablero.buscarPalabraBFS(palabraUpper);
        long tiempoFinBusquedaPalabra = System.nanoTime();
        long tiempoTotalBusquedaPalabraNs = tiempoFinBusquedaPalabra - tiempoInicioBusquedaPalabra;

        Vertice verticeRaizBFS = null;
        if (!caminoPalabraEncontrada.estaVacia()) {
            verticeRaizBFS = caminoPalabraEncontrada.obtener(0);
        }

        HashMap<Vertice, Vertice> arbolBFS = null;
        if (verticeRaizBFS != null) {
            arbolBFS = grafoTablero.obtenerArbolBFS(verticeRaizBFS);
        }

        if (this.visualizador == null) {
            this.visualizador = new VisualizadorGrafo(this.grafoTablero.getVerticesDelTablero());
        }
        
        visualizador.mostrarArbolBFS(palabraUpper, verticeRaizBFS, arbolBFS, caminoPalabraEncontrada);

        if (!caminoPalabraEncontrada.estaVacia()) {
            JOptionPane.showMessageDialog(null, "La palabra '" + palabraUpper + "' FUE encontrada.\n"
                                         + "Tiempo de búsqueda de palabra (BFS): " + String.format("%,d", tiempoTotalBusquedaPalabraNs) + " ns",
                                         "Encontrada", JOptionPane.INFORMATION_MESSAGE);
            if (diccionario != null && !diccionario.contiene(palabraUpper)) {
                diccionario.agregar(palabraUpper);
                JOptionPane.showMessageDialog(null, "La palabra '" + palabraUpper + "' ha sido agregada al diccionario.", "Diccionario Actualizado", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La palabra '" + palabraUpper + "' NO fue encontrada.", "No Encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

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