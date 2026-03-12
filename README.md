# 🚗 API Oficina Mecânica
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Docker](https://img.shields.io/badge/Docker-Container-blue)

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de uma oficina mecânica.

Este foi meu **primeiro projeto backend completo**, criado com o objetivo de praticar conceitos importantes de desenvolvimento de APIs utilizando o ecossistema Spring.

---

# 🚀 Tecnologias utilizadas

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL (executando via Docker)**
- **MapStruct**
- **Specification (JPA Criteria API)**
- **Lombok**
- **Maven**

---

# 📚 Conceitos praticados

Durante o desenvolvimento deste projeto foram aplicados diversos conceitos importantes:

- Arquitetura em camadas (**Controller, Service, Repository**)
- Uso de **Enum para controle de status**
- Uso de **DTOs** para comunicação da API
- Conversão entre **DTO e Entidade com MapStruct**
- **Tratamento global de exceções**
- **Paginação de resultados**
- **Filtros dinâmicos utilizando Specification**
- Implementação de **regras de negócio**

---

# 📦 Funcionalidades da API

## 👤 Clientes
- Cadastrar cliente
- Atualizar cliente
- Buscar cliente por ID
- Filtrar clientes com paginação e Specification

## 🚘 Veículos
- Cadastrar veículo
- Atualizar veículo
- Buscar veículos por ID
- Filtrar veículos com paginação e Specification

## 🔧 Ordens de Serviço
- Criar ordem de serviço
- Atualizar ordem
- Buscar ordens por ID
- Filtrar ordens de serviço com paginação e Specification

---

# 📋 Regras de negócio implementadas

- Uma ordem **não pode ser concluída duas vezes**
- Uma ordem só pode ser concluída caso esteja com status **EM_ANDAMENTO**
- Uma ordem **não pode ser cancelada após ser concluída**

---

# 🗂 Estrutura do projeto
- **model**
- **repository**
- **service**
- **controller**
- **dto**
- **mapper**
- **specification**
- **validation**
- **exception**
---
# 📖 Aprendizados

Durante o desenvolvimento deste projeto pude praticar:

- Construção de APIs REST com Spring Boot
- Organização de projeto em camadas
- Implementação de regras de negócio
- Uso de filtros dinâmicos com Specification
- Mapeamento entre DTOs e entidades
---
# 🎯 Objetivo do projeto

Este projeto foi desenvolvido com fins de **aprendizado**, aplicando na prática conceitos importantes do desenvolvimento backend com **Java e Spring Boot**.

---

