# estante-critica-api

![GitHub repo size](https://img.shields.io/github/repo-size/valfrido-dev/estante-critica-api?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/valfrido-dev/estante-critica-api?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/valfrido-dev/estante-critica-api?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/valfrido-dev/estante-critica-api?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/valfrido-dev/estante-critica-api?style=for-the-badge)

Projeto para conclusão de curso (desenvolvimento full stack). 
O projeto é uma API para avaliação e indicações de livros para leitura.
Este projeto possui uma base de dados com livros avaliados e suas sinopse, os livros serão ordenados
pelas melhores avaliações e o usuário poderá criar uma lista de livros de interesse para leitura.

## Skills
![Static Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Static Badge](https://img.shields.io/badge/maven-2496ED?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Static Badge](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
![Static Badge](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)


## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- É necessário `<Java / JDK21 / Docker>`
- Para `<Windows>` é necessário instalar o `<WSL>` para que seja instalado e executado o docker.

## 🚀 Instalando estante-critica-api

Para instalar o estante-critica-api, siga estas etapas:

- Criar um arqvuivo `<.env>`no diretório `</docker>` para definir as variáveis de ambiente:

```
JWT_SECRET_API: <your-secret-key>
MONGODB_ROOT_USERNAME: <your-user-db>
MONGODB_ROOT_PASSWORD: <your-password-user-db>
MONGODB_URI: mongodb://<your-user-db>:<your-password-user-db>@mongodb:27017/estantedb?authSource=admin
ADMIN_APP_PASSWORD: <your-admin-default-password>
EMAIL_ADMIN_APP: <your-admin-default-email>
```
- Executar os comandos abaixo no diretório `<docker>`

```
<docker compose build>
```
Após sucesso do comando anterior executar:
```
<docker compose up>
```

## ☕ Usando estante-critica-api

Para usar estante-critica-api, pode se utilizar o projeto frontend em Angular [ng-estante-critica](https://github.com/valfrido-dev/ng-estante-critica)
Ou utilizar uma ferramenta de testes de API para interagir com o projeto, como o `<postman>`.

- Os endpoints de cadastro de novo usuário e de login estão abertos, sem a necessidade autenticação, 
os restantes dos endpoints nessicitando de autenticação.
- A autentição é realizada através do endpoint `</api/users/user/login>` informando usuário e senha,
a autenticação retorna o token de autorização.
- O usuário administrador padrão é o adminApp, com senha definida na variável de ambiente `<ADMIN_APP_PASSWORD>`
