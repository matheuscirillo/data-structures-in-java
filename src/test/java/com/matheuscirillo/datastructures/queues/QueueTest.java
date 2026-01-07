package com.matheuscirillo.datastructures.queues;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QueueTest {

    @Test
    public void basicTesting() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals(2, queue.size());

        String dequeue = queue.dequeue();
        assertEquals("A", dequeue);
        assertEquals(1, queue.size());

        dequeue = queue.dequeue();
        assertEquals("B", dequeue);
        assertEquals(0, queue.size());

        assertNull(queue.dequeue());
    }

    @Test
    public void headWrapAround() throws NoSuchFieldException, IllegalAccessException {
        Queue<String> queue = new Queue<>(5);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");

        Field headField = queue.getClass().getDeclaredField("head");
        headField.setAccessible(true);
        assertEquals(0, (int) headField.get(queue));

        queue.dequeue();
        assertEquals(1, (int) headField.get(queue));

        queue.dequeue();
        queue.dequeue();
        assertEquals(3, (int) headField.get(queue));

        queue.dequeue();
        queue.dequeue();
        queue.enqueue("F");
        assertEquals(0, (int) headField.get(queue));
    }

    @Test
    public void queueGrowsTest() throws NoSuchFieldException, IllegalAccessException {
        Queue<String> queue = new Queue<>(5);
        Field queueField = queue.getClass().getDeclaredField("queue");
        queueField.setAccessible(true);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");

        assertEquals(5, ((Object[]) queueField.get(queue)).length);

        queue.enqueue("F");
        queue.enqueue("G");
        assertEquals(10, ((Object[]) queueField.get(queue)).length);

        queue.enqueue("H");
        queue.enqueue("I");
        queue.enqueue("J");
        queue.enqueue("K");
        queue.enqueue("L");
        queue.enqueue("M");
        assertEquals(20, ((Object[]) queueField.get(queue)).length);
    }

    @Test
    public void peekTest() {
        Queue<String> queue = new Queue<>(5);
        queue.enqueue("A");
        queue.enqueue("B");
        String peek = queue.peek();
        assertEquals("A", peek);
        assertEquals(2, queue.size());
    }
}
