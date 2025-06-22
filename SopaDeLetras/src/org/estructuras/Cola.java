/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.estructuras;

/**
 * La clase `Cola` implementa una estructura de datos de tipo "primero en entrar, primero en salir" (FIFO - First-In, First-Out).
 * Utiliza nodos para almacenar elementos de forma genérica, permitiendo operaciones de encolar, desencolar y verificar si está vacía.
 *
 * @param <T> El tipo de elementos que contendrá esta cola.
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class Cola<T> {

    /**
     * Referencia al primer elemento de la cola (el "frente" de la cola).
     * Si la cola está vacía, es `null`.
     */
    private Nodo<T> frente;
    /**
     * Referencia al último elemento de la cola (el "fin" de la cola).
     * Si la cola está vacía, es `null`.
     */
    private Nodo<T> fin;
    /**
     * El número actual de elementos en la cola.
     */
    private int tamano;

    /**
     * Constructor para la clase `Cola`.
     * Inicializa una cola vacía, con `frente` y `fin` apuntando a `null`
     * y el tamaño en cero.
     */
    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    /**
     * Agrega un elemento al final de la cola.
     * Este es un proceso de "encolar" (enqueue).
     *
     * @param dato El elemento de tipo `T` a agregar a la cola.
     */
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo); // El actual fin apunta al nuevo nodo
            fin = nuevoNodo; // El nuevo nodo se convierte en el fin
        }
        tamano++;
    }

    /**
     * Elimina y retorna el elemento del frente de la cola.
     * Este es un proceso de "desencolar" (dequeue).
     *
     * @return El elemento de tipo `T` que estaba al frente de la cola, o `null` si la cola está vacía.
     */
    public T desencolar() {
        if (estaVacia()) {
            return null; // No hay elementos para desencolar
        }
        T dato = frente.getDato(); // Obtener el dato del frente
        frente = frente.getSiguiente(); // Mover el frente al siguiente nodo
        
        // Si el frente se vuelve null, significa que la cola ahora está vacía
        if (frente == null) {
            fin = null; 
        }
        tamano--;
        return dato;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return `true` si la cola no contiene elementos; `false` en caso contrario.
     */
    public boolean estaVacia() {
        return tamano == 0;
    }
    
    /**
     * Retorna el número de elementos en esta cola.
     *
     * @return El número de elementos en la cola.
     */
    public int tamano() {
        return tamano;
    }
}