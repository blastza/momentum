# momentum
Automating the withdrawal process for its investors

# Swagger ui
http://localhost:8081/swagger-ui/index.html

# Endpoint
To access all secured endpoint, you need to acquire a bearer token using.
NB the credentials used on to accquire the accessToken is store on the db when the application start.
You can add your own, make sure the password is Bcrypt encoded.

Todo : user register endpoint 

localhost:8081/auth/login
```
{
    "email" : "{email address that is saved inside the database}",
    "password" : "{password}"
}
```

```
Todo:
* refactor error validation properly and exception handling
* adding send mail for notification on the process
```
