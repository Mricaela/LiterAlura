package com.alura.br.Literalura;

import com.alura.br.Literalura.model.Livro;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GutendexMain {
    public static void main(String[] args) throws Exception {
        String searchQuery = "Jane Austen"; //
        String url = "https://gutendex.com/books/?search=" + searchQuery.replace(" ", "+");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode results = root.get("results");

        List<Livro> livros = new ArrayList<>();
        for (JsonNode book : results) {
            Livro livro = new Livro();
            livro.setTitulo(book.get("title").asText());

            JsonNode authors = book.get("authors");
            if (authors != null && authors.size() > 0) {
                JsonNode firstAuthor = authors.get(0);
                livro.setAutor(firstAuthor.get("name").asText());

                // Ano de nascimento
                if (firstAuthor.has("birth_year") && !firstAuthor.get("birth_year").isNull()) {
                    livro.setAnoNascimento(firstAuthor.get("birth_year").asInt());
                } else {
                    livro.setAnoNascimento(0);
                }

                // Ano de falecimento
                if (firstAuthor.has("death_year") && !firstAuthor.get("death_year").isNull()) {
                    livro.setAnoFalecimento(firstAuthor.get("death_year").asInt());
                } else {
                    livro.setAnoFalecimento(0);
                }

            } else {
                livro.setAutor("Desconhecido");
                livro.setAnoNascimento(0);
                livro.setAnoFalecimento(0);
            }

            livros.add(livro);
        }

        // Printando os resultados
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Nascimento: " + livro.getAnoNascimento());
            System.out.println("Ano de Falecimento: " + livro.getAnoFalecimento());
            System.out.println("------------");
        }
    }
}
