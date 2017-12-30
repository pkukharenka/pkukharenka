package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void whenAddElementInEmptyBSTThenTrue() {
        BinaryTree<String> bst = new BinaryTree<>();
        bst.add("1");
        assertThat(bst.getRoot().getValue(), is("1"));
    }

    @Test
    public void whenAddElementLessThanRootThenLeft() {
        BinaryTree<Integer> bst = new BinaryTree<>();
        bst.add(5);
        bst.add(4);
        bst.add(6);
        bst.add(7);
        assertThat(bst.getRoot().getLeft().getValue(), is(4));
        assertThat(bst.getRoot().getRight().getValue(), is(6));
        assertThat(bst.getRoot().getRight().getRight().getValue(), is(7));
    }

}