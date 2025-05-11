package DataStructures;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 * Interface for a generic List data structure
 * @author rubik
 * @param <T> the type of elemnte held in this list
 */
public interface IList<T> {
    
    /**
     * Adds an element to the beginning of the list.
     * @param data the data to be added
     */
    void addFirst(T data);
    /**
     * Adds an element to the end of the list.
     * @param data the data to be added
     */
    
    void addLast(T data);
    /**
     * Adds an element at the specified index in the list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     * @param index the index at which the specified element is to be inserted
     * @param data the data to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > getSize())
     */
    
    void addAtIndex(int index, T data);
    /**
     * Removes and returns the first element from the list.
     * @return the first element from the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    
    T removeFirst();
    /**
     * Removes and returns the last element from the list.
     * @return the last element from the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    
    T removeLast();
    /**
     * Removes and returns the element at the specified index in the list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= getSize())
     */
    
    T removeAtIndex();
    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= getSize())
     */
    
    T get(int index);
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index index of the element to replace
     * @param data element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= getSize())
     */
    
    T set(int index, T data);
    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    
    boolean isEmpty();
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    
    int getSize();
    /**
     * Returns true if this list contains the specified element.
     * More formally, returns true if and only if this list contains
     * at least one element e such that (o==null ? e==null : o.equals(e)).
     * @param data element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    
    boolean contains(T data);
    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    
    void clear();
    /**
     * Returns the head node of the list.
     * This is generally not part of a public list interface but can be useful for testing
     * or specific advanced operations.
     * @return the head node, or null if the list is empty.
     */
    
    Node<T> getHeadNode();
    /**
     * Returns the tail node of the list.
     * This is generally not part of a public list interface but can be useful for testing
     * or specific advanced operations.
     * @return the tail node, or null if the list is empty.
     */
    
    Node<T> getTailNode();
    // For testing or specific needs
}
