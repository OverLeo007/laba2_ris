package ru.paskal.laba2.client

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import ru.paskal.laba2.dtos.responses.UpdateFromApiResponse
import ru.paskal.laba2.exceptions.LaureateNotUpdatedException

private val log = KotlinLogging.logger {}

@Component
class Requester(
    @Value("\${client.data-url}") private val dataUrl: String,
    private val handler: Handler,
    private val webClient: WebClient
) {

    fun fetchRequest(): UpdateFromApiResponse {
        log.info { "Fetching data from $dataUrl" }
        val response = webClient.get()
            .uri(dataUrl)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        if (response != null) {
            return handler.handleResponse(response)
        } else {
            throw LaureateNotUpdatedException("Failed to fetch data from $dataUrl")
        }
    }

}