package com.alura.br.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroGutendex {

    private String title;
    private List<AuthorGutendex> authors;

    // Novo campo: lista de idiomas
    private List<String> languages;

    // Novo campo: número de downloads
    @JsonAlias("download_count")
    private int downloadCount;

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

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}
