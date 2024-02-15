/*package org.example.week2.APIExercise;
import org.testng.annotations.Test;
import org.mockito.Mockito;
import org.example.week2.APIExercise.IMDB.MovieDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class MovieTest {

    @Test
    public void testPersistMovies() {

        List<IMDB.MovieDTO> movies = Arrays.asList(
                new MovieDTO("tt0111161", "The Shawshank Redemption"),
                 new MovieDTO("tt0068646", "The Godfather"),
                 new MovieDTO("tt0468569", "The Dark Knight"),
                 new MovieDTO("tt0071562", "The Godfather: Part II"),
                 new MovieDTO("tt0167260", "The Lord of the Rings: The Return of the King"),
                 new MovieDTO("tt0110912", "Pulp Fiction"),
                 new MovieDTO("tt0050083", "12 Angry Men"),
                 new MovieDTO("tt0060196", "The Good, the Bad and the Ugly"),
                 new MovieDTO("tt0109830", "Forrest Gump"),
                 new MovieDTO("tt0137523", "Fight Club"),
                 new MovieDTO("tt1375666", "Inception")
                );

        List<MovieDTO> foundMovies = searchMoviesByTitle(movies, "The Shawshank Redemption");

        // Assert that the correct movie is found
        assertTrue(foundMovies.size() == 1, "Exactly one movie should be found for the given title.");
        assertTrue(foundMovies.get(0).getTitle().equals("The Shawshank Redemption"), "The found movie should be 'The Shawshank Redemption'.");
    }

    private List<MovieDTO> searchMoviesByTitle(List<MovieDTO> movies, String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equals(title))
                .collect(Collectors.toList());
    }
}*/
