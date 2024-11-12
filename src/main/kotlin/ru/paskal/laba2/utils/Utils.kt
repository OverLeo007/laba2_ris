package ru.paskal.laba2.utils

import com.fasterxml.jackson.databind.JsonNode

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
