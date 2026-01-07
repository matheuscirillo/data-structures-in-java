package com.matheuscirillo.datastructures.lists;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest {

    @Test
    public void appendTesting() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);

        list.append("A");
        assertEquals(list.getSize(), 1);

        list.append("B");
        assertEquals(list.getSize(), 2);

        DoublyLinkedList.Node<String> cNode = list.append("C");

        assertEquals(cNode.getData(), list.getLast().getData());
    }

    @Test
    public void prependTesting() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);

        list.prepend("A");
        assertEquals(list.getSize(), 1);

        list.prepend("B");
        assertEquals(list.getSize(), 2);

        DoublyLinkedList.Node<String> cNode = list.prepend("C");
        assertEquals(list.getSize(), 3);

        assertEquals(cNode.getData(), list.getFirst().getData());
    }

    @Test
    public void insertBeforeWithIndexTesting() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        list.append("A");
        list.append("B");
        list.append("C");

        list.insertBefore("D", 0);
        assertEquals("D", list.getFirst().getData());

        list.insertBefore("E", 2);
        assertEquals("E", list.get(2));
    }

    @Test
    public void insertBeforeWithIndexTestingOnly2Items() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        list.append("A");

        list.insertBefore("B", 0);
        assertEquals("B", list.getFirst().getData());
    }

    @Test
    public void insertBeforeWithNodeTesting() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        DoublyLinkedList.Node<String> a = list.append("A");
        DoublyLinkedList.Node<String> b = list.append("B");
        list.append("C");

        list.insertBefore("D", a);
        assertEquals("D", list.getFirst().getData());

        list.insertBefore("E", b);
        assertEquals("E", list.get(2));
    }

    @Test
    public void insertBeforeWithNodeTestingOnly2Items() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        DoublyLinkedList.Node<String> a = list.append("A");
        list.insertBefore("B", a);
        assertEquals("B", list.getFirst().getData());
    }

    @Test
    public void insertAfterWithIndexTesting() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        list.append("A");
        list.append("B");
        list.append("C");

        list.insertAfter("D", 0);
        assertEquals("D", list.get(1));

        list.insertAfter("E", 2);
        assertEquals("E", list.get(3));

        list.insertAfter("F", 4);
        assertEquals("F", list.getLast().getData());
    }

    @Test
    public void insertAfterWithIndexTestingOnly2Items() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        list.append("A");

        list.insertAfter("B", 0);
        assertEquals("B", list.getLast().getData());
    }

    @Test
    public void insertAfterWithNodeTesting() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        DoublyLinkedList.Node<String> a = list.append("A");
        DoublyLinkedList.Node<String> b = list.append("B");
        DoublyLinkedList.Node<String> c = list.append("C");

        list.insertAfter("D", a);
        assertEquals("D", list.get(1));

        list.insertAfter("E", b);
        assertEquals("E", list.get(3));

        list.insertAfter("E", b);
        assertEquals("E", list.get(3));

        list.insertAfter("F", c);
        assertEquals("F", list.getLast().getData());
    }

    @Test
    public void insertAfterWithNodeTestingOnly2Items() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        assertEquals(list.getSize(), 0);
        DoublyLinkedList.Node<String> a = list.append("A");
        list.insertAfter("B", a);
        assertEquals("B", list.getLast().getData());
    }

    @Test
    public void removeByIndexTestingEmptyingList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.append("A");
        DoublyLinkedList.Node<String> remove = list.remove(0);
        assertEquals(remove.getData(), "A");
        assertEquals(list.getSize(), 0);
    }

    @Test
    public void removeByIndexTestingWithMoreItems() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.append("A");
        list.remove(0);
        assertEquals(list.getSize(), 0);

        list.append("B");
        assertEquals(list.getSize(), 1);

        list.append("C");
        assertEquals(list.getSize(), 2);

        list.append("D");
        assertEquals(list.getSize(), 3);

        DoublyLinkedList.Node<String> remove = list.remove(1);
        assertEquals("C", remove.getData());
        assertEquals(list.getSize(), 2);
        assertEquals("B", list.get(0));
        assertEquals("D", list.get(1));
    }

    @Test
    public void removeByNodeTestingEmptyingList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        DoublyLinkedList.Node<String> a = list.append("A");
        list.remove(a);
        assertEquals(list.getSize(), 0);
    }

    @Test
    public void removeByNodeTestingWithMoreItems() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        DoublyLinkedList.Node<String> a = list.append("A");
        list.remove(a);
        assertEquals(list.getSize(), 0);

        list.append("B");
        assertEquals(list.getSize(), 1);

        DoublyLinkedList.Node<String> c = list.append("C");
        assertEquals(list.getSize(), 2);

        list.append("D");
        assertEquals(list.getSize(), 3);

        list.remove(c);
        assertEquals(list.getSize(), 2);
        assertEquals("B", list.get(0));
        assertEquals("D", list.get(1));
    }

    @Test
    public void forwardTraverse() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        // populate
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            list.append(String.valueOf(ch));
        }

        Iterator<String> it = list.iterator();
        int i = 0;
        while (it.hasNext())
            assertEquals(it.next(), list.get(i++));
    }

    @Test
    public void forwardTraverseOnEmptyList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        Iterator<String> it = list.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void backwardsTraverse() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        // populate
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            list.append(String.valueOf(ch));
        }

        Iterator<String> it = list.reverseIterator();
        int i = list.getSize() - 1;
        while (it.hasNext())
            assertEquals(it.next(), list.get(i--));
    }

    @Test
    public void backwardsTraverseOnEmptyList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        Iterator<String> it = list.reverseIterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void enhancedForIterationTesting() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        // populate
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            list.append(String.valueOf(ch));
        }

        int idx = 0;
        for (String s : list) {
            assertEquals(s, list.get(idx++));
        }
    }

}
