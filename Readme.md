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

## 🔐 Autenticação JWT

### Cadastro

`POST /auth/register`

```json
{
  "username": "admin",
  "password": "123456"
}
```

### Login

`POST /auth/login`

```json
{
  "username": "admin",
  "password": "123456"
}
```

📌 Após o login, copie o token retornado e clique em **🔒 Authorize** no Swagger para autenticar.

---

## 📁 Principais Endpoints

### 🌎 Regiões

* `POST /regioes`
* `GET /regioes`
* `GET /regioes/{id}`
* `PUT /regioes/{id}`
* `DELETE /regioes/{id}`

### 🌡️ Sensores

* `POST /sensores`
* `GET /sensores` → suporte a filtros `?tipo=Temperatura`, paginação e ordenação `?sort=tipo,asc`
* `GET /sensores/{id}`
* `PUT /sensores/{id}`
* `DELETE /sensores/{id}`

### 🚨 Alertas

* `POST /alertas`
* `GET /alertas` → suporte a paginação e ordenação `?sort=tipo,asc`
* `GET /alertas/{id}`
* `PUT /alertas/{id}`
* `DELETE /alertas/{id}`
* `GET /alertas/stats` → estatísticas por tipo

### 🛡️ Auth

* `POST /auth/register`
* `POST /auth/login`

### ⚙️ Monitoramento

* `GET /` – confirmação de execução
* `GET /health` – para sistemas de monitoramento

---

## ✅ Boas Práticas Implementadas

* 🔐 JWT Token com filtro de segurança e `@SecurityRequirement` no Swagger
* ⚠️ Tratamento global de erros (`GlobalExceptionHandler`)
* 🧾 Validações com `@NotBlank`, `@Valid`
* 🔍 DTOs e Responses separados (entrada e saída)
* 📦 Paginação e ordenação (`Pageable`, `Sort`, `@ParameterObject`)
* 📊 Estatísticas agregadas (`/alertas/stats`)
* 🧪 Testes automatizados com JUnit (service layer)

---

## 🧪 Testes Recomendados

### 🔐 Autenticação

1. `POST /auth/register` → criar novo usuário
2. `POST /auth/login` → fazer login e copiar token JWT
3. Usar token com **🔒 Authorize** no Swagger

### 🌎 Regiões

4. Criar região com:

```json
{
  "nome": "Pantanal Sul",
  "bioma": "Pantanal"
}
```

5. Listar (`/regioes?page=0&size=5`)
6. Buscar por ID (`/regioes/1`)
7. Atualizar:

```json
{
  "nome": "Pantanal Norte",
  "bioma": "Pantanal"
}
```

8. Deletar (`/regioes/1`)

### 🌡️ Sensores

9. Criar sensor com:

```json
{
  "tipo": "Temperatura",
  "localizacao": "-3.123, -60.456",
  "regiaoId": 1
}
```

10. Listar sensores com filtro: `/sensores?tipo=Temperatura&page=0&size=5`
11. Listar com ordenação: `/sensores?sort=tipo,asc`
12. Buscar por ID, atualizar e deletar

### 🚨 Alertas

13. Criar alerta:

```json
{
  "tipo": "Fumaça",
  "mensagem": "Fumaça detectada na floresta.",
  "sensorId": 1
}
```

14. Listar `/alertas?page=0&size=5`, ordenar `?sort=tipo,asc`
15. Buscar por ID, atualizar e deletar
16. Ver estatísticas `/alertas/stats`

### ⚠️ Validações e Erros

17. Criar alerta com campo vazio → erro 400
18. Buscar ID inexistente `/alertas/999` → erro 404 com mensagem customizada

### 🧪 Testes automatizados (JUnit)

19. Testar método `salvar()` e `buscarPorId()` de `SensorService`
20. Validar ambiente de testes rodando com `./mvnw test`

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

Gabriel Gomes Mancera RM:555427
Victor Hugo Carvalho Pereira RM:5558550
Juliana de Andrade Sousa RM:558834
