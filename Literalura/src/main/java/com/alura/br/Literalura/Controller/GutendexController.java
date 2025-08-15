package com.alura.br.Literalura.Controller;

import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.Service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gutendex")
public class GutendexController {

    @Autowired
    private GutendexService gutendexService;

    @GetMapping
    public List<Livro> buscarLivros(@RequestParam String autor) throws Exception {
        return gutendexService.buscarLivrosPorAutor(autor);
    }
}
