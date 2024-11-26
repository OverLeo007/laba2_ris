package ru.paskal.laba2.security.jwtTools

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment

private val log = KotlinLogging.logger {}

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("ru.paskal.laba2")
class JwtProperties(env: Environment) {
    final var secretKey: String = env.getProperty("security.jwt.secret-key", String::class.java).toString()

}