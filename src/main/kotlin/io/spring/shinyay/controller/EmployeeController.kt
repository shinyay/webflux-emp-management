package io.spring.shinyay.controller

import io.spring.shinyay.entity.Employee
import io.spring.shinyay.repository.EmployeeRepository
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

    @GetMapping("/hello-mono")
    fun helloMono() = Mono.just("Hello Mono")

    @Bean
    fun routeHelloFlux(): RouterFunction<ServerResponse?>? {
        return route(GET("/hello-flux")) { this.helloFlux() }
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
        return repository.findAllEmployees()
    }
}
