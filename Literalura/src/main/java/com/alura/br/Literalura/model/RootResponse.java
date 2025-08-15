package com.alura.br.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootResponse {

    private List<LivroGutendex> results;

    public List<LivroGutendex> getResults() {
        return results;
    }

    public void setResults(List<LivroGutendex> results) {
        this.results = results;
    }
}
