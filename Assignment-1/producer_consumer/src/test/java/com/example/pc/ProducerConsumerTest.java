package com.example.pc;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProducerConsumerTest {
    @Test
    public void testFlow() throws InterruptedException {
        SharedBuffer b = new SharedBuffer(3);
        List<Integer> s = List.of(10, 20, 30);
        List<Integer> d = new ArrayList<>();
        Thread p = new Thread(new Producer(b, s));
        Thread c = new Thread(new Consumer(b, d));
        p.start();
        c.start();
        p.join();
        Thread.sleep(1000);
        assertEquals(s.size(), d.size());
        assertTrue(d.containsAll(s));
    }

    @Test
    public void testConsumerBlocksUntilProducerAdds() throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(2);
        List<Integer> destination = new ArrayList<>();

        Thread consumer = new Thread(new Consumer(buffer, destination));

        consumer.start();

        Thread.sleep(300); // Give consumer time to block on empty buffer
        assertEquals(0, destination.size()); // Nothing consumed yet

        // Now producer adds
        buffer.put(99);

        Thread.sleep(300);

        assertTrue(destination.contains(99));
    }

    @Test
    public void testProducerBlocksOnFullBuffer() throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(1);
        List<Integer> source = List.of(5, 6);
        List<Integer> dest = new ArrayList<>();

        Thread producer = new Thread(new Producer(buffer, source));

        long start = System.currentTimeMillis();
        producer.start();

        // Delay consumer so producer fills buffer and blocks on second put
        Thread.sleep(300);

        Thread consumer = new Thread(new Consumer(buffer, dest));
        consumer.start();

        producer.join();
        long end = System.currentTimeMillis();

        assertTrue((end - start) >= 200);  // Producer MUST have blocked
        assertEquals(2, dest.size());
    }


    @Test
    public void testMultipleProducers() throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(5);
        List<Integer> destination = new ArrayList<>();

        List<Integer> s1 = List.of(1, 2, 3);
        List<Integer> s2 = List.of(4, 5, 6);

        Thread p1 = new Thread(new Producer(buffer, s1));
        Thread p2 = new Thread(new Producer(buffer, s2));
        Thread c = new Thread(new Consumer(buffer, destination));

        p1.start();
        p2.start();
        c.start();

        p1.join();
        p2.join();

        Thread.sleep(500);

        assertEquals(6, destination.size());
        assertTrue(destination.containsAll(s1));
        assertTrue(destination.containsAll(s2));
    }

    @Test
    public void testMultipleConsumers() throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(5);

        List<Integer> source = List.of(100, 200, 300, 400, 500);
        List<Integer> d1 = new ArrayList<>();
        List<Integer> d2 = new ArrayList<>();

        Thread p = new Thread(new Producer(buffer, source));
        Thread c1 = new Thread(new Consumer(buffer, d1));
        Thread c2 = new Thread(new Consumer(buffer, d2));

        p.start();
        c1.start();
        c2.start();

        p.join();
        Thread.sleep(1000);

        int totalConsumed = d1.size() + d2.size();
        assertEquals(source.size(), totalConsumed);

        for (int val : source) {
            assertTrue(d1.contains(val) || d2.contains(val));
        }
    }

}