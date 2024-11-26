package ru.paskal.laba2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class Laba2Application {
//	@Bean
//	fun run(requester: Requester): CommandLineRunner {
//		return CommandLineRunner {
//			requester.fetchRequest()
//		}
//	}

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}
}

fun main(args: Array<String>) {
	runApplication<Laba2Application>(*args)
}


