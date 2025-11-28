package com.example.pc;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(5); //shared buffer with put and take
        // for insertion by producer and consumption by consumer
        List<Integer> source = List.of(1, 2, 3, 4, 5);
        List<Integer> destination = new ArrayList<>();
        Thread p = new Thread(new Producer(buffer, source));
        Thread c = new Thread(new Consumer(buffer, destination));
        p.start();
        c.start();
        p.join(); // asking main thread to wait till p thread joins
        Thread.sleep(2000);
        System.out.println("Destination: " + destination);
    }
}