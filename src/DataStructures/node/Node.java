/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures.node;

/**
 * Represents a node in a singly linked list
 *
 * @author Alejandro Zamora
 * @param <T> the type of data stored in the node
 */
public class Node<T> {

    private T data;
    private Node<T> next;

    /**
     * Constructs a new node with the specified data.
     * The next is initialized to null.
     * @param data the data to be stored in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Returns the data stored in this node.
     * @return the data stored in this node
     */
    public T getData() {
        return data;
    }
    
    /**
     * Sets the data for this node.
     * @param data the new data to be stored in this node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *  Returns the next node in the list.
     * @return the next node, or null if this is the last node
     */
    public Node<T> getNext() {
        return next;
    }
    
    /**
     * Sets the next node in the list.
     * @param next the node that should follow this node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        // Basic toString to avoid issues if data is null and to prevent deep recursion in complex structures
        String dataStr = (data == null) ? "null" : data.toString();
        String nextStr = (next == null) ? "null" : "Node@" + Integer.toHexString(next.hashCode());
        return "Node{data=" + dataStr + ", next=" + nextStr + "}";
    }
}
