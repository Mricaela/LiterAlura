package com.alura.br.Literalura;

import com.alura.br.Literalura.model.Livro;
import com.alura.br.Literalura.Repository.LivroRepository;
import com.alura.br.Literalura.Service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final Scanner scanner = new Scanner(System.in);

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		exibirMenu();
	}

	private void exibirMenu() {
		int opcao;
		do {
			System.out.println("\n=== MENU ===");
			System.out.println("1 - Inserir livro");
			System.out.println("2 - Listar todos os livros");
			System.out.println("3 - Buscar livro por autor via Gutendex");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar buffer

			switch (opcao) {
				case 1:
					inserirLivro();
					break;
				case 2:
					listarLivros();
					break;
				case 3:
					buscarLivroGutendex();
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);
	}

	private void inserirLivro() {
		System.out.print("Título: ");
		String titulo = scanner.nextLine();
		System.out.print("Autor: ");
		String autor = scanner.nextLine();
		System.out.print("Ano de nascimento: ");
		int anoNascimento = scanner.nextInt();
		System.out.print("Ano de falecimento: ");
		int anoFalecimento = scanner.nextInt();
		scanner.nextLine(); // Limpar buffer

		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setAnoNascimento(anoNascimento);
		livro.setAnoFalecimento(anoFalecimento);

		livroRepository.save(livro);
		System.out.println("Livro inserido: " + livro);
	}

	private void listarLivros() {
		List<Livro> livros = livroRepository.findAll();
		if (livros.isEmpty()) {
			System.out.println("Nenhum livro cadastrado.");
		} else {
			System.out.println("=== Lista de Livros ===");
			livros.forEach(System.out::println);
		}
	}

	private void buscarLivroGutendex() {
		System.out.print("Digite o nome do autor: ");
		String autor = scanner.nextLine();

		try {
			List<Livro> livros = gutendexService.buscarLivrosPorAutor(autor);
			if (livros.isEmpty()) {
				System.out.println("Nenhum livro encontrado para " + autor);
			} else {
				System.out.println("=== Livros encontrados na API Gutendex ===");
				livros.forEach(System.out::println);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar livros: " + e.getMessage());
		}
	}
}
