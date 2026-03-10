# 🚗 API Oficina Mecânica

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de uma oficina mecânica.

Este foi meu **primeiro projeto backend completo**, criado com o objetivo de praticar conceitos importantes de desenvolvimento de APIs utilizando o ecossistema Spring.

---

# 🚀 Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- MapStruct
- Specification (JPA)
- Lombok
- Maven

---

# 📚 Conceitos praticados

Durante o desenvolvimento deste projeto foram aplicados diversos conceitos importantes:

- Arquitetura em camadas (**Controller, Service, Repository**)
- Uso de **DTOs** para comunicação da API
- Conversão entre **DTO e Entidade com MapStruct**
- **Tratamento global de exceções**
- **Paginação de resultados**
- **Filtros dinâmicos utilizando Specification**
- Implementação de **regras de negócio**
- Uso de **Enum para controle de status**

---

# 📦 Funcionalidades da API

## 👤 Clientes
- Cadastrar cliente
- Atualizar cliente
- Buscar cliente por ID
- Filtrar clientes com paginação e Specification

## 🚘 Veículos
- Cadastrar veículo
- Atualizar veiculo
- Buscar veículos por ID
- Filtrar veiculos com paginação e Specification

## 🔧 Ordens de Serviço
- Criar ordem de serviço
- Atualizar ordem
- Buscar ordens por ID
- Filtrar ordens de serviço com paginação e Specification

---

# 📋 Regras de negócio implementadas

- Uma ordem **não pode ser concluída duas vezes**
- Uma ordem só pode ser concluida caso esteja **em_andamento**
- Uma ordem **não pode ser cancelada após ser concluída**

---

# 🗂 Estrutura do projeto
- model
- repository
- service
- controller
- dto
- mapper
- specification
- validation
- exception
-
---

# 🎯 Objetivo do projeto

Este projeto foi desenvolvido com fins de **aprendizado**, aplicando na prática conceitos importantes do desenvolvimento backend com **Java e Spring Boot**.

---

