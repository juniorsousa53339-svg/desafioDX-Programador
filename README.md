# Desafio de Desenvolvimento — DX

Solução desenvolvida para o **Desafio de Desenvolvimento da DX**, com o objetivo de implementar uma aplicação para cadastro, montagem e análise de escalações de times.

O projeto foi desenvolvido a partir do código-base disponibilizado no desafio. A implementação teve como foco evoluir a estrutura existente, desenvolver as regras de negócio solicitadas, criar os endpoints da API, implementar os testes e integrar o backend com o frontend.

## Tecnologias utilizadas

### Backend

* Java 17
* Spring Boot 2.5.3
* Spring Web
* Spring Data JPA
* Hibernate
* H2 Database
* MySQL
* Lombok
* Bean Validation
* JUnit
* Mockito
* JUnit DataProvider
* SpringDoc OpenAPI / Swagger
* Maven

### Frontend

* Angular
* TypeScript
* HTML
* CSS

O projeto originalmente utilizava **Java 8**. Durante o desenvolvimento, a configuração foi atualizada para **Java 17** e foram adicionadas dependências como **Lombok**, **H2** e **SpringDoc OpenAPI**.

---

# Estrutura da aplicação

A aplicação foi organizada utilizando o padrão **MVC (Model-View-Controller)**, juntamente com uma separação das responsabilidades em diferentes camadas.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

A estrutura do backend é dividida principalmente entre:

```text
model
dto
mapper
controller
service
repository
config
```

## Model

O pacote `model` contém as entidades utilizadas pela aplicação:

* `Integrante`
* `Time`
* `ComposicaoTime`

Essas classes representam os dados persistidos e seus relacionamentos.

`ComposicaoTime` é utilizado para representar a associação entre um `Time` e um `Integrante`.

## DTO

O pacote `dto` contém os objetos utilizados para entrada e saída de dados da API.

A utilização de DTOs permite separar os dados expostos pela API das entidades utilizadas para persistência.

Por exemplo, no cadastro de um time são enviados os IDs dos integrantes que irão compor a equipe:

```json
{
  "nomeDoClube": "Chicago Bulls",
  "data": "1995-01-01",
  "integrantesIds": [1, 2, 3]
}
```

## Mapper

O pacote `mapper` é responsável por realizar a conversão entre **DTOs e entidades**.

Essa separação facilita o desenvolvimento dos Services, pois a conversão dos objetos não precisa ficar dentro do Controller ou misturada com as regras de negócio.

O fluxo pode ser representado da seguinte maneira:

```text
Request DTO
    ↓
Mapper
    ↓
Entity
    ↓
Service
    ↓
Repository
```

Dessa forma, o Mapper fica responsável pela transformação dos dados, enquanto o Service permanece focado na regra de negócio e no fluxo da aplicação.

## Controller

Os Controllers são responsáveis por receber as requisições HTTP, chamar os Services correspondentes e retornar as respostas da API.

Foram utilizados:

* `ApiController`
* `IntegranteController`
* `TimeController`

## Service

A camada de Service concentra as regras de negócio da aplicação.

Foram utilizados:

* `ApiService`
* `IntegranteService`
* `TimeService`

O `IntegranteService` e o `TimeService` são responsáveis pelos cadastros.

O `ApiService` concentra principalmente as regras de processamento e análise dos dados solicitadas pelo desafio.

## Repository

Os Repositories são responsáveis pelo acesso aos dados utilizando **Spring Data JPA**.

Foram utilizados:

* `IntegranteRepository`
* `TimeRepository`
* `ComposicaoTimeRepository`

## Config

O pacote `config` contém as configurações necessárias para a aplicação.

Uma das principais responsabilidades é configurar a comunicação entre o **backend Spring Boot** e o **frontend Angular**, permitindo que o frontend, executado em `localhost:4200`, possa realizar requisições para a API executada em `localhost:8080`.

Essa configuração é importante porque frontend e backend são executados em portas diferentes durante o desenvolvimento.

```text
Angular
localhost:4200
      │
      │ HTTP
      ↓
Spring Boot
localhost:8080
```

A configuração de CORS permite essa comunicação entre as duas aplicações.

---

# Tratamento e processamento dos dados

A principal parte do desafio foi implementada no `ApiService`.

Os dados são recuperados através dos Repositories e o processamento é realizado em Java, utilizando estruturas como:

```text
List
Map
Set
Streams
```

Foram implementadas funcionalidades para:

* buscar o time de uma determinada data;
* identificar o integrante mais utilizado;
* identificar a composição de time mais recorrente;
* identificar a função mais recorrente;
* identificar o clube mais recorrente;
* realizar a contagem de clubes em determinado período;
* realizar a contagem por função.

---

# Filtro por período

Uma das implementações realizadas foi a criação de uma lógica reutilizável para trabalhar com períodos.

Os métodos de análise recebem:

```text
dataInicial
dataFinal
```

As duas datas podem ser informadas ou permanecer `null`.

Com isso, a aplicação consegue trabalhar com:

* período definido;
* somente data inicial;
* somente data final;
* nenhuma data, considerando todos os registros.

A lógica foi centralizada para evitar repetição de código nos diferentes métodos de processamento.

---

# Testes

