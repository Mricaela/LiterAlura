package com.alura.br.Literalura.model;

import jakarta.persistence.*;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int anoNascimento;
    private int anoFalecimento;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getAnoNascimento() { return anoNascimento; }
    public void setAnoNascimento(int anoNascimento) { this.anoNascimento = anoNascimento; }

    public int getAnoFalecimento() { return anoFalecimento; }
    public void setAnoFalecimento(int anoFalecimento) { this.anoFalecimento = anoFalecimento; }

    @Override
    public String toString() {
        return nome + " (" + anoNascimento + " - " + anoFalecimento + ")";
    }
}
