package com.example.kotlinwebservice

import com.example.kotlinwebservice.global.utils.encoding.Base64Encoder
import com.example.kotlinwebservice.global.utils.encoding.SHA512Encoder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinWebserviceApplication

fun main(args: Array<String>) {
    runApplication<KotlinWebserviceApplication>(*args)


}
