
# 🌿 Projeto BioSentinela – Monitoramento Inteligente de Áreas com Espécies em Extinção

## 📘 Visão Geral
Este sistema realiza o monitoramento de áreas afetadas por queimadas e desmatamento usando sensores IoT. Ele permite o cadastro de Regiões, Sensores e Alertas, com controle e visualização via API REST.

---

## 🚀 Como Executar o Projeto

1. Clone o projeto:
```bash
git clone https://github.com/seuusuario/biosentinela-api.git
cd biosentinela-api
```

2. Execute via Maven ou diretamente pela sua IDE (IntelliJ/VSCode).
3. Acesse o Swagger: `http://localhost:8080/swagger-ui.html`
4. Acesse o banco H2: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:biosentinela-db`
    - User: `sa`
    - Password: *(em branco)*

---

## 🧪 Endpoints e Testes

### 📍 Regiões
**POST /regioes**
```json
{
  "nome": "Pantanal Sul",
  "bioma": "Pantanal"
}
```

**PUT /regioes/{id}**
```json
{
  "nome": "Amazônia Norte",
  "bioma": "Amazônico"
}
```

### 📡 Sensores
**POST /sensores**
```json
{
  "tipo": "Temperatura",
  "localizacao": "Latitude -15.6, Longitude -56.1",
  "regiao": {
    "id": 1
  }
}
```

**PUT /sensores/{id}**
```json
{
  "tipo": "Fumaça",
  "localizacao": "Ponto Alto",
  "regiao": {
    "id": 1
  }
}
```

### 🚨 Alertas
**POST /alertas**
```json
{
  "tipo": "Fumaça detectada",
  "mensagem": "Alta concentração de fumaça detectada em região crítica",
  "sensor": {
    "id": 1
  }
}
```

**PUT /alertas/{id}**
```json
{
  "tipo": "Alerta de Calor",
  "mensagem": "Temperatura acima de 42°C detectada",
  "sensor": {
    "id": 1
  }
}
```

---

## 🛡️ Segurança
- Swagger, H2 e todas as rotas da API foram liberadas para facilitar o desenvolvimento.
- Em produção, recomenda-se proteger os endpoints com autenticação.

---

## 🧱 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.2.5
- Spring Web, Data JPA, Security, Validation
- H2 Database (in-memory)
- Swagger/OpenAPI (springdoc-openapi)
- Lombok

---

## 👥 Equipe FIAP
- Gabriel Gomes Mancera (RM: 555427)
- Victor Hugo Carvalho (RM: 558550)
- Juliana de Andrade Sousa (RM: 558834)

---
