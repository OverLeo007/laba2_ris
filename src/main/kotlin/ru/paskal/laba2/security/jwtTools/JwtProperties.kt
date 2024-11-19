package ru.paskal.laba2.security.jwtTools

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("security.jwt")
class JwtProperties {
    val secretKey: String? = null
}