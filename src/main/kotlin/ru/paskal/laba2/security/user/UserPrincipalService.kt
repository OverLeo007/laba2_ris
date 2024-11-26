package ru.paskal.laba2.security.user

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.paskal.laba2.dtos.requests.LoginRequest
import ru.paskal.laba2.dtos.responses.LoginResponse
import ru.paskal.laba2.exceptions.AccessForbiddenException
import ru.paskal.laba2.security.jwtTools.JwtIssuer

private val log = KotlinLogging.logger {}

@Service
class UserPrincipalService(
    private val userEntityService: UserEntityService,
    private val authenticationManager: AuthenticationManager,
    private val jwtIssuer: JwtIssuer
) {
    fun getUserFromPrincipal(principal: UserPrincipal?): UserEntity {
        if (principal == null) {
            throw AccessForbiddenException("Unauthorized")
        }
        return userEntityService.getById(principal.id)
    }

    fun login(request: LoginRequest): LoginResponse {
        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
        SecurityContextHolder.getContext().authentication = auth
        val principal = auth.principal as UserPrincipal
        return LoginResponse(jwtIssuer.issue(principal))
    }

    fun register(request: LoginRequest): LoginResponse {
        val user = userEntityService.createUser(request.username!!, request.password!!, "ROLE_USER")
        return login(LoginRequest(user.username, request.password))
    }



}