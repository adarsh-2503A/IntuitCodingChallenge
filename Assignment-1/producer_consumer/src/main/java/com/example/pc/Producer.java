package com.example.pc;

import java.util.List;

public class Producer implements Runnable {
    private final SharedBuffer buffer;
    private final List<Integer> source;

    public Producer(SharedBuffer buffer, List<Integer> source) {
        this.buffer = buffer;
        this.source = source;
    }

    public void run() {// adding items from source to queue
        try {
            for (Integer item : source) {
                System.out.println("Producing: " + item);
                buffer.put(item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}