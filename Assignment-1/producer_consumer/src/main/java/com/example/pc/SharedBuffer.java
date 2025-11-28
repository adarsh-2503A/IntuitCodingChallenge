package com.example.pc;

import java.util.ArrayList;
import java.util.List;

public class SharedBuffer{

    private final List<Integer> buffer = new ArrayList<>(); //defining the shared buffer queue
    private final int capacity; // max capacity of shared buffer

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(int item) throws InterruptedException { //method to add items to shared queue
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(item);
        notifyAll();
    }

    public synchronized int take() throws InterruptedException { // method to remove items from shared queue
        while (buffer.isEmpty()) {
            wait();
        }
        int item = buffer.remove(0);
        notifyAll();
        return item;
    }
}
