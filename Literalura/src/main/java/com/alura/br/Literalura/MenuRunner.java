package com.alura.br.Literalura;

import com.alura.br.Literalura.Service.GutendexService;
import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.model.Autor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MenuRunner implements CommandLineRunner {

    private final GutendexService gutendexService;

    public MenuRunner(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Inserir livro por título");
            System.out.println("2 - Listar todos os livros");
            System.out.println("3 - Listar livros por idioma");
            System.out.println("4 - Listar todos os autores");
            System.out.println("5 - Listar autores vivos em determinado ano");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    Livro livro = gutendexService.buscarLivroPorTitulo(titulo);
                    if (livro != null) {
                        System.out.println("Livro adicionado com sucesso:");
                        System.out.println(livro);
                    } else {
                        System.out.println("Nenhum livro encontrado para esse título.");
                    }
                }
                case 2 -> {
                    List<Livro> todos = gutendexService.listarTodosLivros();
                    System.out.println("\n=== TODOS OS LIVROS ===");
                    todos.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Digite o idioma: ");
                    String idioma = scanner.nextLine();
                    List<Livro> livrosPorIdioma = gutendexService.listarLivrosPorIdioma(idioma);
                    System.out.println("\n=== LIVROS NO IDIOMA " + idioma + " ===");
                    livrosPorIdioma.forEach(System.out::println);
                }
                case 4 -> {
                    List<String> autores = gutendexService.listarTodosAutores();
                    System.out.println("\n=== TODOS OS AUTORES ===");
                    autores.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("Digite o ano: ");
                    int ano = Integer.parseInt(scanner.nextLine());
                    List<String> autoresVivos = gutendexService.listarAutoresVivosEmAno(ano);
                    System.out.println("\n=== AUTORES VIVOS EM " + ano + " ===");
                    autoresVivos.forEach(System.out::println);
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
