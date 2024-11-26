package ru.paskal.laba2.utils

import com.fasterxml.jackson.databind.JsonNode
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Return cropped to 300 symbols length string with "..." at the end
 */
fun String.short() = if (this.length > 300) this.substring(0, 300) + "..." else this

/**
 * Return cropped plain json string to 300 symbols length
 */
fun JsonNode.shortString() = this.toString().short()

/**
 * Return cropped pretty json string to 300 symbols length
 */
fun JsonNode.shortPSting() = this.toPrettyString().short()

/**
 * Convert camelCase to snake_case
 */
fun String.camelToSnake(): String {
    return this.replace(Regex("([a-z])([A-Z]+)"), "$1_$2").lowercase()
}

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault())

fun Instant.toFormattedString(): String = formatter.format(this)