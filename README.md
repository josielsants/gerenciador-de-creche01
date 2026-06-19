# 📋 Gerenciador de Creche - Spring Boot

API REST completa de gerenciamento de creche com 5 entidades principais, desenvolvida com Spring Boot 3.2 e Java 21.

## 🎯 Projeto Pronto para Uso

- ✅ Gerenciamento de Alunos, Professores, Coordenadores, Funcionários e Responsáveis
- ✅ Interface Web dinâmica em HTML/JavaScript
- ✅ API RESTful completa (GET, POST, PUT, DELETE)
- ✅ Banco de dados H2 em memória
- ✅ Validação e tratamento de exceções centralizado
- ✅ Testes unitários com JUnit 5 e Mockito
- ✅ Estrutura limpa e escalável

## 📁 Estrutura do Projeto

```
gerenciador-de-creche01/
├── src/
│   ├── main/
│   │   ├── java/com/josielsants/gerenciadorcreche/
│   │   │   ├── GerenciadorCrecheApplication.java    (Classe main)
│   │   │   ├── model/                               (5 entities JPA)
│   │   │   │   ├── Aluno.java
│   │   │   │   ├── Professor.java
│   │   │   │   ├── Coordenador.java
│   │   │   │   ├── Funcionario.java
│   │   │   │   └── Responsavel.java
│   │   │   ├── repository/                          (Spring Data JPA)
│   │   │   │   ├── AlunoRepository.java
│   │   │   │   ├── ProfessorRepository.java
│   │   │   │   ├── CoordenadorRepository.java
│   │   │   │   ├── FuncionarioRepository.java
│   │   │   │   └── ResponsavelRepository.java
│   │   │   ├── service/                             (Lógica de negócio)
│   │   │   │   ├── AlunoService.java
│   │   │   │   ├── ProfessorService.java
│   │   │   │   ├── CoordenadorService.java
│   │   │   │   ├── FuncionarioService.java
│   │   │   │   └── ResponsavelService.java
│   │   │   ├── controller/                          (Endpoints REST)
│   │   │   │   ├── AlunoController.java
│   │   │   │   ├── ProfessorController.java
│   │   │   │   ├── CoordenadorController.java
│   │   │   │   ├── FuncionarioController.java
│   │   │   │   └── ResponsavelController.java
│   │   │   └── exception/
│   │   │       ├── RecursoNaoEncontradoException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── index.html                       (Interface Web)
│   └── test/
│       └── java/.../AlunoServiceTest.java           (Testes unitários)
├── legacy/                                          (Código anterior)
├── pom.xml                                          (Dependências Maven)
└── README-SPRING.md                                 (Documentação técnica)
```

## 🚀 Como Começar

### Pré-requisitos
- Java 21+
- Maven 3.6+

### Instalação e Execução

```bash
cd gerenciador-de-creche01
mvn clean install
mvn spring-boot:run
```

A aplicação iniciará em: **http://localhost:8080/**

## 🌐 Acessando a Interface Web

### Página Principal (Recomendado)
- **URL**: http://localhost:8080/
- **Recursos**:
  - ✅ Abas para trocar entre Alunos, Professores, Coordenadores, Funcionários e Responsáveis
  - ✅ Formulário dinâmico com campos específicos de cada entidade
  - ✅ Tabela com listagem de registros
  - ✅ Botões de Editar e Excluir integrados
  - ✅ Criar novo registro por formulário

### Endpoints da API (para requisições diretas)

#### Alunos
- **GET** `http://localhost:8080/alunos` - Lista todos os alunos
- **GET** `http://localhost:8080/alunos/{id}` - Busca aluno por ID
- **POST** `http://localhost:8080/alunos` - Cadastra novo aluno
- **PUT** `http://localhost:8080/alunos/{id}` - Atualiza aluno
- **DELETE** `http://localhost:8080/alunos/{id}` - Remove aluno

#### Professores
- **GET** `http://localhost:8080/professores` - Lista todos os professores
- **GET** `http://localhost:8080/professores/{id}` - Busca professor por ID
- **POST** `http://localhost:8080/professores` - Cadastra novo professor
- **PUT** `http://localhost:8080/professores/{id}` - Atualiza professor
- **DELETE** `http://localhost:8080/professores/{id}` - Remove professor

#### Coordenadores
- **GET** `http://localhost:8080/coordenadores` - Lista todos os coordenadores
- **POST** `http://localhost:8080/coordenadores` - Cadastra novo coordenador
- **PUT** `http://localhost:8080/coordenadores/{id}` - Atualiza coordenador
- **DELETE** `http://localhost:8080/coordenadores/{id}` - Remove coordenador

#### Funcionários
- **GET** `http://localhost:8080/funcionarios` - Lista todos os funcionários
- **POST** `http://localhost:8080/funcionarios` - Cadastra novo funcionário
- **PUT** `http://localhost:8080/funcionarios/{id}` - Atualiza funcionário
- **DELETE** `http://localhost:8080/funcionarios/{id}` - Remove funcionário

#### Responsáveis
- **GET** `http://localhost:8080/responsaveis` - Lista todos os responsáveis
- **POST** `http://localhost:8080/responsaveis` - Cadastra novo responsável
- **PUT** `http://localhost:8080/responsaveis/{id}` - Atualiza responsável
- **DELETE** `http://localhost:8080/responsaveis/{id}` - Remove responsável

### Ferramentas Adicionais
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:tarefasdb`
  - Usuário: `sa`
  - Senha: deixe em branco

## 🧪 Executar Testes

```bash
mvn clean test
```

Todos os testes unitários do serviço `AlunoService` estão configurados e passando.

## 📊 Exemplos de Requisição (cURL)

### Criar um Novo Aluno
```bash
curl -X POST http://localhost:8080/alunos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana Silva",
    "idade": 4,
    "turma": "Creche A",
    "responsavelNome": "Paulo Silva"
  }'
```

### Listar Todos os Alunos
```bash
curl http://localhost:8080/alunos
```

### Buscar Aluno por ID
```bash
curl http://localhost:8080/alunos/1
```

### Atualizar Aluno
```bash
curl -X PUT http://localhost:8080/alunos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana Silva Updated",
    "idade": 5,
    "turma": "Creche B",
    "responsavelNome": "Maria Silva"
  }'
```

### Deletar Aluno
```bash
curl -X DELETE http://localhost:8080/alunos/1
```

## 💡 Dicas de Uso

1. **Para gerenciar dados rapidamente**: Use a interface web em http://localhost:8080/
2. **Para testes automatizados**: Use as requisições cURL acima ou Postman
3. **Para consultar o banco de dados**: Acesse o H2 Console em http://localhost:8080/h2-console
4. **Para verificar erros**: Abra o console do navegador (F12 → Console) na página web

## 📚 Referências

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Maven Official Guide](https://maven.apache.org/guides/)

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

