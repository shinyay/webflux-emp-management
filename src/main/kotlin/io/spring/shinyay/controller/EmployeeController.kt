package io.spring.shinyay.controller

import io.spring.shinyay.entity.Employee
import io.spring.shinyay.repository.EmployeeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/v1")
class EmployeeController(val repository: EmployeeRepository) {

    @GetMapping("/employees")
    fun getAllEmployees(): Flux<Employee> {
        return repository.findAllEmployees()
    }
}
