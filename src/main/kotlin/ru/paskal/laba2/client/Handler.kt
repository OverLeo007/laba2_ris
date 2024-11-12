package ru.paskal.laba2.client

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import ru.paskal.laba2.utils.short
import ru.paskal.laba2.utils.shortPSting

private val log = KotlinLogging.logger {}

@Component
class Handler(private val objectMapper: ObjectMapper) {

    fun handleResponse(response: String) {
        val jsonTree = objectMapper.readTree(response)

        println("Response: ${jsonTree.shortPSting()}")
    }
}