package com.maddness.data_structures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maddness on 12/03/2016.
 */
public class LinkedListTest {

    private LinkedList list;

    @Before
    public void setUp() throws Exception {
        this.list = new LinkedList();
    }

    @Test
    public void testZeroElements() throws Exception {
        assertEquals(0, list.size());
        assertEquals("<...>", list.toString());
    }

    @Test
    public void shouldAddElements() throws Exception {
        list.add(3);

        assertEquals(1, list.size());
        assertEquals("[3]", list.toString());

        list.add(800);
        list.add(6);

        assertEquals(3, list.size());
        assertEquals("[3][800][6]", list.toString());
    }

    @Test
    public void shouldGetElements() throws Exception {
        list.add(3);
        list.add(0);
        list.add(1);

        assertEquals(3, list.get(0));
        assertEquals(1, list.get(2));
        assertEquals(0, list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenIndexInvalid() throws Exception {
        list.add(0);
        list.add(1);

        list.get(2);
    }

    @Test
    public void shouldAddNull() throws Exception {
        list.add(null);
        System.out.println(list);
    }

    @Test
    public void shouldRemoveElements() throws Exception {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(3);
        list.remove(1);

        assertEquals(2, list.size());
        assertEquals("[0][2]", list.toString());

        list.remove(1);
        list.remove(0);

        assertEquals(0, list.size());
        assertEquals("<...>", list.toString());
    }

    @Test
    public void testContainsNotNull() throws Exception {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(2));
        assertFalse(list.contains(8));
    }

    @Test
    public void testContainsNull() throws Exception {
        list.add(0);
        list.add(null);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(null));
    }
}