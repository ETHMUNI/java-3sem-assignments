package org.example.week2.ThreadExercise;

public class ExerciseTwo {

    private static class Counter {
        private int count = 0;

        // Method to increment the count, synchronized to ensure thread safety
        public synchronized void increment() {
            count++;
        }

        // Method to retrieve the current count value
        public synchronized int getCount() {
            return count;
        }
    }

    // Demonstration på at counteren er thread safe
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                System.out.println("thread1: " + counter.getCount());
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                System.out.println("thread2: " + counter.getCount());
            }
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
    }
}
