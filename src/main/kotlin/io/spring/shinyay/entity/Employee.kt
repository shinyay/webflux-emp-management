package io.spring.shinyay.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("employee")
data class Employee(
    @Id
    var employee_id: Long,
    val department_id: Long,
    val name: String,
    val role: String
)
