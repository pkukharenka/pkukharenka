package ru.job4j.test;

import org.junit.Test;

import java.util.TreeMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SaxParserTest {


    @Test
    public void whenAddOrderInBidAndVolumeMoreThanAskThenAskRemoveAndVolumeDecrease() {
        Analyse a = new Analyse();
        TreeMap<Integer, Integer> bid = new TreeMap<>();
        TreeMap<Integer, Integer> ask = new TreeMap<>();
        ask.put(9970, 20);
        Order order = new Order(99.7d, 50);
        a.checkPrice(bid, ask, order);
        assertNull(ask.get(9970));
        assertThat(bid.get(9970), is(30));
    }

    @Test
    public void whenAddOrderInAskAndVolumeMoreThanBidThenBidRemoveAndAskVolumeDecrease() {
        Analyse a = new Analyse();
        TreeMap<Integer, Integer> bid = new TreeMap<>();
        TreeMap<Integer, Integer> ask = new TreeMap<>();
        bid.put(10020, 30);
        Order order = new Order(100.2d, 50);
        a.checkPrice(ask, bid, order);
        assertNull(bid.get(10020));
        assertThat(ask.get(10020), is(20));
    }

    @Test
    public void whenAddOrderInBidAndVolumeLessThanAskThenAskVolumeDecrease() {
        Analyse a = new Analyse();
        TreeMap<Integer, Integer> bid = new TreeMap<>();
        TreeMap<Integer, Integer> ask = new TreeMap<>();
        ask.put(9970, 70);
        Order order = new Order(99.7d, 50);
        a.checkPrice(bid, ask, order);
        assertNull(bid.get(9970));
        assertThat(ask.get(9970), is(20));
    }

    @Test
    public void whenAddOrderInBidAndVolumeEqualsAskThenAskRemove() {
        Analyse a = new Analyse();
        TreeMap<Integer, Integer> bid = new TreeMap<>();
        TreeMap<Integer, Integer> ask = new TreeMap<>();
        ask.put(9830, 70);
        Order order = new Order(98.3d, 70);
        a.checkPrice(bid, ask, order);
        assertNull(bid.get(9830));
        assertNull(ask.get(9830));
    }
}