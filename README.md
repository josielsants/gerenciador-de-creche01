# 📋 Gerenciador de Creche - Spring Boot

API REST completa de gerenciamento de alunos para creche, desenvolvida com Spring Boot 3.2 e Java 17.

## 🎯 Projeto Pronto para Uso

- ✅ Gerenciamento de alunos e turmas
- ✅ API RESTful para cadastro, consulta, atualização e exclusão
- ✅ Banco de dados H2 em memória
- ✅ Validação e tratamento de exceções
- ✅ Testes unitários com JUnit 5 e Mockito
- ✅ Documentação automática com Swagger
- ✅ Estrutura limpa e coerente

## 📁 Estrutura do Projeto

```
gerenciador-de-creche01/
├── src/
│   ├── main/java/com/josielsants/gerenciadortarefas/
│   │   ├── GerenciadortarefasApplication.java       (Classe main)
│   │   ├── model/
│   │   │   └── Aluno.java                            (Entidade JPA)
│   │   ├── repository/
│   │   │   └── AlunoRepository.java                 (Spring Data JPA)
│   │   ├── service/
│   │   │   └── AlunoService.java                    (Lógica de negócio)
│   │   ├── controller/
│   │   │   └── AlunoController.java                 (Endpoints REST)
│   │   └── exception/
│   │       ├── AlunoNaoEncontradaException.java
│   │       └── GlobalExceptionHandler.java
│   ├── resources/
│   │   └── application.properties
│   └── test/java/.../AlunoServiceTest.java          (Testes unitários)
├── legacy/                                          (Código anterior)
├── pom.xml                                          (Dependências Maven)
├── README-SPRING.md                                 (Documentação técnica)
└── GUIA-FINAL.txt                                   (Guia de uso)
```

## 🚀 Como Começar

### Pré-requisitos
- Java 17+
- Maven 3.6+

### Instalação

```bash
cd gerenciador-de-creche01
mvn clean install
mvn spring-boot:run
```

### Acessar a API

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Base**: http://localhost:8080/api/alunos
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:tarefasdb`
  - Usuário: `sa`
  - Senha: deixe em branco

## 📡 Endpoints Principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/alunos` | Lista todos os alunos |
| GET | `/api/alunos/{id}` | Busca aluno por ID |
| POST | `/api/alunos` | Cadastra novo aluno |
| PUT | `/api/alunos/{id}` | Atualiza dados do aluno |
| DELETE | `/api/alunos/{id}` | Remove aluno |

## 🧪 Executar Testes

```bash
mvn test
```

## 📝 Exemplos de Requisição

### Criar Aluno
```bash
curl -X POST http://localhost:8080/api/alunos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana",
    "idade": 4,
    "turma": "Creche A",
    "responsavel": "Paulo",
    "telefoneResponsavel": "(11) 99999-9999",
    "necessidadeEspecial": "Nenhuma"
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

## 🏗️ Tecnologias Utilizadas

- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Bean Validation**
- **Swagger/OpenAPI**
- **JUnit 5 & Mockito**

## 📚 Documentação Adicional

- [README-SPRING.md](README-SPRING.md)
- [GUIA-FINAL.txt](GUIA-FINAL.txt)
- [legacy/](legacy/) - Código antigo do projeto

## 📝 Licença

Este projeto é fornecido como exemplo educacional.

