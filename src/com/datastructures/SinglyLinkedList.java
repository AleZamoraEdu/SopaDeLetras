/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datastructures;

import com.datastructures.node.Node;
import com.datastructures.exception.EmptyStructureException;
import com.datastructures.exception.InvalidElementException;
import com.datastructures.exception.IndexOutOfBoundsCustomException;

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
        if (isEmpty()) {
            throw new EmptyStructureException("List is empty, cannot remove first element");
        }
        T removedData = this.head.getData();
        Node<T> temp = this.head;
        this.head = this.head.getNext();
        this.size--;
        if (isEmpty()) { // If the list becomes empty
            this.tail = null;
        }
        if (temp != null) {
            temp.setNext(null);
        }
        return removedData;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new EmptyStructureException("List is empty, cannot remove first element");
        }
        if (this.size == 1) {
            // If only one element, removeFirst handles head and tail update correctly
            return removeFirst();
        }

        // To remove the last, we need to find the second to last node
        Node<T> current = this.head;
        // Iterate until current.getNext() is the tail
        while (current.getNext() != this.tail) {
            current = current.getNext();
        }
        T removedData = this.tail.getData();

        this.tail = current;
        this.tail.setNext(null);
        this.size--;
        return removedData;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsCustomException("Index: " + index + ", Size: " + this.size);
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == this.size - 1) {
            return removeLast();
        }

        Node<T> previousNode = getNodeAtIndex(index - 1);
        Node<T> nodeToRemove = previousNode.getNext();
        T removedData = nodeToRemove.getData();
        previousNode.setNext(nodeToRemove.getNext());
        nodeToRemove.setData(null); // Optional
        nodeToRemove.setNext(null);
        this.size--;
        // If nodeToRemove was the tail, the tail pointer would be incorrect.
        // This is why we explicitly call removeLast() if index == this.size - 1.
        return removedData;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsCustomException("Index: " + index + ", Size: " + this.size);
        }
        return getNodeAtIndex(index).getData();
    }

    @Override
    public void set(int index, T data) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsCustomException("Index: " + index + ", Size: " + this.size);
        }
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsCustomException("Index: " + index + ", Size: " + this.size);
        }
        Node<T> nodeToSet = getNodeAtIndex(index);
        nodeToSet.setData(data);
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            // Consistent with add operations that disallow null.
            return false;
        }

        Node<T> current = this.head;
        while (current != null) {
            if (data.equals(current.getData())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        Node<T> current = this.head;
        while (current != null) {
            Node<T> next = current.getNext();
            current.setData(null);
            current.setNext(null);
            current = next;
        }
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = this.head;
        while (current != null) {
            sb.append(current.getData() == null ? "null" : current.getData().toString());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString() + " Size: " + this.size + ")";
    }

}
