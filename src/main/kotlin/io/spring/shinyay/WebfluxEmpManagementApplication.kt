package io.spring.shinyay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxEmpManagementApplication

fun main(args: Array<String>) {
	runApplication<WebfluxEmpManagementApplication>(*args)
}
