package ru.paskal.laba2.client

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

private val log = KotlinLogging.logger {}

@Component
class Requester(
    @Value("\${client.data-url}") private val dataUrl: String,
    private val handler: Handler,
    private val webClient: WebClient
) {

    fun fetchRequest() {
        log.info { "Fetching data from $dataUrl" }
        webClient.get()
            .uri(dataUrl)
            .retrieve()
            .bodyToMono(String::class.java)
            .subscribe(handler::handleResponse)
    }

}