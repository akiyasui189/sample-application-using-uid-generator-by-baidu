package io.spring.start.sample.uid

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UniqueIdGeneratorApplication

fun main(args: Array<String>) {
    runApplication<UniqueIdGeneratorApplication>(*args)
}
