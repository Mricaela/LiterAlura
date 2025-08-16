package com.alura.br.Literalura;

import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.model.Autor;
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
        String searchQuery = "Jane Austen";
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

                Autor autor = new Autor();
                autor.setNome(firstAuthor.get("name").asText());

                if (firstAuthor.has("birth_year") && !firstAuthor.get("birth_year").isNull()) {
                    autor.setAnoNascimento(firstAuthor.get("birth_year").asInt());
                }

                if (firstAuthor.has("death_year") && !firstAuthor.get("death_year").isNull()) {
                    autor.setAnoFalecimento(firstAuthor.get("death_year").asInt());
                }

                livro.setAutor(autor);

            } else {
                Autor autorDesconhecido = new Autor();
                autorDesconhecido.setNome("Desconhecido");
                livro.setAutor(autorDesconhecido);
            }

            // Pega o primeiro idioma da lista
            JsonNode languages = book.get("languages");
            if (languages != null && languages.size() > 0) {
                livro.setIdioma(languages.get(0).asText());
            } else {
                livro.setIdioma("Desconhecido");
            }

            // Número de downloads
            if (book.has("download_count") && !book.get("download_count").isNull()) {
                livro.setDownloads(book.get("download_count").asInt());
            } else {
                livro.setDownloads(0);
            }

            livros.add(livro);
        }

        // Printando os resultados
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor().getNome());
            System.out.println("Ano de Nascimento: " + livro.getAutor().getAnoNascimento());
            System.out.println("Ano de Falecimento: " + livro.getAutor().getAnoFalecimento());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Downloads: " + livro.getDownloads());
            System.out.println("------------");
        }
    }
}
