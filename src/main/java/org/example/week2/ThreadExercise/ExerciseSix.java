package org.example.week2.ThreadExercise;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExerciseSix {

    static String[] urls = new String[]{
            "https://icanhazdadjoke.com/api",
            "https://api.chucknorris.io/jokes/random",
            "https://api.kanye.rest",
            "https://api.whatdoestrumpthink.com/api/v1/quotes/random",
            "https://api.spacexdata.com/v5/launches/latest",
            "https://pokeapi.co/api/v2/pokemon/ditto",
            "https://api.agify.io/?name=emma",
            "https://dog.ceo/api/breeds/image/random",
            "https://api.thecatapi.com/v1/images/search",
            "https://restcountries.com/v3.1/name/India?fullText=true"
    };

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ExecutorService executor = Executors.newFixedThreadPool(urls.length);

        List<Callable<String>> tasks = new ArrayList<>();
        for (String url : urls) {
            tasks.add(() -> getApiResponse(client, url));
        }

        List<Future<String>> results = executor.invokeAll(tasks);
        executor.shutdown();

        // Assuming MegaDto is a class that aggregates all API responses. You need to implement this.
        MegaDto megaDto = new MegaDto();
        for (Future<String> result : results) {
            // Here, you would parse the JSON response into a DTO and add it to the mega DTO
            // For simplicity, this example just prints out the response.
            String response = result.get(); // This line can throw InterruptedException or ExecutionException
            System.out.println(response);
            // Example: megaDto.add(parseDtoFromResponse(response));
        }

        // Print the mega DTO in a nice format
        // System.out.println(megaDto);
    }

    private static String getApiResponse(HttpClient client, String urlString) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // Define your DTO classes and the MegaDto class based on your specific requirements.
    private static class MegaDto {
        // Example fields and methods for aggregating individual DTOs
    }
}

