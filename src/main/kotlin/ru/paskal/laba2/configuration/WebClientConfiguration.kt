package ru.paskal.laba2.configuration

import io.github.oshai.kotlinlogging.KotlinLogging
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import jakarta.annotation.PostConstruct
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Hooks
import reactor.netty.http.client.HttpClient
import java.util.function.Consumer

private val log = KotlinLogging.logger {}

@Configuration
@ComponentScan("ru.paskal.laba2")
@PropertySource("classpath:application.properties")
class WebClientConfiguration(env: Environment) {

    private var timeout: Int? = null

    init {
        timeout = env.getProperty("webclient.timeout", Int::class.java) ?: 1000
        log.info { "WebClient timeout: $timeout" }
    }

    @Bean
    fun webClient() = WebClient.builder()
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.ALL_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT_CHARSET, "UTF-16")
        .clientConnector(ReactorClientHttpConnector(
            HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout!!)
                .doOnConnected { connection ->
                    connection.addHandlerLast(ReadTimeoutHandler(timeout!!))
                    connection.addHandlerLast(WriteTimeoutHandler(timeout!!))
                }
        ))
        .exchangeStrategies(
            ExchangeStrategies.builder()
                .codecs { configurer -> configurer.defaultCodecs().maxInMemorySize(32 * 1024 * 1024) }
                .build()
        )
        .build()

    @PostConstruct
    fun init() {
        Hooks.onErrorDropped {e -> log.error { "Error from webClient: ${e.message}" } }
        log.info { "WebClientConfiguration initialized" }
    }
}