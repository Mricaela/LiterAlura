package com.alura.br.Literalura.Repository;

import com.alura.br.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT DISTINCT a FROM Autor a")
    List<Autor> findAllAutores();

    @Query("SELECT a FROM Autor a WHERE a.anoFalecimento = 0 OR a.anoFalecimento > :ano")
    List<Autor> findAutoresVivosEmAno(int ano);
}
