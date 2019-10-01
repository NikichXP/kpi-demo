package com.kpi.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import reactor.core.publisher.Mono


@Configuration
@EnableWebFlux
class WebFluxRoutes {

//    @Bean
//    fun routerFunctionA(): RouterFunction<*> = RouterFunctions.route()
//            .GET("/foo") { Mono.just(ResponseEntity.ok("ok"))}
//            .build()

}