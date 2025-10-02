# Spring_Atividade_Aluno

Este projeto é uma API REST desenvolvida com **Spring Boot** para gerenciamento de uma instituição de ensino, permitindo o controle de **alunos, professores, disciplinas, matrículas e históricos escolares**.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- **PostgreSQL**
- Swagger (OpenAPI)
- Maven

## Estrutura do Projeto

src/

└── main/

├── java/

    └── alunoonline.alunoonline/
    
        ├── config/
    
        ├── controller/
    
        ├── dtos/
    
        ├── enums/
    
        ├── model/
    
        ├── repository/
    
        └── service/
└── resources/

## Funcionalidades da API

- 📘 CRUD de Alunos
- 🧑‍🏫 CRUD de Professores
- 📚 CRUD de Disciplinas
- 📥 Matrícula de Alunos
- 📝 Atualização de notas
- 📄 Histórico escolar
- 🌐 Integração com Swagger UI

---

## Documentação da API

A documentação da API pode ser acessada via Swagger após rodar o projeto:

http://localhost:8080/swagger-ui/index.html

## Testes com Postman

O arquivo de testes para o Postman está incluído na raiz do projeto com o nome:

[Alunos_Casa.postman_collection.json](Alunos_Casa.postman_collection.json)


Você pode importá-lo diretamente no Postman para testar todos os endpoints da API.

## Banco de Dados (PostgreSQL)

O dump (export) do banco de dados PostgreSQL está incluído na raiz com o nome:

[dump-aluno_online_casa-202505270019.sql](dump-aluno_online_casa-202505270019.sql)

------------------------------------

## Como rodar o projeto localmente
Pré-requisitos:
Java 17+
Maven
PostgreSQL

# 1. Clone o repositório
git clone https://github.com/seu-usuario/Spring_Atividade_Aluno.git

# 2. Acesse a pasta do projeto
cd Spring_Atividade_Aluno

# 3. Configure o banco de dados em application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/seubanco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# 4. Execute a aplicação
./mvnw spring-boot:run

Depois de iniciado, acesse:
http://localhost:8080

Desenvolvido por:
Samuel Lorand.



# Spring_Atividade_Aluno

Postman criarAluno:
![Captura de tela 2025-04-01 150520](https://github.com/user-attachments/assets/0b63d6dc-c022-4ed5-85bf-221dd84d66aa)

DBeaver criarAluno:
![Desktop Screenshot 2025 04 01 - 15 04 24 49](https://github.com/user-attachments/assets/e2fe8a29-db3c-4885-8be2-6e289e30c7e8)

Postman listarAlunos:
![image](https://github.com/user-attachments/assets/a5d20cf1-529b-4c8c-82ee-1bd465226d25)

DBeaver listarAlunos:
![image](https://github.com/user-attachments/assets/fbe46d49-157d-4207-a714-07553cb6adc3)

Postman buscarAlunoPorId:
![image](https://github.com/user-attachments/assets/0201a13f-c289-410e-8708-0a9fae390d57)

Postman deletarAlunoPorId:
![image](https://github.com/user-attachments/assets/63cbd953-9e2d-44f5-b8a3-1da75ff6bb00)

DBeaver aluno deletado:
![image](https://github.com/user-attachments/assets/863a3f29-da5d-4361-9ee3-c788b3defca3)

Postman criarDisciplina:
![image](https://github.com/user-attachments/assets/64f6444b-f992-4499-8cba-e9ccb0326fd9)

DBeaver criarDisciplina:
![image](https://github.com/user-attachments/assets/abd1a6d5-0e1b-4dcb-a50a-de5314d537fa)

Postman listarDiscipinas:
![image](https://github.com/user-attachments/assets/a732b621-f2a6-4bda-8c93-b3b7f242d9a9)

Postman listarDisciplinasPorId:
![image](https://github.com/user-attachments/assets/a895afe2-d238-4e2a-b139-5b8ff4b8bf42)

Postman deletarDisciplinaPorId:
![image](https://github.com/user-attachments/assets/ac2d1a78-bab5-4dda-94bc-17fff822976b)

DBeaver deletarDisciplinaPorId:
![image](https://github.com/user-attachments/assets/494ce49c-1b86-4414-b798-951937a6d0b5)

Postman atualizarDisciplinaPorId:
![image](https://github.com/user-attachments/assets/3fbdf1ba-c884-4a6d-9772-7c51a0838e47)

DBeaver atualizarDisciplinaPorId:
![image](https://github.com/user-attachments/assets/8b29fe1b-fe68-457d-9ef3-a8c4c91f4d00)

Swagger trancarMatricula
![image](https://github.com/user-attachments/assets/b3fbfb7c-4318-4d6e-b207-5983f56129ac)

