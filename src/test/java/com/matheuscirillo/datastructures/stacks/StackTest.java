package com.matheuscirillo.datastructures.stacks;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void basicTest() throws NoSuchFieldException, IllegalAccessException {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        assertFalse(stack.isEmpty());
        Field stackField = stack.getClass().getDeclaredField("stack");
        stackField.setAccessible(true);
        Object[] underlyingArray = (Object[]) stackField.get(stack);

        Field sizeField = stack.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);

        assertNotNull(underlyingArray[0]);
        assertNotNull(underlyingArray[1]);
        assertNotNull(underlyingArray[2]);
        assertNotNull(underlyingArray[3]);
        assertEquals(4, (int) sizeField.get(stack));

        assertEquals("D", stack.pop());
        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        assertEquals(0, (int) sizeField.get(stack));

        assertNull(underlyingArray[0]);
        assertNull(underlyingArray[1]);
        assertNull(underlyingArray[2]);
        assertNull(underlyingArray[3]);
    }

    @Test
    public void growTest() throws NoSuchFieldException, IllegalAccessException {
        Stack<String> stack = new Stack<>(2);
        Field stackField = stack.getClass().getDeclaredField("stack");
        stackField.setAccessible(true);
        Object[] underlyingArray = (Object[]) stackField.get(stack);

        assertEquals(2, underlyingArray.length);

        stack.push("A");
        stack.push("B");

        Field sizeField = stack.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);
        int size = (int) sizeField.get(stack);
        assertEquals(2, size);


        stack.push("C");
        underlyingArray = (Object[]) stackField.get(stack);
        size = (int) sizeField.get(stack);
        assertEquals(4, underlyingArray.length);
        assertEquals(3, size);

    }

    @Test
    public void peekTest() throws NoSuchFieldException, IllegalAccessException {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        String peek = stack.peek();
        assertEquals("A", peek);
        stack.push("B");
        peek = stack.peek();
        assertEquals("B", peek);

        Field sizeField = stack.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);
        int size = (int) sizeField.get(stack);
        assertEquals(2, size);
    }
}
