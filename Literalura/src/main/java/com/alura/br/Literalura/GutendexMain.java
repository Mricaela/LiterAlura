package com.alura.br.Literalura;

import com.google.gson.*;
import java.net.http.*;
import java.net.URI;
public class GutendexMain {
    public static void main(String[] args) throws Exception {
        String searchQuery = "Brandon Sanderson";
        String url = "https://gutendex.com/books/?search=" + searchQuery.replace(" ", "+");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class);
        JsonArray results = obj.getAsJsonArray("results");

        for (JsonElement element : results) {
            JsonObject book = element.getAsJsonObject();
            System.out.println("Título: " + book.get("title").getAsString());
        }
    }
}
