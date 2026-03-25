package library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


public class HttpJava {

    private final static String userEndpoint = "https://jsonplaceholder.typicode.com/users";
    private final static String postEndpoint = "https://jsonplaceholder.typicode.com/posts";
    static PriorityQueue<ApiResponse> sorted = new PriorityQueue<>(Comparator.comparingLong(ApiResponse::getTotalPosts).reversed());

    private static List<Map<String, String>> fetchUsers() throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI(userEndpoint);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new ObjectMapper().readValue(response.body(), new TypeReference<>() {
            });
        }
    }

    private static List<Map<String, String>> fetchPosts() throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI(postEndpoint);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new ObjectMapper().readValue(response.body(), new TypeReference<>() {
            });
        }
    }

    private static List<ApiResponse> merge(List<Map<String, String>> users, List<Map<String, String>> posts) {
        Map<Long, Long> postCount = posts.stream().collect(Collectors.groupingBy(data -> Long.valueOf(data.get("userId")), Collectors.counting()));
        return users.stream().map(data -> {
            Long userId = Long.valueOf(data.get("userId"));
            return new ApiResponse(userId, data.get("userName"), postCount.get(userId));
        }).toList();
    }

    private static void updateSorted(List<ApiResponse> response) {
        sorted.addAll(response);
    }
    private void uploadChunk(byte[] chunk, int chunkNumber) {

    }



    public static void main(String[] args) {

    }
}
