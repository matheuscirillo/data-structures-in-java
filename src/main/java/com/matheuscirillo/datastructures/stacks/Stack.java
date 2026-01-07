package com.matheuscirillo.datastructures.stacks;

public class Stack<T> {

    private int top = -1;
    private T[] stack;
    private int size = 0;

    private final int DEFAULT_INITIAL_SIZE = 10;

    public Stack() {
        this.stack = (T[]) new Object[DEFAULT_INITIAL_SIZE];
    }

    public Stack(int initialSize) {
        this.stack = (T[]) new Object[initialSize];
    }

    public void push(T element) {
        if (size == stack.length) {
            grow();
        }

        stack[++this.top] = element;
        this.size++;
    }

    public T peek() {
        if (size == 0)
            return null;

        return this.stack[top];
    }

    public T pop() {
        if (size == 0)
            return null;

        T ret = this.stack[top];
        this.stack[top] = null;
        this.top--;
        this.size--;

        return ret;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] newStack = (T[]) new Object[stack.length * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        this.stack = newStack;
    }
}
