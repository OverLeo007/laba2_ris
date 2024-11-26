package ru.paskal.laba2.dtos.responses

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "DTO для ответа на логин")
data class LoginResponse(
    @Schema(description = "JWT токен", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    var token: String? = null
)