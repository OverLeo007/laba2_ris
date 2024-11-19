package ru.paskal.laba2.security.jwtTools

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Component

@Component
class JwtDecoder(
    private val jwtProperties: JwtProperties
) {

    fun decode(token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC256(jwtProperties.secretKey))
            .build()
            .verify(token)
    }
}