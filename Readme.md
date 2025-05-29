
# 🌿 BioSentinela – Monitoramento Inteligente de Áreas com Espécies em Extinção

Sistema completo em Java com Spring Boot que integra sensores IoT, alertas automáticos e autenticação JWT, desenvolvido como parte do Global Solution da FIAP.

---

## 📘 Visão Geral

O projeto **BioSentinela** permite monitorar áreas ambientais sensíveis através de sensores de temperatura, umidade e fumaça, com emissão de alertas automáticos. Os dados são organizados por regiões e acessados via API REST documentada com Swagger.

---

## 🚀 Como Executar o Projeto

1. **Clone o projeto:**

```bash
git clone https://github.com/GS-BioSentinela/Java-BioSentinela.git
cd Java-BioSentinela
```

2. **Execute o projeto com Maven ou por sua IDE **

3. **Acesse o Swagger:**
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

4. **Acesse o console H2:**
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:biosentinela-db`
   - User: `sa`
   - Password: *(deixe em branco)*

---

## 🔐 Autenticação JWT

### 📥 Cadastro

`POST /auth/register`

```json
{
  "nome": "Admin",
  "email": "admin@biosentinela.com",
  "senha": "123456"
}
```

### 🔐 Login

`POST /auth/login`

```json
{
  "email": "admin@biosentinela.com",
  "senha": "123456"
}
```

O token gerado deve ser usado em **Authorize (🔒)** no Swagger:
```
SEU_TOKEN_JWT
```

---

## 📂 Endpoints Disponíveis

### 🌎 Região

- `POST /regioes`

```json
{
  "nome": "Amazônia Sul",
  "bioma": "Amazônico"
}
```

- `GET /regioes`
- `GET /regioes/{id}`
- `PUT /regioes/{id}`
- `DELETE /regioes/{id}`

---

### 🌡️ Sensor

- `POST /sensores`

```json
{
  "tipo": "Temperatura",
  "localizacao": "Latitude -15, Longitude -55",
  "regiaoId": 1
}
```

- `GET /sensores`
   - Parâmetro opcional: `tipo`
- `GET /sensores/{id}`
- `PUT /sensores/{id}`
- `DELETE /sensores/{id}`

---

### 🚨 Alerta

- `POST /alertas`

```json
{
  "tipo": "Fumaça",
  "mensagem": "Alta concentração de fumaça detectada",
  "sensorId": 1
}
```

- `GET /alertas`
- `GET /alertas/{id}`
- `PUT /alertas/{id}`
- `DELETE /alertas/{id}`
- `GET /alertas/stats`  
  Estatísticas agrupadas por tipo.

---

## ✅ Validações e Tratamento de Erros

- Campos obrigatórios com `@NotBlank`
- `ResourceNotFoundException` para erros 404
- Validação estruturada em `GlobalExceptionHandler`
- Mensagens amigáveis com data/hora e detalhes

---

## 🧪 Testes Recomendados no Swagger

1. Cadastrar usuário (`/auth/register`)
2. Logar e copiar token JWT (`/auth/login`)
3. Cadastrar Região
4. Cadastrar Sensor com `regiaoId` existente
5. Cadastrar Alerta com `sensorId` existente
6. Testar filtros (`/sensores?tipo=Temperatura`)
7. Testar `GET /alertas/stats`
8. Testar mensagens de erro (ex: `GET /alertas/999`)

---

## 💡 Tecnologias Usadas

- Java 17 + Spring Boot
- Spring Security + JWT
- Spring Data JPA
- H2 Database
- OpenAPI/Swagger 3
- Lombok

---

## 📦 Melhorias Futuras

- Deploy com Docker e Render
- Exportação de relatórios
- Integração com sensores reais (IoT)
- Dashboard Web e App Mobile

---

## 👥 Autores

Projeto desenvolvido por alunos da FIAP – Global Solution 2025

---

