package com.example.SmartSteuer.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmartSteuerApplication

fun main(args: Array<String>) {
	runApplication<SmartSteuerApplication>(*args)
}
