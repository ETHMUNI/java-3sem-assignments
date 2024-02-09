package org.example.week2.ThreadExercise;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

        List<Callable<responseDto>> tasks = new ArrayList<>();
        for (String url : urls) {
            tasks.add(() -> new responseDto(url, getApiResponse(client, url)));
        }

        List<Future<responseDto>> results = executor.invokeAll(tasks);
        executor.shutdown();

        MegaDto megaDto = new MegaDto();
        for (Future<responseDto> result : results) {
            responseDto responseDto = result.get();
            megaDto.addResponseDto(responseDto);
        }

        System.out.println(megaDto);
    }

    private static String getApiResponse(HttpClient client, String urlString) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Getter
    @Setter
    private static class responseDto {
        private String url;
        private String response;
        private String title;
        private String desc;

        public responseDto(String url, String response) {
            this.url = url;
            this.response = response;
        }

        @Override
        public String toString() {
            return "responseDto{" +
                    "url='" + url + '\'' +
                    ", response='" + response + '\'' +
                    ", title='" + title + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    private static class MegaDto {
        private List<responseDto> responses = new ArrayList<>();

        public void addResponseDto(responseDto dto) {
            responses.add(dto);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (responseDto dto : responses) {
                sb.append(dto.toString()).append("\n"); // Bruger responseDto toString metode
            }
            return sb.toString();
        }
    }


}