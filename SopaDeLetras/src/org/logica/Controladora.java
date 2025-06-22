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

/**
 * La clase `Controladora` actúa como la capa de lógica de negocio principal
 * de la aplicación Sopa de Letras. Coordina las operaciones entre la interfaz
 * de usuario (GUI), la capa de persistencia y las estructuras de datos/algoritmos
 * de búsqueda. Es responsable de cargar datos, realizar búsquedas de palabras
 * (individuales o de todo el diccionario) y gestionar la visualización de resultados.
 *
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class Controladora {
    /**
     * Instancia de la controladora de persistencia para manejar la lectura y escritura de archivos.
     */
    private final ControladoraPersistencia controlPersis;
    /**
     * Instancia del visualizador de grafo para mostrar el proceso de búsqueda BFS.
     */
    private VisualizadorGrafo visualizador;
    /**
     * El grafo que representa el tablero de la sopa de letras, utilizado para las búsquedas.
     */
    private Grafo grafoTablero;
    /**
     * La lista de palabras que conforman el diccionario para la búsqueda.
     */
    private ListaSimple<String> diccionario;
    /**
     * La matriz de caracteres 4x4 que representa las letras del tablero de la sopa de letras.
     */
    private char[][] letrasTablero;

    /**
     * Constructor de la clase `Controladora`.
     * Inicializa la instancia de {ControladoraPersistencia}.
     */
    public Controladora() {
        this.controlPersis = new ControladoraPersistencia();
    }

    /**
     * Carga un archivo que contiene tanto el tablero de la sopa de letras como el diccionario.
     * Delega la lectura al {ControladoraPersistencia} y luego inicializa
     * el tablero de letras, el diccionario y construye el {Grafo} del tablero.
     * También inicializa el {VisualizadorGrafo}.
     *
     * @param archivo El {File} que apunta al archivo de entrada.
     * @throws IOException Si ocurre un error durante la lectura del archivo.
     */
    public void cargarArchivo(File archivo) throws IOException {
        DatosCargados datos = controlPersis.leerArchivo(archivo);
        this.diccionario = datos.getDiccionario();
        this.letrasTablero = datos.getTablero();
        this.grafoTablero = new Grafo(this.letrasTablero);

        // Se inicializa el visualizador con los vértices del tablero.
        this.visualizador = new VisualizadorGrafo(this.grafoTablero.getVerticesDelTablero());
    }

    /**
     * Busca todas las palabras del diccionario en el tablero de la sopa de letras
     * utilizando el algoritmo de búsqueda en profundidad (DFS - Depth First Search).
     * Mide el tiempo transcurrido para la operación.
     *
     * @return Un objeto {ResultadoBusqueda} que contiene la lista de palabras
     * encontradas y el tiempo total de ejecución en nanosegundos.
     * Si el tablero o el diccionario no han sido cargados, retorna un resultado vacío.
     */
    public ResultadoBusqueda buscarTodasLasPalabras() {
        if (grafoTablero == null || diccionario == null) {
            return new ResultadoBusqueda(new ListaSimple<>(), 0);
        }
        
        ListaSimple<String> encontradas = new ListaSimple<>();
        long tiempoInicio = System.nanoTime(); // Marca de tiempo de inicio

        for (int i = 0; i < diccionario.tamano(); i++) {
            String palabra = diccionario.obtener(i);
            // Las palabras deben tener al menos 3 letras para ser válidas.
            if (palabra.length() >= 3 && grafoTablero.buscarPalabraDFS(palabra)) {
                encontradas.agregar(palabra);
            }
        }
        long tiempoFin = System.nanoTime(); // Marca de tiempo de fin
        long tiempoTotalNs = tiempoFin - tiempoInicio; // Cálculo del tiempo total
        
        return new ResultadoBusqueda(encontradas, tiempoTotalNs);
    }

    /**
     * Busca una palabra específica en el tablero utilizando el algoritmo de búsqueda
     * en amplitud (BFS - Breadth First Search) y visualiza el proceso.
     * Si la palabra es encontrada, el camino recorrido por BFS y el árbol de búsqueda
     * se muestran mediante el {VisualizadorGrafo}.
     * Si la palabra se encuentra y no está en el diccionario, la agrega.
     *
     * @param palabra La palabra {@link String} a buscar en el tablero.
     * Requiere que el tablero haya sido cargado y que la palabra tenga al menos 3 letras.
     */
    public void buscarYVisualizarPalabra(String palabra) {
        if (grafoTablero == null) {
            JOptionPane.showMessageDialog(null, "El tablero no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (palabra == null || palabra.isEmpty() || palabra.length() < 3) {
            JOptionPane.showMessageDialog(null, "La palabra debe tener al menos 3 letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String palabraUpper = palabra.toUpperCase(); // Normaliza la palabra a mayúsculas
        
        long tiempoInicioBusquedaPalabra = System.nanoTime();
        // Busca la palabra usando BFS y obtiene el camino si es encontrada
        ListaSimple<Vertice> caminoPalabraEncontrada = grafoTablero.buscarPalabraBFS(palabraUpper);
        long tiempoFinBusquedaPalabra = System.nanoTime();
        long tiempoTotalBusquedaPalabraNs = tiempoFinBusquedaPalabra - tiempoInicioBusquedaPalabra;

        Vertice verticeRaizBFS = null;
        if (!caminoPalabraEncontrada.estaVacia()) {
            verticeRaizBFS = caminoPalabraEncontrada.obtener(0); // El primer vértice del camino es la raíz BFS
        }

        HashMap<Vertice, Vertice> arbolBFS = null;
        // Solo obtiene el árbol BFS si se encontró al menos un vértice inicial
        if (verticeRaizBFS != null) {
            arbolBFS = grafoTablero.obtenerArbolBFS(verticeRaizBFS);
        }

        // Asegura que el visualizador esté inicializado, si no lo está.
        if (this.visualizador == null) {
            this.visualizador = new VisualizadorGrafo(this.grafoTablero.getVerticesDelTablero());
        }
        
        // Muestra el árbol BFS y el camino de la palabra encontrada
        visualizador.mostrarArbolBFS(palabraUpper, verticeRaizBFS, arbolBFS, caminoPalabraEncontrada);

        if (!caminoPalabraEncontrada.estaVacia()) {
            JOptionPane.showMessageDialog(null, "La palabra '" + palabraUpper + "' FUE encontrada.\n"
                                                 + "Tiempo de búsqueda de palabra (BFS): " + String.format("%,d", tiempoTotalBusquedaPalabraNs) + " ns",
                                                 "Encontrada", JOptionPane.INFORMATION_MESSAGE);
            // Si la palabra se encontró y no estaba en el diccionario, la agrega.
            if (diccionario != null && !diccionario.contiene(palabraUpper)) {
                diccionario.agregar(palabraUpper);
                JOptionPane.showMessageDialog(null, "La palabra '" + palabraUpper + "' ha sido agregada al diccionario.", "Diccionario Actualizado", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La palabra '" + palabraUpper + "' NO fue encontrada.", "No Encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Busca una palabra individual en el tablero utilizando el algoritmo de búsqueda
     * en profundidad (DFS - Depth First Search). Mide el tiempo transcurrido para la operación.
     *
     * @param palabra La palabra {@link String} a buscar.
     * @return Un objeto {@link ResultadoBusquedaIndividual} que indica si la palabra fue encontrada,
     * un camino (actualmente no utilizado por DFS en este contexto), y el tiempo de ejecución en nanosegundos.
     * Retorna un resultado negativo si el tablero no está cargado o la palabra es inválida.
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

    /**
     * Guarda el diccionario actual en un archivo de texto.
     *
     * @param archivoDestino El { File} donde se guardará el diccionario.
     * @throws IOException Si ocurre un error durante la escritura del archivo,
     * o si no hay un diccionario cargado.
     */
    public void guardarDiccionario(File archivoDestino) throws IOException {
        if (this.diccionario == null) {
            throw new IOException("No hay un diccionario cargado para guardar.");
        }
        controlPersis.guardarDiccionario(this.diccionario, archivoDestino);
    }
    
    /**
     * Obtiene la matriz de caracteres que representa el tablero actual de la sopa de letras.
     *
     * @return {char}
     */
    public char[][] getLetrasTablero() {
        return letrasTablero;
    }

    /**
     * Obtiene la lista de palabras que conforman el diccionario actualmente cargado.
     *
     * @return La {ListaSimple} de {String} del diccionario.
     */
    public ListaSimple<String> getDiccionario() {
        return diccionario;
    }
}