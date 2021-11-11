package io.spring.shinyay

import io.spring.shinyay.entity.Employee
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.test.StepVerifier

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxEmpManagementApplicationTests(@LocalServerPort val port: Int) {

	val client: WebTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()

	@Test
	fun Given_LocalH2_When_AccessFluxAPI_Then_Return_OK() {
        client.get().uri("/api/v1/employees")
			.exchange()
			.expectStatus()
			.isOk
	}

	@Test
	fun Given_LocalH2_When_FindAll_Then_Return_Element() {
		val employee = client.get().uri("/api/v1/employees")
			.exchange()
			.expectStatus()
			.isOk
			.returnResult(Employee::class.java)
			.responseBody
		StepVerifier.create(employee)
            .expectNextCount(8)
            .verifyComplete()
	}

	@Test
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


}
