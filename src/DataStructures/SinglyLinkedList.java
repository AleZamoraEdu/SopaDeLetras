/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures;

import DataStructures.node.Node;
import DataStructures.exception.EmptyStructureException;
import DataStructures.exception.InvalidElementException;
import DataStructures.exception.IndexOutOfBoundsCustomException;

/**
 * A singly linked list implementation. Does not allow null elements to be
 * stored. Uses custom exceptions to avoid java.util dependencies.
 *
 * @author Alejandro Zamora
 *
 * @param <T> the type of elements held in this list
 */
public class SinglyLinkedList<T> implements IList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Node<T> getHeadNode() {
        return this.head;
    }

    @Override
    public Node<T> getTailNode() {
        return this.tail;
    }

    @Override
    public void addFirst(T data) {
        if (data == null) {
            throw new InvalidElementException("Data cannot be null");
        }
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        this.size++;
    }

    @Override
    public void addLast(T data) {
        if (data == null) {
            throw new InvalidElementException("Data cannot be null.");
        }
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;
    }

    private Node<T> getNodeAtIndex(int index) {
        // Assumes index is valid and within bounds [0, size-1]
        // This is an internal helper, public methods must validate index before calling.
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new InvalidElementException("Data cannot be null.");
        }
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsCustomException("Index: " + index + ", Size: " + this.size);
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == this.size) {
            addLast(data);
        } else {
            Node<T> newNode = new Node<>(data);
            Node<T> previousNode = getNodeAtIndex(index - 1);
            newNode.setNext(previousNode.getNext());
            previousNode.setNext(newNode);
            this.size++;
        }
    }

    @Override
    public T removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T removeAtIndex() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T set(int index, T data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(T data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
