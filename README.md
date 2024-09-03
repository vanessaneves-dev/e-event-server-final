
# 🎉 E-event API

**Projeto:** Sistema de Gestão de Eventos  
**Desenvolvido por:** Vanessa Neves  
**Academia:** 7ª Academia Java

![E-event](https://firebasestorage.googleapis.com/v0/b/upload-344ee.appspot.com/o/uploads%2FE-events.png?alt=media&token=937a20b2-7c9d-4e2e-84b0-357853263354) 

## 📋 Descrição do Projeto

O **E-event API** é uma solução completa para gestão de eventos, oferecendo recursos para organizadores e participantes. A aplicação foi desenvolvida como um projeto final para a 7ª Academia Java, com o objetivo de consolidar conhecimentos adquiridos durante o curso.

## 🚀 Funcionalidades

- **Gestão de Eventos:** Criação, atualização, exclusão e listagem de eventos.
- **Confirmação de Presença:** Participantes podem confirmar presença nos eventos.
- **Favoritos:** Permite que participantes marquem eventos como favoritos.
- **Autenticação e Autorização:** Utilização de Spring Security com OAuth2 e JWT para segurança.
- **Sistema de Usuários:** CRUD completo para gerenciar organizadores e participantes.
- **Integração com Banco de Dados:** PostgreSQL usando JPA com Hibernate.
- **Facilidade de Desenvolvimento:** Utilização de Lombok para redução de boilerplate code.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework Backend:** Spring Boot
- **Segurança:** Spring Security com OAuth2 e JWT
- **Persistência:** JPA com Hibernate
- **Banco de Dados:** PostgreSQL
- **Gerenciamento de Dependências:** Maven
- **Gerenciamento de Código:** Lombok

## 📦 Estrutura do Projeto

```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── projetofinal
│   │   │           └── eeventserverfinal
│   │   │               ├── controllers
│   │   │               ├── dto
│   │   │               ├── exeptions
│   │   │               ├── models
│   │   │               ├── repository
│   │   │               ├── security
│   │   │               ├── services
│   │   │               └── WebConfig
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
└── pom.xml
```

## 📄 Endpoints da API

### 🔐 Autenticação

- **POST /api/user/auth**
- **POST /api/organizer/auth**
  - Realiza a autenticação do usuário (participante ou organizador) e retorna um token JWT.
- **POST /api/user/new**
- **POST /api/organizer/new**
  - Registra um novo usuário (participante ou organizador) no sistema.
 
### 📝 Perfil
- **PUT /api/user/auth/{id}**
  - Exibe os dados e habilita a edição do perfil.
- **PUT /api/organizer/auth/{id}**
  - Exibe os dados e habilita a edição do perfil.

### 🎟️ Eventos

- **GET /events**
  - Retorna uma lista de todos os eventos.
- **POST /api/events**
  - Cria um novo evento (somente para organizadores).
- **PUT /api/events/{id}**
  - Atualiza as informações de um evento (somente para organizadores).
- **DELETE /events/{id}**
  - Remove um evento (somente para organizadores).

### 📅 Presença - Recursos pós autenticação

- **POST /api/user-event/confirm**
  - Confirma a presença de um participante em um evento. (somente para participantes).
- **GET /api/user-event/confirmed/{userId}**
  - Mostra todos os eventos com a presença confirmada do participante.

### ❤️ Favoritos - Recursos pós autenticação

- **POST /api/user-event/favorite**
  - Marca um evento como favorito.
- **DELETE /api/user-event/favorite**
  - Remove um evento dos favoritos.
- **GET /api/user-event/favorites/{userId}**
  - Mostra todos os eventos favoritos do participante.

## 🛡️ Segurança

A segurança da API é implementada com **Spring Security** usando **OAuth2** e **JWT**. Todos os endpoints sensíveis são protegidos e só podem ser acessados com um token válido.

## 🗂️ Configuração do Banco de Dados

A API utiliza **PostgreSQL** como banco de dados principal. A configuração básica pode ser feita no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/eevent_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## 🧰 Configuração do Projeto

### Pré-requisitos

- Java 17 ou superior
- PostgreSQL
- Maven

### Instalação

1. Clone o repositório:

    ```bash
    git clone [https://github.com/vanessaneves-dev/E-events-server.git]
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd e-events-server
    ```

3. Compile o projeto com Maven:

    ```bash
    mvn clean install
    ```

4. Execute a aplicação:

    ```bash
    mvn spring-boot:run
    ```

## 👩‍💻 Contribuindo

Se você deseja contribuir para o projeto, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## 📧 Contato

Para dúvidas ou sugestões, entre em contato através de **vanessaneves.dev@gmail.com**.

## 📜 Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
