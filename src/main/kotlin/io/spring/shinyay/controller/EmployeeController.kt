package io.spring.shinyay.controller

import io.spring.shinyay.entity.Employee
import io.spring.shinyay.repository.EmployeeRepository
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunctions.route
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/api/v1")
class EmployeeController(val repository: EmployeeRepository) {

    // Annotation Controller Model
    @GetMapping("/hello-mono")
    fun helloMono() = Mono.just("Hello Mono")

    // Router Function Model
    @Bean
    fun routeHelloFlux(): RouterFunction<ServerResponse?>? {
        return route(GET("/api/v1/hello-flux")) { this.helloFlux() }
    }

    fun helloFlux(): Mono<ServerResponse?> {
        return ServerResponse.ok().body(
            Flux.just(
                "Hello",
                "Flux",
                "at",
                ZonedDateTime
                    .now(
                        ZoneId.of("Japan")
                    ).format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                    )
            ), String::class.java
        )
    }

    @GetMapping("/employees")
    fun getAllEmployees(): Flux<Employee> {
        return repository.findAll()
    }

    @GetMapping("/employees/{id}")
    fun getEmployeeById(@PathVariable id: Long): Mono<Employee> {
        return repository.findById(id)
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    fun createEmployee(@RequestBody employee: Employee): Mono<Employee> {
        return repository.save(employee)
    }

    @PutMapping("/employees/{id}")
    fun updateEmployee(@PathVariable id: Long, @RequestBody employee: Employee): Mono<Employee> {
        return repository.findById(id).flatMap {
            employee.employee_id = id
            repository.save(employee)
        }
    }

    @DeleteMapping("/employees/{id}")
    fun deleteEmployee(@PathVariable id: Long): Mono<Void> {
        return repository.deleteById(id)
    }
}
