package io.spring.shinyay.controller

import io.spring.shinyay.entity.Employee
import io.spring.shinyay.repository.EmployeeRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Configuration
class EmployeeFunctionalEndpoint(val repository: EmployeeRepository) {

    @Bean
    fun router(): RouterFunction<ServerResponse> {
        return route()
                .GET("/functional/employees", this::findAllHandler)
                .build()
    }

    fun findAllHandler(request: ServerRequest): Mono<ServerResponse> {
        val result: Flux<Employee> = repository.findAll()
        return ServerResponse.ok().body(result, Employee::class.java)
    }

}
