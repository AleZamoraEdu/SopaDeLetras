/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures.exception;

/**
 * Custom exception to indicate that an index is out of range.
 * Similar to IndexOutOfBoundsException but without java.util dependency for the core logic.
 * @author rubik
 */
public class IndexOutOfBoundsCustomException extends RuntimeException {

    /**
     * Constructs a new IndexOutOfBoundsCustomException with the specified detail message.
     * @param message the detail message.
     */
    public IndexOutOfBoundsCustomException(String message) {
        super(message);
    }
}