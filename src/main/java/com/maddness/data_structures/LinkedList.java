package com.maddness.data_structures;

/**
 * Created by maddness on 12/03/2016.
 */
public class LinkedList {

    private Node head;
    private int count;

    public LinkedList() {
        this.head = null;
        this.count = 0;
    }

    public void add(Object newElement) {
        if (head == null) {
            head = new Node(newElement);
            incrementCounter();
            return;
        }

        Node newNode = new Node(newElement);

        Node currentNode = head;
        Node nextNode = currentNode.getNextNode();

        while (nextNode != null) {
            currentNode = nextNode;
            nextNode = currentNode.getNextNode();
        }

        currentNode.setNextNode(newNode);
        incrementCounter();
    }

    public boolean contains(Object element) {
        if (count == 0) {
            return false;
        }

        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.checkEquals(element)) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }

        return false;
    }

    public Object get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should be more than zero.");
        }

        if (count < index + 1) {
            throw new IndexOutOfBoundsException();
        }

        int currentIndex = 0;
        Node currentNode = head;

        while (currentIndex != index) {
            currentNode = currentNode.getNextNode();
            currentIndex++;
        }

        return currentNode.getValue();
    }

    public void remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should be more than zero.");
        }

        if (count < index + 1) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = head.getNextNode();
            decrementCounter();
            return;
        }

        int currentIndex = 0;
        Node previousNode = null;
        Node currentNode = head;

        while (currentIndex != index) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
            currentIndex++;
        }

        previousNode.setNextNode(currentNode.getNextNode());
        decrementCounter();
    }

    private void incrementCounter() {
        this.count++;
    }

    private void decrementCounter() {
        this.count--;
    }

    public int size() {
        return count;
    }

    public String toString() {
        if (count == 0) {
            return "<...>";
        }

        Node currentNode = head;
        StringBuilder sb = new StringBuilder();

        while (currentNode != null) {
            sb.append(currentNode.toString());
            currentNode = currentNode.getNextNode();
        }

        return sb.toString();
    }

    private class Node {

        private Object value;
        private Node nextNode;

        public Node(Object value) {
            this.value = value;
            this.nextNode = null;
        }

        public Object getValue() {
            return value;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public String toString() {
            return "[" + ((value == null) ? "null" : value.toString()) + "]";
        }

        public boolean checkEquals(Object element) {
            if (value == null) {
                return element == null;
            }
            return value.equals(element);
        }
    }
}