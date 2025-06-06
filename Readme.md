# 🌿 BioSentinela – Monitoramento Inteligente de Áreas com Espécies em Extinção

Sistema completo desenvolvido com Java e Spring Boot, focado no monitoramento de áreas ambientais sensíveis com sensores IoT. O BioSentinela permite registrar alertas automáticos com autenticação segura via JWT, documentação com Swagger, deploy em nuvem e testes com JUnit.

---

## 📌 Objetivo do Projeto

O BioSentinela tem como missão auxiliar na preservação ambiental, monitorando regiões com fauna em risco de extinção. Através de sensores instalados em campo, o sistema identifica condições críticas (como fumaça, temperatura elevada ou baixa umidade) e emite alertas em tempo real.

---

## 🌍 Deploy da API (Produção)

* 🔗 Swagger Online: [biosentinela-api.onrender.com/swagger-ui.html](https://biosentinela-api.onrender.com/swagger-ui.html)
* ✅ Status da API: [`/`](https://biosentinela-api.onrender.com/)
* ⚙️ Health Check: [`/health`](https://biosentinela-api.onrender.com/health)

---

## 🚀 Executando Localmente

```bash
git clone https://github.com/GS-BioSentinela/Java-BioSentinela.git
cd Java-BioSentinela
./mvnw spring-boot:run
```

* Swagger local: `http://localhost:8080/swagger-ui.html`
* Console H2: `http://localhost:8080/h2-console`

  * JDBC URL: `jdbc:h2:mem:biosentinela-db`
  * Usuário: `sa` | Senha: *(em branco)*

---

## 📁 Principais Endpoints

*(Endpoints protegidos requerem autenticação JWT via Bearer Token)*

### 🌎 Regiões (Protegido)

* `POST /regioes`
* `GET /regioes` (Suporta paginação e ordenação padrão)
* `GET /regioes/{id}`
* `PUT /regioes/{id}`
* `DELETE /regioes/{id}`

### 🌡️ Sensores (Protegido)

* `POST /sensores`
* `GET /sensores` (Suporta filtro `?tipo=...`, paginação `?page=...&size=...` e ordenação `?sort=...,asc/desc`)
* `GET /sensores/{id}`
* `PUT /sensores/{id}`
* `DELETE /sensores/{id}`

### 🚨 Alertas (Protegido)

* `POST /alertas`
* `GET /alertas` (Suporta paginação `?page=...&size=...` e ordenação `?sort=...,asc/desc`)
* `GET /alertas/{id}`
* `PUT /alertas/{id}`
* `DELETE /alertas/{id}`
* `GET /alertas/stats` (Estatísticas por tipo de alerta)

### 🛡️ Autenticação (Público)

* `POST /auth/register`
* `POST /auth/login`

### ⚙️ Monitoramento (Público)

* `GET /` – Confirmação de execução da API
* `GET /health` – Endpoint para sistemas de monitoramento (ex: health check do Render)

---

## ✅ Boas Práticas Implementadas

* 🔐 JWT Token com filtro de segurança e `@SecurityRequirement` no Swagger
* ⚠️ Tratamento global de erros (`GlobalExceptionHandler`)
* 🧾 Validações com `@NotBlank`, `@Valid`
* 🔍 DTOs e Responses separados (entrada e saída)
* 📦 Paginação e ordenação (`Pageable`, `Sort`, `@ParameterObject`)
* 📊 Estatísticas agregadas (`/alertas/stats`)
* 🧪 Testes automatizados com JUnit (service layer) - *Cobertura ampliada, veja seção abaixo*

---

## 🧪 Testes Recomendados

Esta seção descreve os passos recomendados para testar manualmente as principais funcionalidades da API utilizando o Swagger.

### 🔐 Autenticação (Passos Iniciais)

1.  **Registre um novo usuário:**
    `POST /auth/register`
    ```json
    {
      "username": "admin",
      "password": "123456"
    }
    ```
2.  **Faça o login:**
    `POST /auth/login`
    ```json
    {
      "username": "admin",
      "password": "123456"
    }
    ```
3.  **Copie o Token JWT:** Copie o `token` retornado na resposta do login.
4.  **Autorize no Swagger:** Clique no botão **🔒 Authorize** no topo da página do Swagger, cole o token JWT no campo `Value` (prefixado por `Bearer `) e clique em `Authorize`.

📌 Agora você está autenticado e pode testar os endpoints protegidos.

### 🌎 Regiões

5.  Criar região com:
    `POST /regioes`
    ```json
    {
      "nome": "Pantanal Sul",
      "bioma": "Pantanal"
    }
    ```
6.  Listar (`GET /regioes?page=0&size=5`)
7.  Buscar por ID (`GET /regioes/1`)
8.  Atualizar (`PUT /regioes/1`):
    ```json
    {
      "nome": "Pantanal Norte",
      "bioma": "Pantanal"
    }
    ```
9.  Deletar (`DELETE /regioes/1`)

### 🌡️ Sensores

10. Criar sensor com:
    `POST /sensores`
    ```json
    {
      "tipo": "Temperatura",
      "localizacao": "-3.123, -60.456",
      "regiaoId": 1
    }
    ```
11. Listar sensores com filtro: `GET /sensores?tipo=Temperatura&page=0&size=5`
12. Listar com ordenação: `GET /sensores?sort=tipo,asc`
13. Buscar por ID (`GET /sensores/1`), atualizar (`PUT /sensores/1`) e deletar (`DELETE /sensores/1`)

### 🚨 Alertas

14. Criar alerta:
    `POST /alertas`
    ```json
    {
      "tipo": "Fumaça",
      "mensagem": "Fumaça detectada na floresta.",
      "sensorId": 1
    }
    ```
15. Listar (`GET /alertas?page=0&size=5`), ordenar (`?sort=tipo,asc`)
16. Buscar por ID (`GET /alertas/1`), atualizar (`PUT /alertas/1`) e deletar (`DELETE /alertas/1`)
17. Ver estatísticas (`GET /alertas/stats`)

### ⚠️ Validações e Erros

18. Tente criar um alerta com campo obrigatório vazio (ex: `tipo`) → Deve retornar erro 400.
19. Tente buscar um ID inexistente (ex: `GET /alertas/999`) → Deve retornar erro 404 com mensagem customizada.

### 🧪 Testes automatizados (JUnit)

O projeto possui uma cobertura robusta de testes unitários para a camada de serviço, utilizando JUnit 5 e Mockito.

**Classes Testadas:**
*   `AlertaServiceTest.java`: Testa todos os cenários de CRUD e estatísticas para Alertas.
*   `SensorServiceTest.java`: Testa todos os cenários de CRUD e filtros para Sensores.
*   `RegiaoServiceTest.java`: Testa todos os cenários de CRUD para Regiões.

**Execução:**
Para validar o ambiente e executar todos os testes automatizados (22 testes no total), utilize o comando Maven na raiz do projeto:
```bash
./mvnw test
```

---

## 🛠️ Tecnologias Utilizadas

* Java 17 + Spring Boot 3.2.5
* Spring Security + JWT + BCrypt
* Spring Data JPA + H2 Database
* Bean Validation
* Swagger / OpenAPI 3.0
* Lombok
* Maven Wrapper
* JUnit 5 + Mockito
* Render (deploy gratuito)

---

## 👥 Equipe

```
Gabriel Gomes Mancera RM:555427

Victor Hugo Carvalho Pereira RM:558550

Juliana de Andrade Sousa RM:558834
```

