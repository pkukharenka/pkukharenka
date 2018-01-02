package ru.job4j.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FrogTest {

    @Test
    public void whenFrogJumpNormallyThenStepsCountIs6() {
        Point start = new Point(7, 11);
        Point finish = new Point(10, 9);
        List<Point> trees = new ArrayList<>();
        trees.add(new Point(9, 14));
        trees.add(new Point(8, 5));
        Frog frog = new Frog();
        assertThat(frog.stepsCount(frog.findWay(start, finish, trees)), is(6));
    }

    @Test
    public void whenNoAnyStepsThenFrogCantJumpToTarget() {
        Point start = new Point(7, 11);
        Point finish = new Point(10, 9);
        List<Point> trees = new ArrayList<>();
        trees.add(new Point(5, 12));
        trees.add(new Point(6, 13));
        trees.add(new Point(7, 14));
        trees.add(new Point(8, 13));
        trees.add(new Point(9, 12));
        Frog frog = new Frog();
        assertThat(frog.findWay(start, finish, trees).size(), is(0));
    }

}