/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.estructuras.ListaSimple;

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class ControladoraPersistencia {

    /**
     * Lee un archivo de texto con un formato específico y extrae de él el
     * diccionario y el tablero de la sopa de letras.
     * @param archivo El archivo a leer.
     * @return Un objeto DatosCargados que contiene el diccionario y el tablero.
     * @throws IOException Si ocurre un error de lectura o el formato es incorrecto.
     */
    public DatosCargados leerArchivo(File archivo) throws IOException {
        ListaSimple<String> diccionario = new ListaSimple<>();
        char[][] tablero = new char[4][4];

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean leyendoDic = false;
            boolean leyendoTab = false;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim().toUpperCase();

                if (linea.equals("DIC")) { leyendoDic = true; continue; }
                if (linea.equals("/DIC")) { leyendoDic = false; continue; }
                if (linea.equals("TAB")) { leyendoTab = true; continue; }
                if (linea.equals("/TAB")) { leyendoTab = false; continue; }

                if (leyendoDic && !linea.isEmpty()) {
                    diccionario.agregar(linea);
                }

                if (leyendoTab && !linea.isEmpty()) {
                    String[] letras = linea.split(",");
                    if (letras.length != 16) {
                        throw new IOException("El tablero no contiene 16 letras.");
                    }
                    int k = 0;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            tablero[i][j] = letras[k].trim().charAt(0);
                            k++;
                        }
                    }
                }
            }
        }
        return new DatosCargados(diccionario, tablero);
    }
    
    /**
     * Guarda el diccionario actual en un archivo de texto.
     * @param diccionario La lista de palabras a guardar.
     * @param archivoDestino El archivo donde se guardarán los datos.
     * @throws IOException Si ocurre un error de escritura.
     */
    public void guardarDiccionario(ListaSimple<String> diccionario, File archivoDestino) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino))) {
            bw.write("dic");
            bw.newLine();
            for (int i = 0; i < diccionario.tamano(); i++) {
                bw.write(diccionario.obtener(i));
                bw.newLine();
            }
            bw.write("/dic");
            bw.newLine();
        }
    }
}