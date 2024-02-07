package org.example.week2.APIExercise;
import org.testng.annotations.Test;
import org.mockito.Mockito;
import org.example.week2.APIExercise.MovieDTO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class MovieTest {

    @Test
    public void testPersistMovies() {
        IMDB.MovieDTO mockMovieDTO = mock(IMDB.MovieDTO.class);
        // Sample movie data
        IMDB.MovieDTO shawshank = new MovieDTO("tt0111161", "The Shawshank Redemption", "Two imprisoned men bond over a number of years...", "1994-09-23", 9.3);
        IMDB.MovieDTO godfather = new MovieDTO("tt0068646", "The Godfather", "The aging patriarch of an organized crime dynasty transfers control...", "1972-03-14", 9.2);

    }
}
