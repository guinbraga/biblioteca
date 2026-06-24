# Sistema de Biblioteca

Projeto final da disciplina de **Engenharia de Software** — Universidade Estadual de Londrina (UEL), Departamento de Computação.

O sistema implementa um conjunto de funcionalidades para gerenciamento de uma biblioteca, contemplando o cadastro de livros e alunos, e os casos de uso de **empréstimo** e **devolução** de livros, seguindo boas práticas de desenvolvimento ágil (Scrum/Kanban) e DevOps (CI/CD com GitHub Actions).

## Objetivo do Projeto

Desenvolver um Sistema de Biblioteca aplicando:

- Arquitetura em camadas com o padrão **DAO (Data Access Object)** para persistência de dados;
- Fluxo de trabalho colaborativo com **Git/GitHub** (branches, Pull Requests, Code Review);
- Gestão ágil com **Kanban** via GitHub Projects e **GitHub Issues**;
- Integração contínua (**CI**) com **GitHub Actions**;
- Testes unitários automatizados para as principais regras de negócio.

## Equipe

| Nome | GitHub |
| --- | --- |
| Alexandre da Silva Marchioli | [@marchiolialexandre](https://github.com/marchiolialexandre) |
| Guilherme Costanzi Do | [@guihcostanzi](https://github.com/guihcostanzi) |
| Guilherme Nascimento Braga | [@guinbraga](https://github.com/guinbraga) |
| Rafael Figueiredo Cobo | [@rfcob](https://github.com/rfcob) |

## Funcionalidades

- [x] Cadastro de Livros (criar, listar, editar, remover)
- [x] Cadastro de Alunos (criar, listar, editar, remover)
- [ ] Empréstimo de Livro
- [ ] Devolução de Livro

> O código-base para o caso de uso de Empréstimo segue a estrutura disponibilizada em [github.com/menolli/EngenhariaDeSoftware (Codigo2)](https://github.com/menolli/EngenhariaDeSoftware/tree/main/Codigo2), de acordo com os exercícios 2 a 6 (p. 197) do livro-texto da disciplina.

## Tecnologias

| Camada | Tecnologia |
| --- | --- |
| Linguagem | Java |
| Build / Gerenciador de dependências | [FERRAMENTA_BUILD] (ex: Maven ou Gradle) |
| Interface Gráfica (GUI) | [FRAMEWORK_GUI] (ex: Java Swing ou JavaFX) |
| Banco de Dados (SGBD) | [NOME_BANCO_DE_DADOS] (ex: SQLite, MySQL, PostgreSQL) |
| Persistência | Padrão DAO (JDBC puro / [BIBLIOTECA_PERSISTENCIA, se houver]) |
| Testes Unitários | [FRAMEWORK_TESTES] (ex: JUnit 5) |
| CI/CD | GitHub Actions |
| Versionamento | Git + GitHub (Issues, Projects/Kanban, Pull Requests) |

> As tecnologias entre conchetes ainda serão definidas pela equipe e este README será atualizado assim que decididas (ver Issue 02).

## Arquitetura

O sistema segue uma arquitetura em **camadas**, separando:

- **Apresentação (GUI):** telas de cadastro, empréstimo e devolução;
- **Controle/Serviço:** regras de negócio das funcionalidades;
- **Persistência (DAO):** interfaces e implementações de acesso a dados para as entidades `Livro`, `Aluno` e `Empréstimo`.

A documentação completa da arquitetura (visão conceitual, elementos, dependências e padrões adotados) está disponível em [`/docs`](./docs).

## Estrutura do Projeto

```
.
├── src/                  # Código-fonte Java
├── doc/                 # Documentação (arquitetura, diagramas)
├── .github/
│   └── workflows/        # Pipelines de CI (GitHub Actions)
├── test/                 # Testes unitários
└── README.md
```

> Estrutura sujeita a ajustes conforme a ferramenta de build escolhida.

## Como Configurar o Ambiente (Setup)

> Seção a ser detalhada após a definição final das tecnologias.

1. Clonar o repositório:
   ```bash
   git clone https://github.com/guinbraga/biblioteca.git
   cd biblioteca
   ```
2. Instalar [FERRAMENTA_BUILD] e o JDK (versão [VERSAO_JDK]).
3. Configurar o banco de dados [NOME_BANCO_DE_DADOS]:
   ```
   [INSTRUCOES_DE_CONFIGURACAO_DO_BANCO]
   ```
4. Instalar as dependências do projeto:
   ```bash
   [COMANDO_INSTALAR_DEPENDENCIAS]
   ```

## Como Executar a Aplicação

> A ser inserido após a implementação da GUI (Issue 10).

```bash
[COMANDO_RODAR_APLICACAO]
```

## Como Rodar os Testes

> A ser inserido após a configuração do framework de testes (Issue 17).

```bash
[COMANDO_RODAR_TESTES]
```

Os testes são executados automaticamente no pipeline de CI a cada Pull Request.

## Fluxo de Trabalho (Git/DevOps)

- **Branches:** uma branch por funcionalidade, seguindo o padrão `feature/nome-da-funcionalidade` (ex: `feature/cadastro-livro`, `feature/emprestar-livro`, `feature/devolver-livro`).
- **Commits:** mensagens descritivas e frequentes.
- **Pull Requests:** toda alteração é integrada via PR, revisada por ao menos um membro diferente do autor antes do merge.
- **Issues:** cada tarefa/funcionalidade corresponde a uma Issue no GitHub, com labels (`feature`, `bug`, `test`, `review`, `documentation`, `devops`) e vinculada ao quadro Kanban.
- **Kanban:** acompanhamento das tarefas pelo GitHub Projects, com colunas `To Do`, `In Progress`, `Review` e `Done`.
- **CI:** workflow do GitHub Actions disparado automaticamente a cada Pull Request, executando build e (futuramente) os testes automatizados.

## Documentação

- [Backlog de Issues](./doc/Backlog_Issues.docx)
- [Documentação da Arquitetura](./doc)
- Diagramas de Classe e Sequência *(a ser adicionados em `/doc`)*