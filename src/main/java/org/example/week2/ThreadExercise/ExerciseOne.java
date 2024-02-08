package org.example.week2.ThreadExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExerciseOne {

    public static void main( String[] args ) {
        ExecutorService ES = Executors.newFixedThreadPool(2);
        List<Future<?>> futures = new ArrayList<>();

           for ( int i = 0; i < 26; i++ ) {
               String tasks = String.valueOf((char) ('A' + i)).repeat(3);
               Future<?> future = ES.submit(() -> System.out.println(tasks));
                futures.add(future);
            }

        ES.shutdown();

        for (Future<?> f : futures) {
            try {
                f.get(); // sørger for at hver task bliver færdig før den næste bliver kaldt
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All done");
    }


}
