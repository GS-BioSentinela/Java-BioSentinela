
# 🌿 BioSentinela – Monitoramento Inteligente de Áreas com Espécies em Extinção

Sistema completo desenvolvido com Java e Spring Boot, focado no monitoramento de áreas ambientais sensíveis com sensores IoT. O BioSentinela permite registrar alertas automáticos com autenticação segura via JWT e interface de testes via Swagger.

---

## 📌 Objetivo do Projeto

O BioSentinela tem como missão auxiliar na preservação ambiental, monitorando regiões com fauna em risco de extinção. Através de sensores instalados em campo, o sistema identifica condições críticas (como fumaça, temperatura elevada ou baixa umidade) e emite alertas em tempo real.

---

## 🌍 Deploy da API (Produção)

- 🔗 Swagger Online: [biosentinela-api.onrender.com/swagger-ui.html](https://biosentinela-api.onrender.com/swagger-ui.html)
- ✅ Status da API: [`/`](https://biosentinela-api.onrender.com/)
- ⚙️ Health Check: [`/health`](https://biosentinela-api.onrender.com/health)

---

## 🚀 Executando Localmente

```bash
git clone https://github.com/GS-BioSentinela/Java-BioSentinela.git
cd Java-BioSentinela
./mvnw spring-boot:run
```

  - Swagger local: `http://localhost:8080/swagger-ui.html`
- Console H2: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:biosentinela-db`
    - Usuário: `sa` | Senha: *(em branco)*

---

## 🔐 Autenticação JWT

### Cadastro

`POST /auth/register`
```json
{
  "nome": "Admin",
  "email": "admin@biosentinela.com",
  "senha": "123456"
}
```

### Login

`POST /auth/login`
```json
{
  "email": "admin@biosentinela.com",
  "senha": "123456"
}
```

📌 Após o login, use o token gerado no botão **🔒 Authorize** do Swagger.

---

## 📁 Principais Endpoints

### 🌎 Regiões

- `POST /regioes`
- `GET /regioes`
- `GET /regioes/{id}`
- `PUT /regioes/{id}`
- `DELETE /regioes/{id}`

### 🌡️ Sensores

- `POST /sensores`
- `GET /sensores` → suporte a filtros `?tipo=Temperatura`
- `GET /sensores/{id}`
- `PUT /sensores/{id}`
- `DELETE /sensores/{id}`

### 🚨 Alertas

- `POST /alertas`
- `GET /alertas`
- `GET /alertas/{id}`
- `PUT /alertas/{id}`
- `DELETE /alertas/{id}`
- `GET /alertas/stats` → estatísticas por tipo

### 🛡️ Auth

- `POST /auth/register`
- `POST /auth/login`

### ⚙️ Monitoramento

- `GET /` – confirmação de execução
- `GET /health` – para sistemas de monitoramento

---

## ✅ Boas Práticas Implementadas

- 🔐 JWT Token com filtro de segurança
- ⚠️ Tratamento global de erros (`GlobalExceptionHandler`)
- 🧾 Validações com `@NotBlank`, `@Valid`
- 🔍 DTOs e Responses separados para segurança e clareza

---

## 🧪 Testes Recomendados

1. Registrar usuário
2. Realizar login e copiar token JWT
3. Criar região
4. Criar sensor com `regiaoId`
5. Criar alerta com `sensorId`
6. Listar e filtrar sensores
7. Ver estatísticas em `/alertas/stats`
8. Simular erro 404 com `/alertas/999`

---

## 🛠️ Tecnologias Utilizadas

- Java 17 + Spring Boot 3
- Spring Security + JWT
- JPA + H2
- Swagger (OpenAPI 3)
- Lombok
- Render (deploy gratuito com build automático)

---

## 👥 Equipe

Gabriel Gomes Mancera RM:555427
Victor Hugo Carvalho Pereira RM:5558550
Juliana de Andrade Sousa RM:558834

