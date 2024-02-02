package org.example.week1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.CompletableFuture.allOf;

class Task {
    void run() {
        try {
            Thread.sleep(1000); // Simulate 1 second of work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> new Task().run())
                    .exceptionally(ex -> {
                    System.out.println("Exception in future1: " + ex.getMessage());
                    return null;
                });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> new Task().run())
                .exceptionally(ex -> {
                    System.out.println("Exception in future2: " + ex.getMessage());
                    return null;
                });


           CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
                allFutures.thenRun(() -> System.out.println("All tasks completed!"));



        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            executorService.submit(() -> new Task().run()).get();
            executorService.submit(() -> new Task().run()).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Executor tasks were interrupted.");
        } catch (ExecutionException e) {
            System.out.println("Exception occurred in ExecutorService task: " + e.getCause().getMessage());
        } finally {
            executorService.shutdown();
        }

        executorService.shutdown();
        System.out.println("All ExecutorService tasks submitted.");

    }
}
