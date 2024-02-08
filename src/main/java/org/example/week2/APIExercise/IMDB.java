package org.example.week2.APIExercise;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


public class IMDB {
    public static void main(String[] args) {
        IMDB example = new IMDB();
        MovieDTO movie = null;
        try {
            movie = example.getMovie("tt0111161"); // IMDB ID for The Shawshank Redemption
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (movie != null) {
            if (movie != null) {
                System.out.println(movie);
                // Check if releaseDate is not null before attempting to parse it
                if (movie.getReleaseDate() != null && !movie.getReleaseDate().isEmpty()) {
                    LocalDate releaseDate = LocalDate.parse(movie.getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE);
                } else {
                    System.out.println("Release date is not available.");
                }
            } else {
                System.out.println("Failed to get a movie.");
            }
            }
    }

    public MovieDTO getMovie(String imdbId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String apiKey = "258015c949ccc84c70d17dd737699dbc";
        String url = "https://api.themoviedb.org/3/find/" + imdbId
                + "?api_key=" + apiKey + "&external_source=imdb_id"; // Corrected URL

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        Gson gson = new Gson();

        MovieDetails details = gson.fromJson(responseBody, MovieDetails.class);

        // This part depends on the actual structure of the response
        // You need to extract the first movie from the results, if available
        if (details != null && details.movieResults != null && !details.movieResults.isEmpty()) {
            return details.movieResults.get(0);
            }
        return null;
    }

    @Getter
    public class MovieController implements ItemCollection<MovieDTO>{

        private List<MovieDTO> movies;

        public MovieController(List<MovieDTO> movies) {
            this.movies = movies;
        }

        public List<MovieDTO> getByRating(double rating) {
            return movies.stream()
                    .filter(movies -> movies.vote_average >= rating)
                    .collect(Collectors.toList());
        }

        // Get movies sorted by release date descending
        public List<MovieDTO> getSortedByReleaseDate() {
            return movies.stream()
                    .sorted(Comparator.comparing(MovieDTO::getReleaseDate).reversed())
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    @ToString
    public static class MovieDTO {
        private String id;
        private String title;
        private String overview;
        @SerializedName("release_date") // This line maps the JSON property to the field
        private String releaseDate;
        private double vote_average;

        public MovieDTO(String id, String title) {
            this.id = id;
            this.title = title;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.vote_average = vote_average;
        }
    }

    private static class MovieDetails {
        @SerializedName("movie_results")
        private List<MovieDTO> movieResults;
    }
}
