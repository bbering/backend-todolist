# Backend To-Do List

Este projeto fullstack é uma API RESTful para gerenciamento de listas de tarefas (To-Do-List), desenvolvida utilizando Java Spring Boot. A API permite criar, editar, excluir e listar tarefas, além de criar, editar, excluir e listar usuários.

O frontend do projeto esta disponível em: https://github.com/bbering/frontend-todolist

## Tecnologias Utilizadas

- **Java 17**: A versão do Java usada para o desenvolvimento do projeto.
- **Spring Boot 3.3.6**: Framework principal para criação da API RESTful.
  - **Spring Boot Starter Web**: Para desenvolvimento de aplicações web e APIs REST.
  - **Spring Boot Starter Data JPA**: Para integração com banco de dados utilizando JPA (Java Persistence API).
  - **Spring Boot Starter Validation**: Para validação de dados de entrada na API.
  - **Spring Boot DevTools**: Ferramentas de desenvolvimento que permitem recarregamento automático da aplicação durante o desenvolvimento.
- **Lombok**: Para redução de código boilerplate, como getters, setters e construtores.
- **MySQL**: Banco de dados para produção (conector MySQL).

## Pré-requisitos

- **Java 17** ou superior.
- **Maven** para gerenciar dependências e build do projeto.
- **Banco de dados**: MySQL 8.0 ou superior.

## Manipulação de Usuário
| Endpoint  | Retorno |
| ------------- | ------------- |
| GET /user/list | Retorna uma lista de usuários a quem as tarefas serão delegadas |
| GET /user/list/{id} | Retorna um usuário com base no seu ID |
| POST /user/save  | Cria um novo usuário |
| PUT /user/update/{id} | Altera um usuário com base no seu ID |
| DELETE /user/delete/{id} | Deleta um usuário com base no seu ID |

## Manipulação de Tasks

| Endpoint  | Retorno |
| ------------- | ------------- |
| GET /tasks/list | Retorna uma lista de tasks e os usuários a quem elas foram delegadas |
| GET /tasks/list/{id} | Retorna uma task com base no seu ID |
| POST /tasks/save | Atribui uma nova task a um usuário |
| PUT /tasks/update/{id} | Atualiza uma task com base no seu ID |
| DELETE /tasks/delete/{id} | Deleta uma task com base no seu ID |

## Como Executar o Projeto

### 1. Clonar o Repositório

Clone o repositório para sua máquina local:

```bash
git clone https://github.com/seuusuario/backend-todolist.git
```

### 2. Navegar até o diretório do projeto:

```bash
cd backend-todolist
```

### 3. Compilar, executar com o Maven e buildar o projeto:

```bash
mvn spring-boot:run
```

```bash
mvn clean install
```

E pronto, a aplicação fica disponível na porta http://localhost:8080
