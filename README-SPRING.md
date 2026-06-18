# 🚀 Gerenciador de Tarefas - API REST

Um projeto de **API REST completo** desenvolvido com **Spring Boot 3.2** e **Java 17**, demonstrando boas práticas de desenvolvimento para desenvolvedor júnior Java.

## 📋 Sobre o Projeto

Sistema de gerenciamento de tarefas com funcionalidades CRUD (Create, Read, Update, Delete), tratamento de exceções global, validação de dados e documentação automática com Swagger.

## ✨ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.5** - Framework web
- **Spring Data JPA** - ORM para persistência
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de boilerplate
- **Swagger/OpenAPI** - Documentação automática da API
- **JUnit 5** - Testes unitários
- **Mockito** - Mock para testes
- **Maven** - Gerenciador de dependências

## 🏗️ Arquitetura e Estrutura

```
src/main/java/com/josielsants/gerenciadortarefas/
├── model/              # Entidades JPA
│   └── Tarefa.java
├── repository/         # Camada de acesso a dados
│   └── TarefaRepository.java
├── service/            # Lógica de negócio
│   └── TarefaService.java
├── controller/         # Endpoints REST
│   └── TarefaController.java
├── exception/          # Tratamento de erros
│   ├── TarefaNaoEncontradaException.java
│   └── GlobalExceptionHandler.java
└── GerenciadortarefasApplication.java  # Classe main
```

## 🎯 Conceitos Aplicados

✅ **SOLID Principles** - Princípios de design orientado a objetos  
✅ **Padrão MVC** - Model, View, Controller separação de responsabilidades  
✅ **Injeção de Dependência** - Fornecida pelo Spring  
✅ **REST API Design** - Endpoints profissionais com HTTP methods corretos  
✅ **Validação de Dados** - Bean Validation com @NotBlank  
✅ **Tratamento de Exceções** - @RestControllerAdvice e @ExceptionHandler  
✅ **Testes Unitários** - JUnit 5 com Mockito  
✅ **Documentação** - Swagger automático  

## ⚡ Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+

### Passo 1: Clone o repositório
```bash
git clone https://github.com/josielsants/gerenciador-de-creche01.git
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

Ou execute diretamente no IntelliJ IDEA clicando no botão verde ▶️.

### Passo 4: Acesse a aplicação
- **API**: http://localhost:8080/api/tarefas
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Banco H2**: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:tarefasdb`)

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/tarefas` | Lista todas as tarefas |
| `GET` | `/api/tarefas/{id}` | Busca tarefa por ID |
| `POST` | `/api/tarefas` | Cria nova tarefa |
| `PUT` | `/api/tarefas/{id}` | Atualiza tarefa |
| `DELETE` | `/api/tarefas/{id}` | Deleta tarefa |
| `PATCH` | `/api/tarefas/{id}/concluir` | Marca como concluída |
| `PATCH` | `/api/tarefas/{id}/reabrir` | Reabre tarefa |

## 📝 Exemplos de Uso

### Criar Tarefa
```bash
curl -X POST http://localhost:8080/api/tarefas \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Estudar Spring Boot",
    "descricao": "Aprender os conceitos fundamentais"
  }'
```

### Listar Tarefas
```bash
curl http://localhost:8080/api/tarefas
```

### Atualizar Tarefa
```bash
curl -X PUT http://localhost:8080/api/tarefas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Estudar Spring Boot avançado",
    "descricao": "Aprender tópicos avançados",
    "concluida": true
  }'
```

## 🧪 Executar Testes

```bash
mvn test
```

Testes incluem:
- ✅ Listar todas as tarefas
- ✅ Buscar tarefa por ID
- ✅ Lançar exceção quando tarefa não existe
- ✅ Criar tarefa
- ✅ Lançar exceção ao criar sem título
- ✅ Atualizar tarefa
- ✅ Deletar tarefa
- ✅ Lançar exceção ao deletar tarefa inexistente

## 🔍 Tratamento de Erros

A API retorna respostas estruturadas com status HTTP apropriados:

```json
{
  "status": 404,
  "mensagem": "Tarefa com ID 99 não encontrada.",
  "timestamp": "2026-06-18T10:30:45"
}
```

## 📚 Padrões de Commits Git

Este projeto segue a especificação de commits convencionais:

- `feat:` - Nova funcionalidade
- `fix:` - Correção de bug
- `docs:` - Documentação
- `test:` - Adição de testes
- `refactor:` - Refatoração de código
- `style:` - Mudanças de formatação

Exemplo:
```bash
git commit -m "feat: adiciona endpoint de criação de tarefa"
```

## 🚀 Próximos Passos

- [ ] Adicionar autenticação JWT
- [ ] Integrar com PostgreSQL
- [ ] Deploy em Docker
- [ ] Deploy em nuvem (Railway/Render)
- [ ] Adicionar testes de integração

## 👨‍💻 Autor

**Josiel Rabelo**  
GitHub: [github.com/josielsants](https://github.com/josielsants)

## 📄 Licença

Este projeto está sob a licença MIT.

---

⭐ Se este projeto foi útil, considere dar uma estrela no GitHub!
