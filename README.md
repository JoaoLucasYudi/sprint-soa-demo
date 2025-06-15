# Serviço de Jogo Responsável (SOA-Demo)

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

## 📖 Descrição do Projeto

Este projeto é um microsserviço desenvolvido em Java com Spring Boot que simula um **Sistema de Jogo Responsável**. O objetivo é fornecer uma API RESTful para monitorar a atividade de apostas de usuários, aplicar limites de gastos e permitir que os próprios usuários se autoexcluam do sistema, promovendo um ambiente de apostas mais seguro.

## ✨ Funcionalidades Principais

-   **Criação e Gestão de Usuários:** Cadastro de perfis de usuários com limites de gastos semanais personalizados.
-   **Registro de Apostas:** Endpoint para registrar apostas feitas por um usuário.
-   **Validação de Limites:** O sistema impede que uma aposta seja registrada se ela exceder o limite de gasto semanal definido pelo usuário.
-   **Mecanismo de Autoexclusão:** Permite que um usuário se bloqueie, impedindo o registro de novas apostas em sua conta.
-   **API RESTful:** Todos os recursos são expostos de forma clara e seguindo os padrões REST.
-   **Tratamento de Exceções:** Respostas de erro claras e informativas para diferentes cenários (usuário não encontrado, limite excedido, etc.).

## 🛠️ Tecnologias Utilizadas

-   **Java 17:** Versão da linguagem utilizada.
-   **Spring Boot:** Framework principal para a criação da aplicação e do web service.
-   **Spring Web:** Para a criação de controllers e endpoints RESTful.
-   **Spring Validation:** Para validação declarativa dos dados de entrada.
-   **Maven:** Gerenciador de dependências e build do projeto.
-   **Lombok:** Para reduzir código boilerplate (getters, setters, construtores).
-   **Postman:** Ferramenta utilizada para testar os endpoints da API.

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
-   [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
-   [Apache Maven](https://maven.apache.org/download.cgi).
-   Um cliente de API como o [Postman](https://www.postman.com/downloads/).
-   Sua IDE de preferência (IntelliJ, Eclipse, VSCode, etc.).

## 🚀 Como Executar o Projeto

Siga os passos abaixo para executar o serviço localmente:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git](https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git)
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd soa-demo
    ```

3.  **Execute a aplicação com o Maven:**
    ```bash
    mvn spring-boot:run
    ```

4.  A aplicação iniciará e estará disponível em `http://localhost:8080`. Você verá no console uma mensagem indicando que o servidor Tomcat foi iniciado na porta 8080.

## 🔌 Documentação da API (Endpoints)

A seguir estão os endpoints disponíveis e como interagir com eles.

| Funcionalidade | Método HTTP | URL | Corpo da Requisição (Exemplo) |
| :--- | :--- |:--- |:--- |
| **Listar todos os usuários** | `GET` | `/api/jogo-responsavel/usuarios` | - |
| **Buscar usuário por ID** | `GET` | `/api/jogo-responsavel/usuarios/{id}` | - |
| **Criar novo usuário** | `POST` | `/api/jogo-responsavel/usuarios` | `{ "nome": "Ana Costa", "limiteGastoSemanal": 150.0 }` |
| **Registrar uma aposta** | `POST` | `/api/jogo-responsavel/usuarios/{id}/registrar-aposta` | `{ "valor": 50.0 }` |
| **Ativar autoexclusão** | `POST` | `/api/jogo-responsavel/usuarios/{id}/autoexclusao` | - |

---

### Exemplos de Respostas

#### Sucesso (200 OK ou 201 Created)
```
// Resposta ao buscar o usuário com ID 1
{
    "id": 1,
    "nome": "João Silva",
    "limiteGastoSemanal": 500.0,
    "gastoAtualSemanal": 0.0,
    "status": "ATIVO"
}

````

#### Erro - Limite Excedido (403 Forbidden)
````
{
    "erro": "Aposta recusada. Limite de gasto semanal (R$200.0) seria excedido."
}
````
#### Erro - Recurso Não Encontrado (404 Not Found)
````
{
    "erro": "Usuário com ID 99 não encontrado."
}
````

#### Erro - Dados Inválidos (400 Bad Request)
````
{
    "valor": "O valor da aposta deve ser positivo."
}
````