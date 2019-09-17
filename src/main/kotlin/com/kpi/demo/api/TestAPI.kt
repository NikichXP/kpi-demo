package com.kpi.demo.api

import com.kpi.demo.model.User
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestAPI(
        val mongoTemplate: MongoTemplate
) {

    @GetMapping("/kotlin")
    fun hello() = "hello from kotlin"

    @GetMapping("/user")
    fun user(@RequestParam name: String): User {
        return User(name).also {
            mongoTemplate.save(it)
        }
    }

}