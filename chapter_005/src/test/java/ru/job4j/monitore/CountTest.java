package ru.job4j.monitore;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CountTest {


    public class CountThread extends Thread {
        private final Count count;

        public CountThread(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenTwoThreadsIncrementThen2() throws InterruptedException {
        Count count = new Count();
        Thread first = new CountThread(count);
        Thread second = new CountThread(count);

        first.start();
        second.start();

        first.join();
        second.join();


        assertThat(count.get(), is(2));
    }

}