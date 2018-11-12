# SpringBoot+Kotlin
### Introduction
This is a demo project for develop springboot by kotlin.  
The project build by `Maven`. 

### Note
Because i like using modules artifact, so there has these modules: panda-base, panda-dao, panda-service, panda-web,
I think you should understand them.

1. One thing is i put my `Application.kt` into package `io.github.shenbinglife.panda.web`, so that I had to config `scanBasePackages` On `@SpringBootApplication` exactly.
2. Because i put `@JsonIgnore` on User.password getter method, my `UserControllerTest` has to use Gson to convert the user object to JSON string for the test, or Jackson util can not keep the password in JSON string.  
   Using postman to call create user URL, you can exactly save the User.password to database, which not return the password to response body.  
3. I set the context path `/panda`, so the example RESTful URL is `http://localhost:8080/panda/users`.

If you have any question, please make an issue.

### Dependency Versions 
```
java8
springboot:2.1.0.RELEASE
kotlin:1.3.0
```

### How to Run
1. git clone the project
2. import the project to an IDE like idea or eclipse
3. config the `application.properties` in panda-web module, change the mysql url
4. run the main method of `Application.kt`