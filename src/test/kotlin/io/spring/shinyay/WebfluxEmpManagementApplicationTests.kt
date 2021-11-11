package io.spring.shinyay

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxEmpManagementApplicationTests(@LocalServerPort val port: Int) {

	val client: WebTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()

	@Test
	fun addEmployee() {
        client.get().uri("/api/v1/employees").exchange().expectStatus().isOk
	}
}
