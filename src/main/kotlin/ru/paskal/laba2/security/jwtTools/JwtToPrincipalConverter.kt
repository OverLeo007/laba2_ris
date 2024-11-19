package ru.paskal.laba2.security.jwtTools

import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import ru.paskal.laba2.security.user.UserPrincipal

@Component
class JwtToPrincipalConverter {

    fun convert(jwt: DecodedJWT): UserPrincipal {
        return UserPrincipal(
            jwt.getClaim("id").asInt(),
            jwt.getClaim("username").asString(),
            jwt.getClaim("password").asString(),
            extractAuthorities(jwt)
        )
    }

    private fun extractAuthorities(jwt: DecodedJWT): MutableList<SimpleGrantedAuthority> {
        val claim = jwt.getClaim("auth")
        if (claim.isNull || claim.isMissing) {
            return mutableListOf()
        }
        return claim.asList(SimpleGrantedAuthority::class.java).toMutableList()
    }
}