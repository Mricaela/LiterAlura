package com.alura.br.Literalura.Controller;

import com.alura.br.Literalura.Service.GutendexService;
import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private GutendexService gutendexService;

    // Listar todos os livros
    @GetMapping
    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    // Adicionar um livro manualmente
    @PostMapping
    public Livro adicionar(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    // Atualizar livro
    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livro.setTitulo(livroAtualizado.getTitulo());
                    livro.setAutor(livroAtualizado.getAutor());
                    livro.setAnoNascimento(livroAtualizado.getAnoNascimento());
                    livro.setAnoFalecimento(livroAtualizado.getAnoFalecimento());
                    return livroRepository.save(livro);
                })
                .orElse(null);
    }

    // Deletar livro
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        livroRepository.deleteById(id);
    }

    // Buscar livros pelo nome do autor usando GutendexService
    @GetMapping("/buscar-autor")
    public List<Livro> buscarPorAutor(@RequestParam String nome) throws Exception {
        return gutendexService.buscarLivrosPorAutor(nome);
    }
}
