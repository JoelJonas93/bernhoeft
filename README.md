<h1 align="center">
  <a href="https://github.com/JoelJonas93/bernhoeft/">
    CRUD Back-end
  </a>
</h1>

## Tecnologias usadas

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [Flyway](https://flywaydb.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Mockito](https://site.mockito.org/)

## Desenvolvimento

Para obter uma cópia local do código, clone-o usando git:

```
git clone https://github.com/JoelJonas93/bernhoeft
```
Importe o projeto como um projeto maven e em seguida atualize o maven pela IDE ou no cmd pelo comando:

```
mvn clean install -U
```

Caso não tenha o lombok instalado na IDE, instalar de acordo com a IDE que está usando.

Para executar o projeto rodar a classe BernhoeftApplication. Será executado localmente na porta padrão 8080.

## Nota
Nos controllers do projeto foi configurado o CrossOrigin da seguinte forma de acordo com a url que o front está rodando @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600).
Primeiro projeto usando TDD de acordo como foi pedido. Através dos commits verá que foi feito o teste e depois implementado a lógica com refatoração.
Configurar o banco de dados de acordo com as informações no arquivo application.properties.
