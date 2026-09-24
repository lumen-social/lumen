# Lumen

Projeto de portfólio que funde três ideias em uma só plataforma: identidade e rede social (estilo Instagram), catálogo de vídeos (estilo YouTube/Netflix/Twitch) e uma camada própria de pagamentos, o **Lumen Pay** (carteira e transações estilo bancário).

Construído em fases incrementais, aplicando na prática conceitos de backend Java que vão de modelagem de dados a concorrência, performance e segurança.

> 🚧 Projeto em desenvolvimento ativo, construído fase a fase como estudo aplicado — o código evolui continuamente.

---

## Stack

- **Java 17**
- **Spring Boot 4.x** (Web, Data JPA, Security, Validation)
- **PostgreSQL**
- **Flyway** — versionamento de schema do banco
- **JWT** (jjwt) — autenticação sem estado
- **Testcontainers** + **JUnit** — testes de integração com banco real
- **Spring Boot Actuator** — health checks

## Estrutura do projeto

```
src/
├── main/
│   ├── java/com/lumen/social/
│   │   ├── controller/     # endpoints REST
│   │   ├── service/        # regras de negócio
│   │   ├── repository/     # acesso a dados (Spring Data JPA)
│   │   ├── utils/          # utilitários
│   │   └── LumenApplication.java
│   └── resources/
│       ├── application.yaml
│       ├── db/migration/   # migrations Flyway
│       ├── static/
│       └── templates/
└── test/
    └── java/com/lumen/social/
        ├── LumenApplicationTests.java
        ├── TestLumenApplication.java
        └── TestcontainersConfiguration.java
```

## Pré-requisitos

- JDK 17
- Maven
- Docker (usado pelo Testcontainers para subir um PostgreSQL descartável nos testes)
- Uma instância PostgreSQL para rodar a aplicação localmente (fora dos testes)

## Configuração

A aplicação lê a configuração de variáveis de ambiente (veja `src/main/resources/application.yaml`). Crie um arquivo `.env` na raiz do projeto:

```env
DATABASE_URL=jdbc:postgresql://localhost:5432/lumen
DATABASE_USERNAME=lumen_user
DATABASE_PASSWORD=sua_senha_aqui
SERVER_PORT=8080
```

## Rodando o projeto

```bash
# clonar o repositório
git clone https://github.com/SEU_USUARIO/lumen.git
cd lumen

# rodar
mvn spring-boot:run
```

## Testes

Os testes de integração sobem um PostgreSQL real via Testcontainers (não usam H2/mock), garantindo que o comportamento testado é o mesmo do banco de produção.

```bash
mvn test
```

Também é possível rodar a aplicação em modo de desenvolvimento com o banco de teste gerenciado automaticamente:

```bash
mvn spring-boot:test-run
```

## Roadmap

O projeto é construído em fases, evoluindo de um monólito simples até tópicos avançados como concorrência, escalabilidade e observabilidade. A fase atual está documentada na pasta `docs/` do repositório (diagramas de arquitetura e decisões técnicas — ADRs).

## Autores

Projeto desenvolvido em dupla, cada um ancorando um módulo:
- **Identidade & Social** (autenticação, follow, comentários, curtidas)
- **Catálogo & Lumen Pay** (vídeos, carteira, transações)

## Licença

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE) — sinta-se livre para usar, estudar e se inspirar no código.