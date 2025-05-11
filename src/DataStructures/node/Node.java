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
        return "Node{" +
                "data=" + (data != null ? data.toString() : "null")
                +
                // Avoid recursively printing the whole list in toString of Node
                ", next=" + (next != null ? "Node@" + 
                Integer.toHexString(next.hashCode()) : "null");
    }
}
