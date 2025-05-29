# 🌿 BioSentinela – Monitoramento Inteligente de Áreas com Espécies em Extinção

Sistema completo com sensores IoT, alertas e autenticação via JWT para ajudar no monitoramento ambiental em áreas críticas.

---

## 📘 Visão Geral

Este sistema realiza o monitoramento de áreas afetadas por queimadas e desmatamento usando sensores IoT. Permite o cadastro de Regiões, Sensores e Alertas, com controle e visualização via API REST + Swagger.

---

## 🚀 Como Executar o Projeto

1. Clone o projeto:
```bash
git clone https://github.com/GS-BioSentinela/Java-BioSentinela.git
cd biosentinela-api
```

2. Execute via Maven ou diretamente pela sua IDE.
3. Acesse o Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
4. Acesse o banco H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:biosentinela-db`
   - User: `sa`
   - Password: *(em branco)*

---

## 🔐 Autenticação JWT

### Cadastro de Usuário

**POST /auth/register**
```json
{
  "username": "admin",
  "password": "123456"
}
```

### Login

**POST /auth/login**
```json
{
  "username": "admin",
  "password": "123456"
}
```

**Resposta:**
```text
Bearer eyJhbGciOiJIUzUxMiJ9...
```

Copie o token, clique no botão 🔒 **Authorize** no Swagger e cole assim:
```
SEU_TOKEN
```

---

## 🔁 Exemplo de Requisições Protegidas

### 📍 POST /regioes
```json
{
  "nome": "Amazônia Sul",
  "bioma": "Amazônico"
}
```

### 📡 POST /sensores
```json
{
  "tipo": "Temperatura",
  "localizacao": "Latitude -15.6, Longitude -56.1",
  "regiao": {
    "id": 1
  }
}
```

### 🚨 POST /alertas
```json
{
  "tipo": "Fumaça detectada",
  "mensagem": "Alta concentração de fumaça detectada em região crítica",
  "sensor": {
    "id": 1
  }
}
```

---

## ✅ Status

Em andamento 

## 👥 Equipe

- Gabriel Gomes Mancera – RM: 555427
- Victor Hugo Carvalho – RM: 558550
- Juliana de Andrade Sousa – RM: 558834
