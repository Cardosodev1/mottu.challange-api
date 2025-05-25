# 🛠️ Mottu Challenge API

Este projeto é uma API desenvolvida como parte de um desafio proposto pela Mottu. A aplicação foi construída em **Java com Spring Boot**, e tem como objetivo gerenciar dispositivos, funcionários, motocicletas, unidades e pátios da empresa de forma eficiente e organizada.

## 👨‍💻 Equipe

- **Guilherme Cardoso** – RM555178  
- **João Pedro Motta** – RM556557  
- **Hassan Chahine** – RM556715

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- JPA/Hibernate
- H2 Database (para testes)
- Lombok

## 📁 Estrutura do Projeto

O projeto está dividido nas seguintes camadas:

- `controller`: Camada responsável pelos endpoints da API.
- `dto`: Objetos de transferência de dados entre camadas.
- `entity`: Entidades que representam as tabelas do banco de dados.
- `repository`: Interfaces responsáveis pela persistência de dados.

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 17 instalado
- Maven instalado

### Passos para rodar localmente

1. Clone este repositório:

   ```bash
   git clone https://github.com/Cardosodev1/mottu.challange-api.git
   cd mottu.challange-api

2. Compile e execute o projeto com Maven:

   ```bash
   mvnw.cmd spring-boot:run

3. Acesse a aplicação no navegador:
   - API em: `http://localhost:8080`
