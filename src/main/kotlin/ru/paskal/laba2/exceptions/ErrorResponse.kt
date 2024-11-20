package ru.paskal.laba2.exceptions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ErrorResponse(
    ex: Throwable,
) {
    val message: String = ex.message ?: "No message"
    val timestamp: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
}