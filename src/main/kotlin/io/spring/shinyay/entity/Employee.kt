package io.spring.shinyay.entity

import org.springframework.data.annotation.Id

data class Employee(
    @Id
    var id: Long,
    var name: String
)
