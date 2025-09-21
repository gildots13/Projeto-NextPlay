# NextPlay - Sistema de Recomendações de Mídia 🎬🎵

![Status do Projeto](https://img.shields.io/badge/status-concluído-brightgreen)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📖 Sobre o Projeto

O NextPlay é uma aplicação web full-stack projetada para fornecer recomendações personalizadas de filmes, séries e músicas. Este meu primeiro projeto foi desenvolvido por mim como parte dos meus estudos em Ciência da Computação, com o objetivo de aplicar e aprofundar meus conhecimentos em desenvolvimento full-stack.

A ideia é que um usuário possa se cadastrar, fazer login, avaliar os itens de mídia que já consumiu e, com base nessas avaliações, receber sugestões inteligentes e personalizadas.

## ✨ Funcionalidades e Andamento

-   [✅] **Cadastro de Usuário:** API e interface para criação de novas contas.
-   [✅] **Autenticação de Usuário:** Sistema de Login para acesso seguro à plataforma.
-   [✅] **Catálogo de Mídia (Filmes, Séries e Músicas):** Integração com a API do TMDb (The Movie Database) para popular o banco de dados com informações reais e atualizadas.
-   [✅] **Sistema de Avaliação:** Funcionalidade para o usuário avaliar os itens do catálogo (ex: nota de 1 a 5 estrelas).
-   [✅] **Motor de Recomendação:** Lógica no back-end para gerar e exibir recomendações personalizadas com base nas avaliações do usuário.
-   [✅] **Publicação do NextPlay:** Publicar o site para disponibilidade 100% online, sem a necessidade do usuário baixar os arquivos de código fonte e clonar o repositório.

## 🛠️ Tecnologias Utilizadas

O projeto é dividido em duas partes principais:

**Back-end:**
* **Java 24**
* **Spring Boot:** Para a criação da API REST, segurança e configuração geral.
* **Spring Data JPA / Hibernate:** Para a persistência de dados e comunicação com o banco.
* **MySQL:** Como banco de dados relacional para armazenar os dados dos usuários, mídias e avaliações.
* **Maven:** Para gerenciamento de dependências.

**Front-end:**
* **HTML5**
* **CSS3**
* **JavaScript (Vanilla JS):** Para manipulação do DOM e comunicação com a API (via Fetch API).

## 🚀 Como Rodar o Projeto

Para executar este projeto há duas maneiras:

**Online:**
Acesse: nextplaybr.netlify.app
* Ao entrar no site e cadastrar ou logar um usuário, haverá um tempo de espera de 50 segundos, para que o servidor fique completamente online.

**Localmente:**

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


## 👨‍💻 Autor

Desenvolvido por **Gildo Júnior**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/gildojuniorab)
