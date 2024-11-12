package ru.paskal.laba2

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import ru.paskal.laba2.client.Requester
import java.util.*

@SpringBootApplication
class Laba2Application {
	@Bean
	fun run(requester: Requester): CommandLineRunner {
		return CommandLineRunner {
			requester.fetchRequest()
		}
	}
}

fun main(args: Array<String>) {
	runApplication<Laba2Application>(*args)
}