Além de utilizar os testes já existentes no projeto, foram adicionados cenários para validar principalmente o comportamento do **filtro por período**.

Foram testadas situações como:

```text
Data inicial + data final
Data inicial definida e data final nula
Data inicial nula e data final definida
Data inicial e data final nulas
```

Os testes foram implementados na classe de testes do `ApiService`, utilizando também **DataProvider** para trabalhar com diferentes conjuntos de dados e cenários.

Esses testes adicionais tiveram como objetivo garantir que o filtro por período funcionasse corretamente mesmo quando uma ou ambas as datas não fossem informadas.

---

# API REST

A aplicação possui endpoints para cadastro e processamento dos dados.

## Cadastro de integrantes

```http
POST /integrantes
```

Exemplo:

```json
{
  "nome": "Michael Jordan",
  "funcao": "ala"
}
```

## Cadastro de times

```http
POST /Times
```

Exemplo:

```json
{
  "nomeDoClube": "Chicago Bulls",
  "data": "1995-01-01",
  "integrantesIds": [1, 2, 3]
}
```

## Endpoints de processamento

```http
GET /api/{data}

GET /api/integrante-mais-usado

GET /api/integrantes-do-time-mais-recorrente

GET /api/funcao-mais-recorrente

GET /api/clube-mais-recorrente

GET /api/contagem-de-clubes

GET /api/contagem-por-funcao
```

Os endpoints de processamento recebem `dataInicial` e `dataFinal` quando necessário e delegam o processamento para o `ApiService`.

---

# Banco de dados

Foi adicionado o **H2 Database** como banco em memória para facilitar a execução da aplicação.

Dessa forma, não é necessário configurar previamente um banco externo para executar o projeto localmente.

O projeto também possui suporte ao MySQL através do respectivo Connector, permitindo alterar a configuração do datasource caso seja necessário utilizar um banco persistente.

---

# Swagger / OpenAPI

Foi adicionada a documentação da API utilizando **SpringDoc OpenAPI**, permitindo visualizar os endpoints disponíveis e realizar testes das requisições através do Swagger.

---

# Como executar o projeto

## Backend

Entre na pasta do backend:

```bash
cd backend
```

Execute a aplicação utilizando o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

O backend será executado na porta:

```text
http://localhost:8080
```

### Executar os testes

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

---

## Frontend

Entre na pasta do frontend:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Execute a aplicação:

```bash
ng serve
```

O frontend será executado na porta:

```text
http://localhost:4200
```

Com as duas aplicações em execução:

```text
Frontend
localhost:4200
      │
      │ HTTP / REST
      ↓
Backend
localhost:8080
      │
      ↓
Database
```

Dessa forma, é possível utilizar a interface Angular para realizar as operações disponíveis na API.

---

# Principais decisões técnicas

Durante o desenvolvimento, foram realizadas algumas alterações e decisões sobre o código-base recebido:

### Java 8 para Java 17

O projeto original utilizava Java 8. A configuração foi atualizada para Java 17, utilizando uma versão mais atual da linguagem.

### Utilização do Lombok

O Lombok foi adicionado para reduzir código repetitivo e facilitar a implementação das classes.

### Utilização do H2

O H2 foi adicionado como banco em memória para simplificar a execução local do projeto e permitir testes sem depender de uma instalação externa de banco de dados.

### Arquitetura MVC

A aplicação foi organizada seguindo o padrão MVC e separando responsabilidades entre Controller, Service, Repository e Model.

Também foram utilizados DTOs e Mappers para manter uma separação clara entre os objetos da API e as entidades de persistência.

### Mapper

Os Mappers foram utilizados para centralizar a conversão entre DTOs e entidades, deixando os Services mais focados nas regras de negócio e no processamento da aplicação.

### Processamento em Java

Os dados são recuperados através dos Repositories e processados na aplicação utilizando recursos da linguagem Java, como `List`, `Map`, `Set` e Streams.

### Filtro por período

Foi criada uma lógica reutilizável para filtrar os dados por período, permitindo trabalhar com datas opcionais.

### Testes adicionais

Foram adicionados testes para validar diferentes cenários do filtro por período, incluindo situações em que `dataInicial` ou `dataFinal` não são informadas.

### Integração Frontend e Backend

Foi configurada a comunicação entre o Angular e o Spring Boot, com o frontend executando na porta `4200` e o backend na porta `8080`, incluindo a configuração necessária de CORS.

---

# Organização do projeto

```text
desafioDX-Programador/
│
├── backend/
│   └── Spring Boot + Java
│
└── frontend/
    └── Angular
```

O backend concentra a API, persistência e regras de negócio, enquanto o frontend fornece a interface para interação com a aplicação.

---

# Considerações finais

A solução foi desenvolvida aproveitando o código-base disponibilizado no desafio e evoluindo sua estrutura de acordo com as necessidades da implementação.

O foco foi construir uma aplicação organizada, com separação de responsabilidades, processamento dos dados em Java, persistência, testes unitários e integração entre frontend e backend.

Para executar a solução completa, basta iniciar o backend com:

```bash
./mvnw spring-boot:run
```

e, em outro terminal, iniciar o frontend com:

```bash
ng serve
```

Com isso, o backend estará disponível em `localhost:8080` e o frontend em `localhost:4200`.
