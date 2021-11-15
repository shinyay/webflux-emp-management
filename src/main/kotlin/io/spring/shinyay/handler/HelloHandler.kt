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
            .contentType(MediaType.TEXT_PLAIN)
            .body(
                Mono.just("Hello Spring Boot!"),String::class.java
            )
    }

    fun helloFlux(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(
                Flux
                    .just("Hello", "Spring", "Boot!")
                    .delayElements(Duration.ofSeconds(1))
                    .log(),String::class.java
            )
    }

    fun helloName(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(
                Flux.just("Hello Spring ", request.pathVariable("name")).log(),String::class.java
            )
    }
}
