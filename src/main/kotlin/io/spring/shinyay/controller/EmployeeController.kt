package io.spring.shinyay.controller

import io.spring.shinyay.entity.Employee
import io.spring.shinyay.repository.EmployeeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

    @GetMapping("/hello-flux")
    fun helloFlux() = Flux.just("Hello","Flux","at",
        ZonedDateTime.now(
            ZoneId.of("Japan")
        ).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        ))

    @GetMapping("/employees")
    fun getAllEmployees(): Flux<Employee> {
        return repository.findAllEmployees()
    }
}
