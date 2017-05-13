# security

[![Build Status](https://travis-ci.org/springbootbuch/security.svg?branch=master)](https://travis-ci.org/springbootbuch/security)

## Profils

Use custom `UserDetailsService` with plain passwords.

```
./mvnw spring-boot:run  -Drun.profiles=custom-userdetailsservice
curl -u Michael:test http://localhost:8080/api/greeting
```

Use custom `UserDetailsService` with bcrypt passwords.

```
./mvnw spring-boot:run -Drun.profiles=custom-userdetailsservice,use-bcrypt-password-hash
curl -u Michael:bcrypted http://localhost:8080/api/greeting
```

Demonstrates custom http security

```
./mvnw spring-boot:run -Drun.profiles=custom-http-security
curl http://localhost:8080/api/greeting
curl -u Michael:test  http://localhost:8080/api/admin/user
```