# phone-book-api
[![Language](http://img.shields.io/badge/language-java-brightgreen.svg)](https://www.java.com/)
[![License](http://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/samtools/PolinaBevad/bio_relatives)

REST-API for phone books management

## Table of Contents
-   [Used technologies](#used-technologies)
-   [Usage](#usage)
    -   [How to run this API](#how-to-run-this-api)
    -   [Functionality](#functionality)
-   [Tests](#tests)
-   [Maintainer](#maintainer)
-   [License](#license)

## Used technologies
-   Java 11
-   Spring Boot
-   Spring Web
-   Spring Cache
-   Spring Data Jpa
-   H2
-   Postgres
-   Lombok
-   Liquibase
-   Spring Test

## Usage
### How to run this API
1.  Create some database in Postgres (you need to create only DB, tables will create automaticaly with liquibase after running the app).
2.  In file `src/main/resources/application.properties` change properties `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password` values to your own.
3.  Build and run application with Intellij IDEA (you will also need Lombok plugin).

After running the application, you can test this API using any application that allows you to send HTTP requests. For example, you can use [Postman](https://www.postman.com/downloads/).
### Functionality
-   `Get all users`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/users`
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "name": "Anton"
    },
    {
        "id": 2,
        "name": "Lena"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404)
   
-   `Get user by id`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/users/{id}`
    -   Response body (example):
    ```
    {
        "id": 1,
        "name": "Anton"
    }
    ```
    -   Statuses: OK(200), NOT FOUND(404)
   
-   `Save user`:
    -   Type: POST
    -   URL: `http://localhost:11000/api/users`
    -   Request body (example):
    ```
    {
        "name" : "Egor"
    }
    ```
    -   Response body (example):
    ```
    {
        "id": 1,
        "name" : "Egor"
    }
    ```
    -   Statuses: CREATED(201), INTERNAL SERVER ERROR(500), BAD_REQUEST(400) 
     
-   `Update user`: 
    -   Type: PUT
    -   URL: `http://localhost:11000/api/users`
    -   Request body (example):
    ```
    {
        "id": 1
        "name" : "Misha"
    }
    ```
    -   Response body (example):
    ```
    {
        "id": 1,
        "name" : "Misha"
    }
    ```
    -   Statuses: OK(200), NOT_FOUND(404), INTERNAL SERVER ERROR(500), BAD_REQUEST(400)  

-   `Delete user by id`: 
    -   Type: DELETE
    -   URL: `http://localhost:11000/api/users/{id}`
    -   Statuses: OK(200), NOT FOUND(404)
   
-   `Get users by name`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/users/name?name={name}`
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "name": "Anton"
    },
    {
        "id": 2,
        "name": "Anton"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404), BAD_REQUEST(400)

-   `Get all phonebook records`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/records`
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    },
    {
        "id": 2,
        "ownerId": 2,
        "title": "dad",
        "phoneNumber": "123-455"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404)
   
-   `Get phonebook record by id`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/records/{id}`
    -   Response body (example):
    ```
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    }
    ```
    -   Statuses: OK(200), NOT FOUND(404)
   
-   `Save phonebook record`:
    -   Type: POST
    -   URL: `http://localhost:11000/api/records`
    -   Request body (example):
    ```
    {
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    }
    ```
    -   Response body (example):
    ```
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    }
    ```
    -   Statuses: CREATED(201), INTERNAL SERVER ERROR(500), BAD_REQUEST(400) 
     
-   `Update phonebook record`: 
    -   Type: PUT
    -   URL: `http://localhost:11000/api/records`
    -   Request body (example):
    ```
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    }
    ```
    -   Response body (example):
    ```
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    }
    ```
    -   Statuses: OK(200), NOT_FOUND(404), INTERNAL SERVER ERROR(500), BAD_REQUEST(400)  

-   `Delete phonebook record by id`: 
    -   Type: DELETE
    -   URL: `http://localhost:11000/api/records/{id}`
    -   Statuses: OK(200), NOT FOUND(404)
   
-   `Get phonebook records by ownerId`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/records/ownerId?ownerId={ownerId}`
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    },
    {
        "id": 2,
        "ownerId": 1,
        "title": "dad",
        "phoneNumber": "123-455"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404), BAD_REQUEST(400)
   
-   `Get phonebook records by ownerId and title`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/records/ownerIdAndTitle?ownerId={ownerId}&title={title}`
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404), BAD_REQUEST(400)
-   `Get phonebook records by ownerId and phone`: 
    -   Type: GET
    -   URL: `http://localhost:11000/api/records/ownerIdAndPhone?ownerId={ownerId}&phoneNumber={phoneNumber}`
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "ownerId": 1,
        "title": "mom",
        "phoneNumber": "123-456"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404), BAD_REQUEST(400)
    
## Tests
You can run Unit tests with Maven. You should use command `mvn clean test` when you are at the root of project.

## Maintainer
Vladislav Marchenko

## License
This project is licenced under the terms of the [MIT](LICENSE) license.
