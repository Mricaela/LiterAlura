package com.alura.br.Literalura.Service;

import com.alura.br.Literalura.model.Autor;
import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.Repository.LivroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    @Autowired
    private LivroRepository livroRepository;

    private final String BASE_URL = "https://gutendex.com/books/";

    // Buscar livros por autor (vários resultados)
    public List<Livro> buscarLivrosPorAutor(String nomeAutor) throws Exception {
        String url = BASE_URL + "?search=" + nomeAutor.replace(" ", "+");
        List<Livro> livros = buscarLivros(url);

        // Salva todos os livros encontrados
        livros.forEach(livroRepository::save);
        return livros;
    }

    // Buscar livro por título (apenas o primeiro resultado)
    public Livro buscarLivroPorTitulo(String titulo) throws Exception {
        String url = BASE_URL + "?search=" + titulo.replace(" ", "+");

        List<Livro> livros = buscarLivros(url);
        if (!livros.isEmpty()) {
            Livro livro = livros.get(0);

            // Salva no banco
            livroRepository.save(livro);
            return livro;
        }
        return null;
    }

    // Método auxiliar para processar a resposta da API
    private List<Livro> buscarLivros(String url) throws Exception {
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

            // Autor
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

            // Idioma (primeiro)
            JsonNode languages = book.get("languages");
            if (languages != null && languages.size() > 0) {
                livro.setIdioma(languages.get(0).asText());
            } else {
                livro.setIdioma("Desconhecido");
            }

            // Downloads
            if (book.has("download_count") && !book.get("download_count").isNull()) {
                livro.setDownloads(book.get("download_count").asInt());
            } else {
                livro.setDownloads(0);
            }

            livros.add(livro);
        }

        return livros;
    }

    // Listar todos os livros cadastrados
    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    // Listar livros por idioma
    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    // Lista de todos os autores únicos
    public List<String> listarTodosAutores() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(l -> l.getAutor().getNome())
                .distinct()
                .toList();
    }

    public List<String> listarAutoresVivosEmAno(int ano) {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(Livro::getAutor)
                .filter(a -> a != null) // evita NullPointerException
                .filter(a -> a.getAnoNascimento() <= ano && (a.getAnoFalecimento() == 0 || a.getAnoFalecimento() >= ano))
                .map(Autor::getNome)
                .distinct()
                .toList();
    }


    public long contarLivrosPorIdioma(String idioma) {
        return livroRepository.countByIdioma(idioma);
    }

    public void mostrarEstatisticas() {
        String[] idiomas = {"en", "pt"}; // Inglês e Português, por exemplo
        for (String idioma : idiomas) {
            long total = contarLivrosPorIdioma(idioma);
            System.out.println("Total de livros em " + idioma + ": " + total);
        }
    }



}
