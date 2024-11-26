package ru.paskal.laba2.dtos.requests

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "DTO для запроса на логин")
data class LoginRequest(
    @Schema(description = "Имя пользователя", example = "user123")
    var username: String? = null,

    @Schema(description = "Пароль пользователя", example = "password123")
    var password: String? = null
)