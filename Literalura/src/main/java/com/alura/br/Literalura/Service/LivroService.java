package com.alura.br.Literalura.Service;

import com.alura.br.Literalura.model.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class LivroService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Livro> buscarLivrosDaApi() throws Exception {
        // Substitua pela URL da sua API real
        String url = "https://api.exemplo.com/livros";

        // Fazendo a requisição GET e recebendo JSON como String
        String json = restTemplate.getForObject(url, String.class);

        // Convertendo JSON para lista de objetos Livro
        List<Livro> livros = Arrays.asList(objectMapper.readValue(json, Livro[].class));

        return livros;
    }

}
