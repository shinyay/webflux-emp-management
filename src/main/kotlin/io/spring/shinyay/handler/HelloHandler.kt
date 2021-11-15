package io.spring.shinyay.handler

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class HelloHandler {

    fun helloMono(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                Mono.just("Hello Spring Boot!"),String::class.java
            )
    }

    fun helloFlux(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                Flux
                    .just("Hello", "Spring", "Boot!")
                    .delayElements(Duration.ofSeconds(1))
                    .log(),String::class.java
            )
    }
}
