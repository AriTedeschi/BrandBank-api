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


## 🔧 Dependencies
You need Java 17 and a Postgrees database to run the projects
Thus, the projects uses those enviroment keys:

```
JWT_SECRET=;
SPRING_DATASOURCE_DBNAME=//<ip>:<port>/brandbank?currentSchema=public;
SPRING_DATASOURCE_USERNAME=;
SPRING_DATASOURCE_PASSWORD=;
```

## 🚀 Running

TODO
