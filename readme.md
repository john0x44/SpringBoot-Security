# Spring Boot Authentication and Authorization

This repository demonstrates an authentication and authorization implementation using Spring Boot and Spring Security. The application provides endpoints for user registration, login, and role-based access control. Let's delve into each file and understand its role in the application.

## Project Architecture

The project follows a modular architecture with distinct packages for various functionalities:

1. `controllers`: Contains controller classes responsible for handling incoming HTTP requests and defining the REST endpoints.

2. `models`: Contains the model classes that represent the application entities.

3. `repository`: Contains repository interfaces for interacting with the database.

4. `services`: Contains service classes that handle business logic and data processing.

5. `utils`: Contains utility classes, in this case, for generating RSA keys.

6. `BackendAuthenticationApplication.java`: The main entry point of the Spring Boot application.

## Key Features

### User Registration and Login

#### `AuthenticationController.java`

This file contains the `AuthenticationController` class, which is responsible for handling authentication-related endpoints. The `/auth/register` endpoint allows users to register with a new username and password. When a user sends registration data, the `AuthenticationService` is invoked to encrypt the password using `BCryptPasswordEncoder`, and the new user is saved to the database using the `UserRepository`. The registered user is then returned in the response.

![Register](https://i.gyazo.com/cf900536785ea2dbffcdc9f220e96886.png)

The response displays the received data such as:
- `userId`
- `username`
- Encrypted `password`
- `authorities` (the role of the user)

#### `AuthenticationService.java`

The `AuthenticationService` class is a service responsible for handling user registration and login. It interacts with the `UserRepository` and `RoleRepository` to save and retrieve user and role data. The `BCryptPasswordEncoder` is used to securely encrypt passwords. For login, the `AuthenticationManager` attempts to authenticate the user with the provided credentials, and a JWT token is generated using the `TokenService` upon successful authentication.

![Login](https://i.gyazo.com/4052775fb0adc3d3b287ab8bbea21dca.png)

The response displays the received JWT token that can be used for subsequent logins.

### Role-Based Access Control

#### `SecurityConfiguration.java`

The `SecurityConfiguration` class is a Spring Security configuration class that defines access rules and security policies for different endpoints. It specifies that the `/auth/**` endpoint is accessible to all users, the `/admin/**` endpoint requires the "ADMIN" role, and the `/user/**` endpoint requires either "ADMIN" or "USER" role. The class also configures JWT-based authentication using `JwtDecoder`, `JwtEncoder`, and `JwtAuthenticationConverter`.

#### `Role.java`

This model class represents the roles (authorities) that can be assigned to users. It implements the `GrantedAuthority` interface, which is required for role-based access control. The `RoleRepository` is used to interact with the database and retrieve role data.

#### `UserDetailsServiceImpl.java`

The `UserDetailsServiceImpl` class is a service that implements the `UserDetailsService` interface provided by Spring Security. It loads user-specific data from the `UserRepository` based on the provided username during authentication.

#### `TokenService.java`

The `TokenService` class handles the generation of JWT tokens upon successful authentication. It uses the `JwtEncoder` and `JwtDecoder` to create and verify JWT tokens with the appropriate claims.

### Utility and Main Entry Point

#### `KeyGeneratorUtility.java`

The `KeyGeneratorUtility` class provides a static method to generate an RSA key pair, which is used in `SecurityConfiguration` for signing and verifying JWT tokens.

#### `RSAKeyProperties.java`

The `RSAKeyProperties` class is a utility component that generates and holds the RSA key pair (public and private keys) using `KeyGeneratorUtility`. The `publicKey` and `privateKey` are used in the creation of `JwtDecoder` and `JwtEncoder` beans.

#### `BackendAuthenticationApplication.java`

This class is the main entry point of the Spring Boot application. It starts the application and implements a `CommandLineRunner` bean that is executed upon application startup. The `CommandLineRunner` bean initializes the database with an admin role and an admin user if they don't already exist.

## Conclusion

This Spring Boot application demonstrates secure user authentication and authorization using Spring Security. It showcases best practices for handling user data, encrypting passwords, and role-based access control with JWT-based authentication. The modular architecture ensures maintainability and scalability of the application. This project can serve as a practical example for implementing similar features in various Spring Boot applications.

## Notes

By following this project, I have learned how to design and implement user authentication and authorization functionality in a Spring Boot application. Additionally, I have gained knowledge of best practices for securing user data, handling passwords, and managing user roles and permissions. This project has provided valuable hands-on experience in building secure and robust backend services for web applications.

