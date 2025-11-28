package com.example.pc;

import java.util.List;

public class Consumer implements Runnable {
    private final SharedBuffer buffer;
    private final List<Integer> destination;

    public Consumer(SharedBuffer buffer, List<Integer> destination) {
        this.buffer = buffer;
        this.destination = destination;
    }

    public void run() {
        try {
            while (true) {//removing items from queue to add to destination
                Integer item = buffer.take();
                System.out.println("Consuming: " + item);
                destination.add(item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}