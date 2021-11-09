package io.spring.shinyay.client

import io.spring.shinyay.entity.Employee
import org.springframework.web.reactive.function.client.WebClient

class EmployeeWebClient {

    private val client = WebClient.create("http://localhost:8080")

    fun consumeEmployee() {
        val employeeFlux = client.get()
            .uri("/api/v1/employees")
            .retrieve()
            .bodyToFlux(Employee::class.java)
        employeeFlux.subscribe { employee -> println(employee) }
    }
}
