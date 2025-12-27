# 🔗 Encurtador de URLs

API REST desenvolvida como solução para um desafio técnico de **encurtamento de URLs**.
O serviço permite converter URLs longas em versões curtas e realizar o redirecionamento para a URL original.

---

## 🧩 Sobre o Desafio

* **Criador do desafio:** Matheus Leandro Ferreira
* **Repositório original:** (https://github.com/backend-br/desafios/blob/master/url-shortener/PROBLEM.md)

---

## ⚙️ Funcionalidades

* Encurtamento de URLs longas
* Geração de códigos curtos com:

  * 5 a 10 caracteres
  * Apenas letras e números
* Persistência em banco de dados **PostgreSQL**
* URLs com prazo de validade (expiração)
* Redirecionamento para a URL original
* Retorno de **404 Not Found** para URLs inexistentes ou expiradas

---

## 🚀 Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven

---

## 🔥 Endpoints

### ➡️ Encurtar URL

**POST** `/shorten-url`

#### Request Body

```json
{
  "url": "https://backendbrasil.com.br"
}
```

#### Response

```json
{
  "url": "https://xxx.com/DXB6V"
}
```

---

### ➡️ Redirecionamento

**GET** `/{codigo}`

* Redireciona para a URL original
* Retorna **404 Not Found** caso o código não exista ou esteja expirado

---




## ▶️ Executando o Projeto

```bash
git clone https://github.com/alex-speck/encurtador-url.git
cd encurtador-url
mvn spring-boot:run
```

## 🗄️ Configuração do PostgreSQL

### 1️⃣ Criar o banco de dados

```sql
CREATE DATABASE encurtador-url;
```

### 2️⃣ Configurar o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/encurtador-url
spring.datasource.username=postgres
spring.datasource.password=ALTERE_SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 📄 Observações

Este projeto foi desenvolvido exclusivamente para fins de **estudo**.
