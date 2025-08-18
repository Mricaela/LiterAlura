package com.alura.br.Literalura.Repository;

import com.alura.br.Literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);

    long countByIdioma(String idioma);
}
