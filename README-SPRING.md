# 🚀 Gerenciador de Creche - API REST

Projeto de **API REST** desenvolvido com **Spring Boot 3.2** e **Java 17**, para gerenciar alunos e turmas de creche.

## 📋 Sobre o Projeto

Sistema de gerenciamento de alunos com funcionalidades CRUD, validação de dados, tratamento de exceções e documentação automática com Swagger.

## ✨ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Swagger/OpenAPI**
- **JUnit 5**
- **Mockito**
- **Maven**

## 🏗️ Arquitetura e Estrutura

```
src/main/java/com/josielsants/gerenciadortarefas/
├── model/              # Entidades JPA
│   └── Aluno.java
├── repository/         # Camada de acesso a dados
│   └── AlunoRepository.java
├── service/            # Lógica de negócio
│   └── AlunoService.java
├── controller/         # Endpoints REST
│   └── AlunoController.java
├── exception/          # Tratamento de erros
│   ├── AlunoNaoEncontradaException.java
│   └── GlobalExceptionHandler.java
└── GerenciadortarefasApplication.java  # Classe main
```

## 🎯 Conceitos Aplicados

✅ **SOLID Principles**
✅ **Padrão MVC**
✅ **Injeção de Dependência**
✅ **REST API Design**
✅ **Validação de Dados**
✅ **Tratamento de Exceções**
✅ **Testes Unitários**
✅ **Documentação com Swagger**

## ⚡ Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+

### Passo 1: Clone o repositório
```bash
git clone https://github.com/josielsants/gerenciador-de-creche01.git
git checkout main
cd gerenciador-de-creche01
```

### Passo 2: Compile o projeto
```bash
mvn clean install
```

### Passo 3: Execute a aplicação
```bash
mvn spring-boot:run
```

### Passo 4: Acesse a aplicação
- **API**: http://localhost:8080/api/alunos
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Banco H2**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:tarefasdb`
  - Usuário: `sa`
  - Senha: (vazia)

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/alunos` | Lista todos os alunos |
| `GET` | `/api/alunos/{id}` | Busca aluno por ID |
| `POST` | `/api/alunos` | Cria novo aluno |
| `PUT` | `/api/alunos/{id}` | Atualiza aluno |
| `DELETE` | `/api/alunos/{id}` | Remove aluno |

## 📝 Exemplos de Uso

### Criar Aluno
```bash
curl -X POST http://localhost:8080/api/alunos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana",
    "idade": 4,
    "turma": "Creche A",
    "responsavel": "Paulo",
    "telefoneResponsavel": "(11) 99999-9999"
  }'
```

### Listar Alunos
```bash
curl http://localhost:8080/api/alunos
```

### Atualizar Aluno
```bash
curl -X PUT http://localhost:8080/api/alunos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana Maria",
    "telefoneResponsavel": "(11) 98888-8888"
  }'
```

## 🧪 Executar Testes

```bash
mvn test
```

## 🔍 Tratamento de Erros

A API retorna respostas estruturadas com status apropriados.

## 👨‍💻 Autor

**Josiel Santos**

---

⭐ Este projeto é ideal para quem quer um gerenciador de creche leve e completo em Spring Boot.
