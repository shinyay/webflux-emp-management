# Spring WebFlux Getting Started

This application is a simple example of a Spring Boot application using WebFlux.

## Description
### Dependencies
- org.springframework.boot
  - `spring-boot-starter-data-r2dbc`
  - `spring-boot-starter-webflux`

## Demo
### Retrieve all employees
```shell
$ curl -X GET http://localhost:8080/api/v1/employees
```

### Create an employee
```shell
$ curl -X POST localhost:8080/api/v1/employees -H 'Content-type:application/json' -d '{"employee_id":0,"department_id": 10, "name": "Alice", "role": "Dev"}'
```

### Update an employee
```shell
$ curl -X PUT localhost:8080/api/v1/employees/1 -H 'Content-type:application/json' -d '{"employee_id":1,"department_id": 10, "name": "Alice", "role": "Dev"}'
```

### Delete an employee
```shell
$ curl -X DELETE localhost:8080/api/v1/employees/1
```

## Features

- feature:1
- feature:2

## Requirement

## Usage

## Installation

## References

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
- twitter: https://twitter.com/yanashin18618
