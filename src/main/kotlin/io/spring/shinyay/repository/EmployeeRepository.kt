package io.spring.shinyay.repository

import io.spring.shinyay.entity.Employee
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
class EmployeeRepository {

    var employeeData: Map<Long, Employee>? = null
    var employeeAccessData: Map<Long, String>? = null

    fun findAllEmployees(): Flux<Employee> {
        return Flux.fromIterable(employeeData!!.values)
    }

    fun updateEmployee(employee: Employee): Mono<Employee> {
        return Mono.just(employee)
    }
}
