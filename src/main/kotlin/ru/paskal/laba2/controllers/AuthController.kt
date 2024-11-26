package ru.paskal.laba2.controllers

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.paskal.laba2.dtos.requests.LoginRequest
import ru.paskal.laba2.dtos.responses.LoginResponse
import ru.paskal.laba2.security.user.UserPrincipalService

private val log = KotlinLogging.logger {}

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "API для аутентификации и регистрации")
class AuthController(
    private var userPrincipalService: UserPrincipalService
) {
    @PostMapping("/login")
    @Operation(summary = "Вход в систему", description = "Аутентификация пользователя с использованием логина и пароля")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Успешная аутентификация"),
            ApiResponse(responseCode = "400", description = "Неверные учетные данные"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
        ]
    )
    fun login(@RequestBody @Validated loginRequest: LoginRequest): LoginResponse {
        log.debug { "Try to login $loginRequest" }
        try {
            return userPrincipalService.login(loginRequest)
        } catch (exception: Exception) {
            throw BadCredentialsException("Bad credentials")
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация", description = "Регистрация нового пользователя")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Успешная регистрация"),
            ApiResponse(responseCode = "400", description = "Неверные учетные данные"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
        ]
    )
    fun register(@RequestBody @Validated loginRequest: LoginRequest): LoginResponse {
        log.debug { "Try to register $loginRequest" }
        return userPrincipalService.register(loginRequest)
    }
}