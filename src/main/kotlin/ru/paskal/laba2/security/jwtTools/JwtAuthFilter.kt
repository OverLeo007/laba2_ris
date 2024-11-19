package ru.paskal.laba2.security.jwtTools

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import ru.paskal.laba2.security.user.UserPrincipalAuthToken
import java.io.IOException
import java.util.*

@Component
class JwtAuthFilter(
    private val jwtDecoder: JwtDecoder,
    private val jwtConverter: JwtToPrincipalConverter
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        extractToken(request)
            .map(jwtDecoder::decode)
            .map(jwtConverter::convert)
            .map(::UserPrincipalAuthToken)
            .ifPresent { auth -> SecurityContextHolder.getContext().authentication = auth }
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): Optional<String> {
        val token = request.getHeader("Authorization")
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return Optional.of(token.replaceFirst("Bearer ".toRegex(), ""))
        }
        return Optional.empty()
    }
}