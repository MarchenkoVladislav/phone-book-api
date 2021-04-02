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
-   Spring Test

## Usage
### How to run this API
1.  Create some database in Postgres.
2.  In file `src/main/resources/application.properties` change properties `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password` values to your own.
3.  Build and run application with Intellij IDEA (you will also need Lombok plugin).

After running the application, you can test this API using any application that allows you to send HTTP requests. For example, you can use [Postman](https://www.postman.com/downloads/).
### Functionality
-   `Get all regions`: 
    -   Type: GET
    -   URL: `http://localhost:9090/regions/get/all`
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "name": "Murmansk region",
        "abbreviatedName": "Mur"
    },
    {
        "id": 2,
        "name": "Leningrad region",
        "abbreviatedName": "Len"
    },
    {
        "id": 3,
        "name": "Vologda region",
        "abbreviatedName": "Vol"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404)
-   `Get region by id`: 
    -   Type: GET
    -   URL: `http://localhost:9090/regions/get/{id}`
    -   Response body (example):
    ```
    {
        "id": 1,
        "name": "Murmansk region",
        "abbreviatedName": "Mur"
    }
    ```
    -   Statuses: OK(200), NOT FOUND(404)
-   `Save region`:
    -   Type: POST
    -   URL: `http://localhost:9090/regions/save`
    -   Request body (example):
    ```
    {
        "name" : "test",
        "abbreviatedName" : "t"
    }
    ```
    -   Response body (example):
    ```
    {
        "id": 1,
        "name": "test",
        "abbreviatedName": "t"
    }
    ```
    -   Statuses: CREATED(201), INTERNAL SERVER ERROR(500), FORBIDDEN(403)
-   `Get all regions in sort order by field of region and sort order`:
    -   Type: POST
    -   URL: `http://localhost:9090/regions/get/sort`
    -   Request body:
    ```
    {
    "sortedField": "NAME" or "ABBREVIATED_NAME",
    "sortOrder": "DESC" or "ASC"
    }
    ```
    -   Response body (example):
    ```
    [
    {
        "id": 1,
        "name": "Murmansk region",
        "abbreviatedName": "Mur"
    },
    {
        "id": 2,
        "name": "Leningrad region",
        "abbreviatedName": "Len"
    },
    {
        "id": 3,
        "name": "Vologda region",
        "abbreviatedName": "Vol"
    }
    ]
    ```
    -   Statuses: OK(200), NOT FOUND(404)
    
-   `Update region`: 
    -   Type: PUT
    -   URL: `http://localhost:9090/regions/update`
    -   Request body (example):
    ```
    {
        "id": 1
        "name" : "test",
        "abbreviatedName" : "t"
    }
    ```
    -   Response body (example):
    ```
    {
        "id": 1,
        "name": "test",
        "abbreviatedName": "t"
    }
    ```
    -   Statuses: OK(200), NOT MODIFIED(304)

-   `Delete region by id`: 
    -   Type: DELETE
    -   URL: `http://localhost:9090/regions/delete/{id}`
    -   Response body (example):
    ```
    {
        "id": 1,
        "name": "test",
        "abbreviatedName": "t"
    }
    ```
    -   Statuses: OK(200), NOT FOUND(404)
    
## Tests
You can run Unit tests with Maven. You should use command `mvn clean test` when you are at the root of project.

## Maintainer
Vladislav Marchenko

## License
This project is licenced under the terms of the [MIT](LICENSE) license.
