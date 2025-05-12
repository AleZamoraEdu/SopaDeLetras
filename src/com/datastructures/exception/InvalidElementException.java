/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datastructures.exception;

/**
 * Custom exception to indicate that an invalid element was provided
 * to a data structure operation (e.g., null when nulls are not permitted).
 * @author Alejandro Zamora
 */

public class InvalidElementException extends RuntimeException {

    /**
     * Constructs a new InvalidElementException with the specified detail message.
     * @param message the detail message.
     */
    public InvalidElementException(String message) {
        super(message);
    }
}