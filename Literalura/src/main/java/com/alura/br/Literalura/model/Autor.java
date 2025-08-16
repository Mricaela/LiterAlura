package com.alura.br.Literalura.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Autor {
    private String nome;
    private int anoNascimento;
    private int anoFalecimento;

    // Getters e Setters
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
