package com.alura.br.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroGutendex {

    private String title;
    private List<AuthorGutendex> authors;

    // Getters e setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorGutendex> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorGutendex> authors) {
        this.authors = authors;
    }
}
