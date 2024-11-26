package ru.paskal.laba2.security.jwtTools

import com.auth0.jwt.exceptions.TokenExpiredException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.paskal.laba2.security.user.UserPrincipalAuthToken
import java.util.*


@Component
class JwtAuthFilter(
    private val jwtDecoder: JwtDecoder,
    private val converter: JwtToPrincipalConverter
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            extractToken(request)
                .map(jwtDecoder::decode)
                .map(converter::convert)
                .map(::UserPrincipalAuthToken)
                .ifPresent { auth -> SecurityContextHolder.getContext().authentication = auth }
        } catch (e: TokenExpiredException) {
            throw TokenExpiredException(e.message, e.expiredOn)
        }
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): Optional<String> {
        val token = request.getHeader("Authorization")
        return if (!token.isNullOrBlank() && token.startsWith("Bearer ")) {
            Optional.of(token.removePrefix("Bearer ").trim())
        } else {
            Optional.empty()
        }
    }
}
