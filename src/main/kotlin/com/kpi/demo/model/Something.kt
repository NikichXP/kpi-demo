package com.kpi.demo.model

import org.springframework.data.annotation.Id

class Something(var text: String) {

    @Id lateinit var key: String

}