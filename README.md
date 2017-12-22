# security

[![Build Status](https://travis-ci.org/springbootbuch/security.svg?branch=master)](https://travis-ci.org/springbootbuch/security)

## Profils

Use custom `UserDetailsService` with plain passwords.

```
./mvnw spring-boot:run  -Dspring-boot.run.profiles=custom-userdetailsservice
curl -u Michael:test http://localhost:8080/api/greeting
```

Use custom `UserDetailsService` with bcrypt passwords.

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=custom-userdetailsservice,use-bcrypt-password-hash
curl -u Michael:bcrypted http://localhost:8080/api/greeting
```

Demonstrates custom http security

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=custom-http-security
curl http://localhost:8080/api/greeting
curl -u Michael:test  http://localhost:8080/api/admin/user
```

Login via Google OAuth 2 (Please register an App via [Googles API Manager](https://console.developers.google.com/apis/credentials) and enter its ID and secret to `application-oauth-sso.properties`)

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=oauth-sso
```

Provide OAuth  Authorization and Resource Server

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=oauth
```

## A word about OAuth

This example project was created during the work on [Spring Boot Buch](https://springbootbuch.de). It started with Spring Security 4 and is now on 5. 

While Spring Boot 1 had a managed version of the OAuth module, Spring Boot 2 doesnt and probably doesn't need it in the future as Spring Security 5 will have that stuff somewhen in its core.

In the meantime there's a project called [OAuth2 Autoconfig](https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/htmlsingle/) for smoothing a migration.

It's used in this example.
