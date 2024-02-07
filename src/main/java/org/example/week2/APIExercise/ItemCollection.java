package org.example.week2.APIExercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface ItemCollection <T> {

    List<T> getByRating(double rating);
    List<T> getSortedByReleaseDate();
}
