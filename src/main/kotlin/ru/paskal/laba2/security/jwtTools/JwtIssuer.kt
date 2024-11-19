package ru.paskal.laba2.security.jwtTools

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import ru.paskal.laba2.security.user.UserPrincipal
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.jvm.Throws

@Component
class JwtIssuer {
    private val properties: JwtProperties? = null

    @Throws(IllegalStateException::class)
    fun issue(principal: UserPrincipal): String {
        return JWT.create()
            .withSubject(java.lang.String.valueOf(principal.id))
            .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
            .withClaim("username", principal.getUsername())
            .withClaim(
                "auth", principal.getAuthorities()
                    .stream().map { obj: GrantedAuthority -> obj.authority }.toList()
            )
            .sign(Algorithm.HMAC256(properties?.secretKey ?: throw IllegalStateException("Secret key is not set")))
    }
}