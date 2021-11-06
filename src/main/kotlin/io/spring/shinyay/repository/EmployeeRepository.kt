package io.spring.shinyay.repository

import io.spring.shinyay.entity.Employee
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux


@Repository
class EmployeeRepository {

    var employeeData: Map<String, Employee>? = null
    var employeeAccessData: Map<String, String>? = null

    fun findAllEmployees(): Flux<Employee> {
        return Flux.fromIterable(employeeData!!.values)
    }
}
