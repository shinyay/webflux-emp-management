package io.spring.shinyay.repository

import io.spring.shinyay.entity.Employee
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface EmployeeRepository : ReactiveCrudRepository<Employee, Long> {

    @Query("select e from Employee e where e.department_id = :departmentId")
    fun findByDepartmentId(departmentId: Long): Flux<Employee>
}
