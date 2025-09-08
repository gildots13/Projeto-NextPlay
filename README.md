# NextPlay - Sistema de Recomendações de Mídia 🎬🎵

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-blue)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

## 📖 Sobre o Projeto

O NextPlay é uma aplicação web full-stack projetada para fornecer recomendações personalizadas de filmes, séries e músicas. Este projeto está sendo desenvolvido como parte dos meus estudos em [Seu Curso, ex: Ciência da Computação], com o objetivo de aplicar e aprofundar meus conhecimentos em desenvolvimento back-end com Java e Spring Boot, além de criar uma interface de usuário funcional com tecnologias front-end.

A ideia é que um usuário possa se cadastrar, fazer login, avaliar os itens de mídia que já consumiu e, com base nessas avaliações, receber sugestões inteligentes e personalizadas.

## ✨ Funcionalidades e Andamento

-   [✅] **Cadastro de Usuário:** API e interface para criação de novas contas.
-   [✅] **Autenticação de Usuário:** Sistema de Login para acesso seguro à plataforma.
-   [⏳] **Catálogo de Mídia:** Integração com a API do TMDb (The Movie Database) para popular o banco de dados com informações reais e atualizadas.
-   [⏳] **Sistema de Avaliação:** Funcionalidade para o usuário avaliar os itens do catálogo (ex: nota de 1 a 5 estrelas).
-   [⏳] **Motor de Recomendação:** Lógica no back-end para gerar e exibir recomendações personalizadas com base nas avaliações do usuário.

## 🛠️ Tecnologias Utilizadas

O projeto é dividido em duas partes principais:

**Back-end:**
* **Java 17+**
* **Spring Boot:** Para a criação da API REST, segurança e configuração geral.
* **Spring Data JPA / Hibernate:** Para a persistência de dados e comunicação com o banco.
* **MySQL:** Como banco de dados relacional para armazenar os dados dos usuários, mídias e avaliações.
* **Maven:** Para gerenciamento de dependências.

**Front-end:**
* **HTML5**
* **CSS3**
* **JavaScript (Vanilla JS):** Para manipulação do DOM e comunicação com a API (via Fetch API).

## 🚀 Como Rodar o Projeto

Para executar este projeto localmente, siga os passos abaixo:

**Pré-requisitos:**
* Java JDK 17 ou superior
* Maven
* MySQL Server

**Back-end:**
1.  Clone o repositório.
2.  Configure suas credenciais do MySQL no arquivo `src/main/resources/application.properties`.
3.  Execute a classe principal `BackendApplication.java` a partir da sua IDE. O servidor estará rodando em `http://localhost:8080`.

**Front-end:**
1.  Navegue até a pasta `frontend`.
2.  Abra o arquivo `index.html` em seu navegador. (Recomendado usar a extensão **Live Server** do VS Code).

## 🗺️ Roadmap Futuro

O plano de desenvolvimento segue as seguintes fases:
1.  **Autenticação:** Finalizar o sistema de Login.
2.  **Catálogo:** Implementar a busca e salvamento de dados da API do TMDb.
3.  **Interação:** Construir a interface para visualização do catálogo e o sistema de avaliação.
4.  **Recomendação:** Desenvolver a lógica principal de recomendações.

## 👨‍💻 Autor

Desenvolvido por **Gildo Júnior**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)]([https://linkedin.com/in/gildojuniorab])