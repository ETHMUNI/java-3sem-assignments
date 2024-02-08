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
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();

        // vent på at threads er færdige
        thread1.join();
        thread2.join();

        System.out.println("Expected count: 200");
        System.out.println("Actual count: " + counter.getCount());

        // Check om counter er thread safe
        if (counter.getCount() == 200) {
            System.out.println("Counter is thread-safe");
        } else {
            System.out.println("Counter is not thread-safe");
        }
    }
}
