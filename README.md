# Spring WebFlux Getting Started

This application is a simple example of a Spring Boot application using WebFlux.

## Description
### Dependencies
- org.springframework.boot
  - `spring-boot-starter-data-r2dbc`
  - `spring-boot-starter-webflux`
- H2 Database
  - `com.h2database:h2`
  - `io.r2dbc:r2dbc-h2`

### Databasd Schema
Department Table
```sql
DROP TABLE IF EXISTS department;
CREATE TABLE department (
                            department_id decimal(4,0) NOT NULL AUTO_INCREMENT,
                            department_name varchar(14) DEFAULT NULL,
                            PRIMARY KEY (department_id)
);
```

Employee Table
```sql
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
                          employee_id decimal(4,0) NOT NULL AUTO_INCREMENT,
                          department_id decimal(4,0),
                          name varchar(64) NOT NULL,
                          role varchar(32) DEFAULT NULL,
                          PRIMARY KEY (employee_id)
);

ALTER TABLE employee ADD FOREIGN KEY (department_id) REFERENCES department (department_id);
```

### Entity
Define the second constructor parameter since primary key is generated by database.

```kotlin
data class Employee(
    @Id
    var employee_id: Long,
      : 
) {
    constructor(department_id: Long, name: String, role: String) : this(0, department_id, name, role)
}
```

### Repository
The repository interface extends ReactiveCrudRepository.

```kotlin
interface EmployeeRepository : ReactiveCrudRepository<Employee, Long>
```

You can add a custom method to the repository interface with `@Query` annotation.

### Controller
#### Annotated Controller
```kotlin
@GetMapping("/employees")
fun getAllEmployees(): Flux<Employee> {
    return repository.findAll()
}
```

#### Functional Endpoint
`Handler function` is a function that returns a Mono.

```kotlin
  fun findAllHandler(request: ServerRequest): Mono<ServerResponse> {
      val result: Flux<Employee> = repository.findAll()
      return ServerResponse.ok().body(result, Employee::class.java)
  }
```

Router function is a function that returns a RouterFunction.
```kotlin
@Bean
fun router(): RouterFunction<ServerResponse> {
    return route()
            .GET("/api/endpoint", this::handlerFuntion)
            .build()
    }
```

### Mono and Flux
- `Mono` is a reactive type that represents a single value.
  - `Mono.just(value)`
  - `Mono.flatMap(function)`
  - `Mono.flatMapMany(function)`
  - `Mono.retry()`
- `Flux` is a reactive type that represents a stream of values.
  - `Flux.just(value)`
  - `Flux.fromIterable(list)`
  - `Flux.fromArray(array)`
  - `Flux.fromStream(stream)`

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
