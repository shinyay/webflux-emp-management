package io.spring.shinyay

import io.spring.shinyay.entity.Employee
import io.spring.shinyay.repository.EmployeeRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Flux
import java.util.stream.Stream


@SpringBootApplication
class WebfluxEmpManagementApplication {

    val ddl = """
		DROP TABLE IF EXISTS employee;
		CREATE TABLE employee (
		                          employee_id decimal(4,0) NOT NULL AUTO_INCREMENT,
		                          department_id decimal(4,0),
		                          name varchar(64) NOT NULL,
		                          role varchar(32) DEFAULT NULL,
		                          PRIMARY KEY (employee_id)
		);
	""".trimIndent()

    @Bean
    fun init(repository: EmployeeRepository, client: DatabaseClient): ApplicationRunner? {
        return ApplicationRunner { args: ApplicationArguments? ->
            client.sql(ddl).fetch().first().subscribe()

            val stream = Stream.of(
                Employee(100, "John", "Dev"),
                Employee(200, "Mary", "QA"),
                Employee(300, "Peter", "Tester"),
                Employee(400, "Mike", "Designer")
            )

            repository.saveAll(Flux.fromStream(stream))
                .then()
                .subscribe()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<WebfluxEmpManagementApplication>(*args)
}
