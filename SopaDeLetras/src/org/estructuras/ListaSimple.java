/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.estructuras;

/**
 * La clase `ListaSimple` implementa una lista enlazada simple genérica.
 * Permite almacenar y manipular una colección de elementos de cualquier tipo,
 * con operaciones básicas como agregar, obtener, eliminar, verificar tamaño y si está vacía.
 *
 * @param <T> El tipo de elementos que contendrá esta lista.
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class ListaSimple<T> {
    /**
     * Referencia al primer nodo de la lista. Si la lista está vacía, es `null`.
     */
    private Nodo<T> cabeza;
    /**
     * El número actual de elementos en la lista.
     */
    private int tamano;

    /**
     * Constructor predeterminado para `ListaSimple`.
     * Inicializa una lista enlazada simple vacía, con la cabeza apuntando a `null`
     * y el tamaño en cero.
     */
    public ListaSimple() {
        this.cabeza = null;
        this.tamano = 0;
    }

    /**
     * Agrega un nuevo elemento al final de la lista.
     *
     * @param dato El elemento de tipo `T` a agregar a la lista.
     */
    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamano++;
    }

    /**
     * Obtiene el elemento en la posición especificada por el índice.
     *
     * @param indice El índice del elemento a obtener (basado en 0).
     * @return El elemento de tipo `T` en la posición `indice`.
     * @throws IndexOutOfBoundsException Si el índice está fuera del rango válido (menor que 0 o mayor o igual al tamaño).
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * Elimina la primera ocurrencia del elemento especificado de esta lista, si está presente.
     *
     * @param dato El elemento a eliminar de la lista.
     * @return `true` si la lista contenía el elemento especificado y fue eliminado; `false` en caso contrario.
     */
    public boolean eliminar(T dato) {
        if (cabeza == null) {
            return false; // La lista está vacía, no hay nada que eliminar
        }
        // Si el elemento a eliminar es la cabeza de la lista
        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            tamano--;
            return true;
        }
        // Buscar el elemento en el resto de la lista
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(dato)) {
            actual = actual.getSiguiente();
        }
        // Si se encontró el elemento (es el siguiente nodo de 'actual')
        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente()); // Enlazar el nodo actual con el nodo después del eliminado
            tamano--;
            return true;
        }
        return false; // El elemento no se encontró en la lista
    }

    /**
     * Retorna el número de elementos en esta lista.
     *
     * @return El número de elementos en la lista.
     */
    public int tamano() {
        return tamano;
    }

    /**
     * Verifica si esta lista está vacía.
     *
     * @return `true` si la lista no contiene elementos; `false` en caso contrario.
     */
    public boolean estaVacia() {
        return tamano == 0;
    }

    /**
     * Determina si esta lista contiene el elemento especificado.
     *
     * @param dato El elemento a buscar en la lista.
     * @return `true` si la lista contiene el elemento; `false` en caso contrario.
     */
    public boolean contiene(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Sobrescribe el método `toString()` para proporcionar una representación
     * de cadena de esta lista, en el formato "[elemento1, elemento2, ..., elementoN]".
     *
     * @return Una cadena que representa la lista completa.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = cabeza;
        sb.append("[");
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Clase interna privada que representa un nodo individual en la `ListaSimple`.
     * Cada nodo contiene un dato y una referencia al siguiente nodo en la secuencia.
     *
     * @param <T> El tipo de dato que almacenará este nodo.
     */
    private static class Nodo<T> {
        /**
         * El dato almacenado en este nodo.
         */
        private T dato;
        /**
         * Referencia al siguiente nodo en la lista. Si es el último nodo, es `null`.
         */
        private Nodo<T> siguiente;

        /**
         * Constructor para la clase `Nodo`.
         * Crea un nuevo nodo con el dato especificado y el puntero al siguiente nodo inicializado en `null`.
         *
         * @param dato El dato de tipo `T` a almacenar en el nodo.
         */
        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }

        /**
         * Obtiene el dato almacenado en este nodo.
         *
         * @return El dato de tipo `T` del nodo.
         */
        public T getDato() {
            return dato;
        }

        /**
         * Obtiene la referencia al siguiente nodo.
         *
         * @return El objeto `Nodo<T>` que sigue a este nodo, o `null` si es el último.
         */
        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        /**
         * Establece la referencia al siguiente nodo.
         *
         * @param siguiente El objeto `Nodo<T>` que será el siguiente nodo.
         */
        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }
    }
}