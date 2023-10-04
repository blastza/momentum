# momentum
Automating the withdrawal process for its investors

# Service endpoints
- Login Request
```
  localhost:8081/auth/login
  {
    "email" : "lutendo@momentum.com",
    "password" : "******"
  }
  ```

- retrieve all investor details
```
  localhost:8081/api/v1/investment/investor-details
  Authorization
  Bearer Token
```

- retrieve all investor details by email

```
localhost:8081/api/v1/investment/investor/products/{email}
  Authorization
  Bearer Token

```

- retrieve all linked products

```
localhost:8081/api/v1/investment/investor/products/admin@momentum.com
  Authorization
  Bearer Token

```


# Swagger ui
http://localhost:8081/swagger-ui/index.html
