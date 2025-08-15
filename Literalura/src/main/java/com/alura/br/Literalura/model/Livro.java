package com.alura.br.Literalura.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // necessário para JPA

    private String titulo;
    private String autor;
    private int anoNascimento;
    private int anoFalecimento;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAnoNascimento() { return anoNascimento; }
    public void setAnoNascimento(int anoNascimento) { this.anoNascimento = anoNascimento; }

    public int getAnoFalecimento() { return anoFalecimento; }
    public void setAnoFalecimento(int anoFalecimento) { this.anoFalecimento = anoFalecimento; }

    // toString() vai aqui, no final da classe
    @Override
    public String toString() {
        return "Livro {" +
                "ID=" + id +
                ", Título='" + titulo + '\'' +
                ", Autor='" + autor + '\'' +
                ", Ano de Nascimento=" + anoNascimento +
                ", Ano de Falecimento=" + anoFalecimento +
                '}';
    }
}
