package io.spring.shinyay

import io.spring.shinyay.entity.Employee
import org.junit.jupiter.api.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.test.StepVerifier

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxEmpManagementApplicationTests(@LocalServerPort val port: Int) {

	val client: WebTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()

	@Test
    @Order(1)
	fun Given_LocalH2_When_AccessFluxAPI_Then_Return_OK() {
        client.get().uri("/api/v1/employees")
			.exchange()
			.expectStatus()
			.isOk
	}

	@Test
    @Order(2)
	fun Given_LocalH2_When_FindAll_Then_Return_Element() {
		val employeeForAnnotatedController = client.get().uri("/api/v1/employees")
			.exchange()
			.expectStatus()
			.isOk
			.returnResult(Employee::class.java)
			.responseBody
		StepVerifier.create(employeeForAnnotatedController)
            .expectNextCount(8)
            .verifyComplete()

        val employeeForFunctionalController = client.get().uri("/router/employees")
            .exchange()
            .expectStatus()
            .isOk
            .returnResult(Employee::class.java)
            .responseBody
        StepVerifier.create(employeeForFunctionalController)
            .expectNextCount(8)
            .verifyComplete()
	}

	@Test
    @Order(3)
	fun Given_LocalH2_When_FindById_Then_Return_Element() {
        val employee = client.get().uri("/api/v1/employees/1")
            .exchange()
            .expectStatus()
            .isOk
            .returnResult(Employee::class.java)
            .responseBody
        StepVerifier.create(employee)
            .expectNextCount(1)
            .verifyComplete()
    }

	@Test
    @Order(4)
	fun Given_LocalH2_When_FindById_Then_Return_DetailedElement() {
		client.get().uri("/api/v1/employees/1")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk
			.expectBody()
			.jsonPath("$.name")
			.isEqualTo("John")
	}

	@Test
    @Order(5)
	fun Given_LocalH2_When_AddElement_Then_Return_Element() {
        val employee = Employee(department_id = 100, name = "Shinya", role = "Developer")
        client.post().uri("/api/v1/employees")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(employee)
            .exchange()
            .expectStatus()
            .isCreated
            .expectBody()
            .jsonPath("$.name")
            .isEqualTo("Shinya")
    }

	@Test
    @Order(6)
	fun Given_LocalH2_When_UpdateElement_Then_Return_Element() {
        val employee = Employee(employee_id = 1, department_id = 100, name = "Shinya", role = "Advocate")
        client.put().uri("/api/v1/employees/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(employee)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.role")
            .isEqualTo("Advocate")
    }
}
