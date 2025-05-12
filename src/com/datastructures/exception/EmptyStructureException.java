/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datastructures.exception;

/**
 * Custom exception to indicate that an operation was attempted on an empty data
 * structure.
 *
 * @author Alejandro Zamora
 */
public class EmptyStructureException extends RuntimeException {

    /**
     * Constructs a new EmptyStructureException with the specified detail
     * message.
     *
     * @param message the detail message.
     */
    public EmptyStructureException(String message) {
        super(message);
    }
}
