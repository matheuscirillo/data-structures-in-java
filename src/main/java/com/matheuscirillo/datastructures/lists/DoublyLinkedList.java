package com.matheuscirillo.datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    public Node<T> prepend(T data) {
        if (this.first == null) {
            return append(data);
        } else {
            return insertBefore(data, 0);
        }
    }

    public Node<T> append(T data) {
        Node<T> newNode;
        if (this.first == null) {
            newNode = new Node<>(null, data, null);
            this.first = newNode;
            this.last = this.first;
            size++;
        } else {
            newNode = insertAfter(data, size == 0 ? 0 : size - 1);
        }

        return newNode;
    }

    public T get(int index) {
        return findNodeOnIndex(index).data;
    }

    public Node<T> insertAfter(T data, int index) {
        validateIndex(index);
        Node<T> node = findNodeOnIndex(index);
        return insertAfter(data, node);
    }

    public Node<T> insertAfter(T data, Node<T> node) {
        return doInsert(node, data, InsertMode.AFTER);
    }

    public Node<T> insertBefore(T data, int index) {
        validateIndex(index);
        Node<T> node = findNodeOnIndex(index);
        return insertBefore(data, node);
    }

    public Node<T> insertBefore(T data, Node<T> node) {
        return doInsert(node, data, InsertMode.BEFORE);
    }

    public Node<T> remove(int index) {
        validateIndex(index);
        Node<T> node = findNodeOnIndex(index);
        remove(node);
        return node;
    }

    public void remove(Node<T> node) {
        if (node == first) {
            first = node.next;
        } else if (node == last) {
            last = node.prev;
        }

        if (node.prev != null)
            node.prev.next = node.next;

        if (node.next != null)
            node.next.prev = node.prev;

        size--;
    }

    private Node<T> findNodeOnIndex(int index) {
        boolean shouldTraverseForward = index == 0 || index < (size / 2);
        Node<T> curNode = shouldTraverseForward ? this.first : this.last;
        int remainingSteps = shouldTraverseForward ? index : size - index - 1;
        while (remainingSteps > 0) {
            curNode = shouldTraverseForward ? curNode.next : curNode.prev;
            remainingSteps--;
        }

        return curNode;
    }

    private Node<T> doInsert(Node<T> node, T data, InsertMode mode) {
        // mode = 0 = before, mode = 1 = after
        Node<T> newNode;
        if (mode == InsertMode.BEFORE) {
            newNode = new Node<>(node.prev, data, node);
            if (node.prev != null)
                node.prev.next = newNode;
            else
                this.first = newNode;

            node.prev = newNode;
        } else {
            newNode = new Node<>(node, data, node.next);
            if (node.next != null)
                node.next.prev = newNode;
            else
                this.last = newNode;

            node.next = newNode;
        }

        size++;
        return newNode;
    }

    private void validateIndex(int index) {
        if (index > size - 1)
            throw new ArrayIndexOutOfBoundsException("index %d is not valid. current size is %d".formatted(index, size));

        if (index < 0)
            throw new ArrayIndexOutOfBoundsException("index cannot be negative");
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> getLast() {
        return last;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> curNode = this.first;
        if (curNode != null)
            do {
                sb.append(curNode.data);
                if (curNode.next != null) {
                    sb.append(", ");
                }
            } while ((curNode = curNode.next) != null);

        sb.append("]");

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            Node<T> node;

            @Override
            public boolean hasNext() {
                return node == null ? first != null : node.next != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("No remaining elements");

                if (node == null)
                    node = first;
                else
                    node = node.next;

                return node.data;
            }
        };
    }

    public Iterator<T> reverseIterator() {
        return new Iterator<>() {

            private Node<T> node;

            @Override
            public boolean hasNext() {
                return node == null ? last != null : node.prev != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("No remaining elements");

                if (node == null)
                    node = last;
                else
                    node = node.prev;

                return node.data;
            }
        };
    }

    public static class Node<T> {
        private Node<T> prev;
        private T data;
        private Node<T> next;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }
    }

    private enum InsertMode {
        BEFORE, AFTER;
    }

}
