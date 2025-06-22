/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.estructuras;

/**
 * La clase `Nodo` representa un elemento individual en una estructura de datos enlazada,
 * Cada nodo contiene un dato y una referencia al siguiente nodo en la secuencia.
 * Es una clase genérica para poder almacenar cualquier tipo de dato.
 *
 * @param <T> El tipo de dato que almacenará este nodo.
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class Nodo<T> {

    /**
     * El dato de tipo `T` que se almacena en este nodo.
     */
    private T dato;
    /**
     * Una referencia al siguiente `Nodo` en la secuencia.
     * Si este es el último nodo, su valor es `null`.
     */
    private Nodo<T> siguiente;

    /**
     * Constructor para la clase `Nodo`.
     * Crea una nueva instancia de `Nodo` con el dato especificado y el puntero
     * al siguiente nodo inicializado en `null`.
     *
     * @param dato El dato de tipo `T` a ser almacenado en este nodo.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null; // Por defecto, un nuevo nodo no apunta a nada.
    }

    /**
     * Obtiene el dato almacenado en este nodo.
     *
     * @return El dato de tipo `T` contenido en el nodo.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato para este nodo.
     *
     * @param dato El nuevo dato de tipo `T` a ser almacenado en el nodo.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo en la secuencia.
     *
     * @return El objeto `Nodo que sigue a este nodo, o `null` si este es el último nodo.
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }
    /**
     * Establece la referencia al siguiente nodo en la secuencia.
     *
     * @param siguiente El objeto `Nodo` que será el siguiente nodo.
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
