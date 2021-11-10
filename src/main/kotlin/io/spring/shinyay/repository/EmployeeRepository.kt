package io.spring.shinyay.repository

import io.spring.shinyay.entity.Employee
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
interface EmployeeRepository : ReactiveCrudRepository<Employee, Long>
