# LiterAlura Challenge

![Alura](https://img.shields.io/badge/Challenge-Alura-blue) ![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-brightgreen)

Este projeto é um desafio de programação proposto pela **Alura**, focado na criação de uma aplicação **Java** com **Spring Boot** para interagir com a API **Gutendex**. A aplicação permite buscar livros por título, persistir os dados em um banco de dados PostgreSQL e realizar diversas consultas sobre livros e autores.

![Print do Challenge](assets/print-challenge-literalura.png)

---

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para criação de aplicações Java stand-alone.
- **Maven**: Gerenciamento de dependências e build automation.
- **PostgreSQL**: Banco de dados relacional.
- **Spring Data JPA**: Abstração para acesso a dados e persistência.
- **Jackson (ObjectMapper)**: Para manipulação de JSON da API Gutendex.

---

## ✨ Funcionalidades

A aplicação oferece as seguintes funcionalidades:

1. **Busca de Livros por Título na API**: Pesquisa livros na API Gutendex pelo título.
2. **Persistência de Dados no Banco de Dados**: Salva livros e autores encontrados no PostgreSQL.
3. **Listagem de Autores**: Exibe todos os autores cadastrados no banco.
4. **Listagem de Livros**: Exibe todos os livros cadastrados no banco.
5. **Busca de Autores Vivos em Determinado Ano**: Filtra autores que estavam vivos em um ano específico.
6. **Listagem de Livros por Idioma**: Filtra livros de acordo com o idioma selecionado.

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven instalado
- PostgreSQL instalado e configurado

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL (ex: `literalura_db`).
2. Atualize o arquivo `src/main/resources/application.properties` com suas credenciais do banco.

### Executando a Aplicação

```bash
# Clone o repositório
git clone git@github.com:Mricaela/LiterAlura.git

# Acesse a pasta do projeto
cd literalura

# Compile o projeto com Maven
mvn clean install

# Execute a aplicação Spring Boot
mvn spring-boot:run
Menu de Interação
Ao executar, você terá um menu textual:

css
Copiar
Editar
--- LITERALURA - PESQUISE SEU LIVRO ---
1 - Buscar Livro pelo Título
2 - Listar Livros Registrados
3 - Listar Autores Registrados
4 - Listar Autores Vivos em um Determinado Ano
5 - Listar Livros em Um Determinado Idioma
0 - Sair
O usuário pode escolher qualquer opção e continuar utilizando o programa sem precisar reiniciar.

💡 Contribuindo
Contribuições são bem-vindas! Você pode sugerir melhorias, reportar bugs ou enviar pull requests para aprimorar o projeto.
