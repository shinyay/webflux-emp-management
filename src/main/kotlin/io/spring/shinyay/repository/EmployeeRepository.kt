package io.spring.shinyay.repository

import io.spring.shinyay.entity.Employee
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : ReactiveCrudRepository<Employee, Long>
