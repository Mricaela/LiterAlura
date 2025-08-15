package com.alura.br.Literalura.Service;

import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.model.LivroGutendex;
import com.alura.br.Literalura.model.AuthorGutendex;
import com.alura.br.Literalura.model.RootResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    public List<Livro> buscarLivrosPorAutor(String autor) throws Exception {
        String url = "https://gutendex.com/books/?search=" + autor.replace(" ", "+");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        ObjectMapper mapper = new ObjectMapper();
        RootResponse rootResponse = mapper.readValue(json, RootResponse.class);

        List<Livro> livros = new ArrayList<>();
        for (LivroGutendex lg : rootResponse.getResults()) {
            Livro livro = new Livro();
            livro.setTitulo(lg.getTitle());

            if (lg.getAuthors() != null && !lg.getAuthors().isEmpty()) {
                AuthorGutendex author = lg.getAuthors().get(0);
                livro.setAutor(author.getName());
                livro.setAnoNascimento(author.getBirthYear());
                livro.setAnoFalecimento(author.getDeathYear());
            } else {
                livro.setAutor("Desconhecido");
                livro.setAnoNascimento(0);
                livro.setAnoFalecimento(0);
            }

            livros.add(livro);
        }

        return livros;
    }
}
