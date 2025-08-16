package com.alura.br.Literalura.Service;

import com.alura.br.Literalura.model.Autor;
import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.Repository.LivroRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LivroServiceApi {

    private final LivroRepository livroRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public LivroServiceApi(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Livro buscarLivroPorTitulo(String titulo) throws Exception {
        // Monta a URL de busca
        String url = "https://gutendex.com/books/?search=" + titulo;

        // Faz a requisição GET
        String jsonResponse = restTemplate.getForObject(url, String.class);

        // Mapeia o JSON para o objeto intermediário
        LivroResponseWrapper wrapper = objectMapper.readValue(jsonResponse, LivroResponseWrapper.class);

        if (wrapper.getResults().isEmpty()) {
            return null; // Nenhum livro encontrado
        }

        // Pega o primeiro resultado
        LivroResponse result = wrapper.getResults().get(0);

        // Cria o objeto Livro do seu modelo
        Livro livro = new Livro();
        livro.setTitulo(result.getTitle());

        // Pega o primeiro autor
        if (!result.getAuthors().isEmpty()) {
            AutorResponse autorResp = result.getAuthors().get(0);
            Autor autor = new Autor();
            autor.setNome(autorResp.getName());
            autor.setAnoNascimento(autorResp.getBirth_year());
            autor.setAnoFalecimento(autorResp.getDeath_year());
            livro.setAutor(autor);
        }

        // Pega o primeiro idioma
        if (!result.getLanguages().isEmpty()) {
            livro.setIdioma(result.getLanguages().get(0));
        }

        livro.setDownloads(result.getDownload_count());

        // Salva no banco
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    // Classes auxiliares para mapear o JSON da API
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class LivroResponseWrapper {
        private List<LivroResponse> results;
        public List<LivroResponse> getResults() { return results; }
        public void setResults(List<LivroResponse> results) { this.results = results; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class LivroResponse {
        private String title;
        private List<AutorResponse> authors;
        private List<String> languages;
        private int download_count;

        // getters e setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public List<AutorResponse> getAuthors() { return authors; }
        public void setAuthors(List<AutorResponse> authors) { this.authors = authors; }
        public List<String> getLanguages() { return languages; }
        public void setLanguages(List<String> languages) { this.languages = languages; }
        public int getDownload_count() { return download_count; }
        public void setDownload_count(int download_count) { this.download_count = download_count; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AutorResponse {
        private String name;
        private Integer birth_year;
        private Integer death_year;

        // getters e setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Integer getBirth_year() { return birth_year; }
        public void setBirth_year(Integer birth_year) { this.birth_year = birth_year; }
        public Integer getDeath_year() { return death_year; }
        public void setDeath_year(Integer death_year) { this.death_year = death_year; }
    }
}
