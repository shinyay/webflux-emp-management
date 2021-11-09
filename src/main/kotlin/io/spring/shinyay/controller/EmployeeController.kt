package io.spring.shinyay.controller

import io.spring.shinyay.entity.Employee
import io.spring.shinyay.repository.EmployeeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1")
class EmployeeController(val repository: EmployeeRepository) {

    @GetMapping("/hello")
    fun helloMono () = Mono.just("Hello Mono")

    @GetMapping("/employees")
    fun getAllEmployees(): Flux<Employee> {
        return repository.findAllEmployees()
    }
}
