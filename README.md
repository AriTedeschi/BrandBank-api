# BrandBanking API

An api which allows trasactions crediting ou debiting user's balance

## :books: Features:
* <b>User's Registration</b>: Allows users registration. Payload example:
```
{
  "email": "test@gmail.com",
  "name": "John",
  "password": "123",
  "age": 23,
  "address": "Street alamedas"
}
```
The user's account code is generated automatically.

* <b>Post Transactions</b>: allows users to move their balance by launching transactions, crediting or debiting the balance.
```
POST /transactions/debt/{value}
i.e.
/transactions/debt/1.23
```

```
POST /transactions/credt/{value}
i.e.
/transactions/credt/8.17
```

* <b>Check users transactions</b>: allows users to consult all transactions carried out in a paged format.
```
GET /transactions?{paginationParameters}
i.e.
/transactions?page=0&linesPerPage=10&direction=DESC&orderBy=created_at
```

* <b>Swagger</b>: allows to see all API's endpoints, allowing to an authentication token to test and see the expected payloads from each endpoint.
```
Enter the url:
http://localhost:8080/swagger-ui/index.html
```
* <b>Authentication & Authorization</b>: The API implemented JWT authentication, all endpoints require a JWT except

`/users/register` : Registration endpoint

`/login` : Authentication endpoint

`/swagger-ui/index.html` : Swagger endpoint

Authentication payload:
```
{
  "accountCode": "12760.00",
  "password": "123"
}
```

More details about autenticated routes at **[SecurityConfig.java](https://github.com/AriTedeschi/BrandBank-api/blob/main/src/main/java/com/brandbank/transactions/application/configuration/SecurityConfig.java):**

## :clipboard: Backlog

The project uses Github Projects as a development board to save all features on **[Backlog](https://github.com/users/AriTedeschi/projects/1):**


## :computer: Entity relationship diagrams

```mermaid
erDiagram
    TB_USER {
        String id PK,
        String accountCode,
        String password,
        Integer role,
        String email,
        String name,
        Integer age,
        String address,
        Datetime createdAt
    }
    
    TB_CUSTODY {
        String id PK,
        BigDecimal balance,
        BigDecimal balance_limit,
        String user_id FK
    }
    
    TB_TRANSACTIONY {
        String id PK,
        Integer movement,
        BigDecimal movementValue,
        Datetime createdAt,
        String custody_id FK
    }
    
    TB_USER ||--|| TB_CUSTODY : has
    TB_CUSTODY ||--o{ TB_TRANSACTIONY : has
```

## 🔧 Dependencies
The project requires Maven, Java 17 and a Postgrees database to run the projects
Thus, the projects uses those enviroment keys:
```
JWT_SECRET=;
SPRING_DATASOURCE_IP=<ip>:<port>
SPRING_DATASOURCE_DBNAME=/brandbank;
SPRING_DATASOURCE_SCHEAMA=?currentSchema=public;
SPRING_DATASOURCE_USERNAME=;
SPRING_DATASOURCE_PASSWORD=;
```

The project provides a "dev" execution profile that uses the H2 in-memory database, in this profile it is not necessary to perform any connection configuration

To use the dev profile, change the **[application.properties](https://github.com/AriTedeschi/BrandBank-api/blob/main/src/main/resources/application.properties):** file as follows
```
spring.profiles.active=dev
```

## 🚀 Running

The api provides an docker-compose.yml which allows a quick setup for a postgres db.

Once configured the database, in order to generate projects's binaries run the following command on projects's root:

```
mvn install
```

Once running go to Swagger-ui endpoint to use
**[Swagger-ui](http://localhost:8080/swagger-ui/index.html):**

