package com.kpi.demo.api

import com.kpi.demo.model.User
import kotlinx.coroutines.*
import org.jboss.logging.Logger
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import javax.annotation.PostConstruct

@RestController
class TestAPI(
        val mongoTemplate: MongoTemplate,
        val reactiveMongoTemplate: ReactiveMongoTemplate
) {

    val logger = Logger.getLogger(this::class.java)

    @PostConstruct
    fun test() {

        val pool = Executors.newCachedThreadPool()

        for (i in 0..10) {
            val tasks = (0..100).map {
                CompletableFuture.runAsync(Runnable {
                    logger.info("Thread $i-$it started")
                    Thread.sleep(1_000)
                    logger.info("Thread $i-$it ends")
                }, pool)
            }.toList()
            tasks.forEach { it.join() }
        }
    }

    suspend fun a() {
        delay(100)
    }

    @GetMapping("/long")
    fun long(): String {
        val f1 = CompletableFuture.supplyAsync {
            Thread.sleep(1_000)
            "hello"
        }
        val f2 = CompletableFuture.supplyAsync {
            Thread.sleep(1_000)
            "world"
        }
        val futures = mutableListOf<Deferred<String>>()
        GlobalScope.run {
            for (i in 1..100) {
                futures += async {
                    delay(1_000)
                    i.toString()
                }
            }
        }
        return runBlocking {
            "${f1.get()} ${f2.get()} " +
                    futures.map { it.await() }.reduce { s1, s2 -> s1 + s2 }
        }
    }


    @GetMapping("/kotlin")
    fun hello(): String = "hello from kotlin"

    @GetMapping("/old")
    fun old(): User = User()

    @GetMapping("/new")
    fun new(): Mono<User> = Mono.just(User())

    @GetMapping("/users")
    fun collections(): Flux<User> {
        return reactiveMongoTemplate.findAll()
    }

    @GetMapping("/users-old")
    fun collections2(): List<User> {
        return mongoTemplate.findAll()
    }

    @GetMapping("/user")
    fun user(@RequestParam name: String): User {
        return User(name).also {
            mongoTemplate.save(it)
        }
    }

}