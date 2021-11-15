package io.spring.shinyay.router

import io.spring.shinyay.handler.HelloHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Flux


@Configuration
class HelloRouter {

    @Bean
    fun hello(handler: HelloHandler): RouterFunction<ServerResponse> {
        return route()
            .GET("/hello/mono", handler::helloMono)
            .GET("/hello/flux", handler::helloFlux)
            .GET("/hello/{name}", handler::helloName)
            .build()
    }
}
