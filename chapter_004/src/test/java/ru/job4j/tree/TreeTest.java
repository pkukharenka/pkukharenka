package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test(expected = InvalidRootException.class)
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
        tree.add(4, 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateTreeThenException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator<Integer> it = tree.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void whenAllNodesHasLessThanTwoChildsThenBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        assertTrue(tree.isBinary());
    }

}