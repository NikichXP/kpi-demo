package com.kpi.demo.model

import java.util.*

class User(var name: String = "hello") {
    var id: String = UUID.randomUUID().toString()

    constructor(a1: Int, a2: Int, a3: Int = 4): this(a1.toString())

    var foo: String = "foo"
    var bar: String
    private var x: String = "a"
    var x1 = name + name + name

    val y: String
        get() = name + x1

    init {
        bar = name + name
    }

    private var z: String = "1234"
        set(value) {
            if (value.isEmpty()) {
                field = "aaaa"
            } else {
                field = value
            }
        }
    fun test() {
        z = ""
    }
}