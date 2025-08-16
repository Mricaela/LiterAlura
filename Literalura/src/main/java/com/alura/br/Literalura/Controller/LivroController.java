package com.alura.br.Literalura.Controller;

import com.alura.br.Literalura.Service.LivroServiceApi;
import com.alura.br.Literalura.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroServiceApi livroService;

    // Buscar livro por título e salvar no banco
    @GetMapping("/buscar")
    public Livro buscarLivro(@RequestParam String titulo) throws Exception {
        return livroService.buscarLivroPorTitulo(titulo);
    }

    // Listar todos os livros cadastrados
    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodosLivros();
    }

    // Listar livros por idioma
    @GetMapping("/idioma")
    public List<Livro> listarPorIdioma(@RequestParam String idioma) {
        return livroService.listarLivrosPorIdioma(idioma);
    }
}
