package org.example.week2.ThreadExercise;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExerciseFour {

    public static void main(String[] args) {
        int cores = 8; // Apple M2 has 8 cores
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        for (int i = 0; i < cores; i++) { //  one task per core
            executor.submit(() -> {
                // CPU-intensive
                double result = 0;
                for (long j = 0; j < Integer.MAX_VALUE; j++) {
                    result += Math.sin(j) * Math.cos(j);
                }
                System.out.println("Task completed with result: " + result + " on " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}
