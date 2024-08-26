package com.matheuscirillo.datastructures.queues;

public class Queue<T> {

    private T[] queue;
    private int size = 0;
    private int tail = -1;
    private int head = 0;

    private final int DEFAULT_INITIAL_CAPACITY = 10;

    public Queue() {
        this.queue = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public Queue(int defaultInitialCapacity) {
        this.queue = (T[]) new Object[defaultInitialCapacity];
    }

    public void enqueue(T element) {
        if (size == this.queue.length)
            grow();

        if (tail == this.queue.length - 1) {
            this.tail = -1;
        }

        this.queue[++tail] = element;

        this.size++;
    }

    public T dequeue() {
        if (size == 0)
            return null;

        T element = this.queue[this.head];
        this.queue[this.head] = null;
        if (this.head == this.queue.length - 1) {
            this.head = 0;
        } else {
            this.head++;
        }

        this.size--;

        return element;
    }

    public T peek() {
        if (size == 0)
            return null;

        return this.queue[head];
    }

    public int size() {
        return this.size;
    }

    private void grow() {
        T[] newQueue = (T[]) new Object[this.queue.length * 2];
        System.arraycopy(this.queue, 0, newQueue, 0, this.queue.length);
        this.queue = newQueue;
    }
}
