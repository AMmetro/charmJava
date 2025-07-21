package ru.eliseev.charm.back;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

// client for crete HTTP request to any url
public class CharmHttpClientRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/ppp"))
                    .setHeader("My-Token", "test")
                    .GET()
//                  .POST (HttpRequest.BodyPublisher.ofString("тело запроса"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> map = httpResponse.headers().map();

            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse);
            System.out.println(map);
            System.out.println(httpResponse.body());
        }
    }
}
