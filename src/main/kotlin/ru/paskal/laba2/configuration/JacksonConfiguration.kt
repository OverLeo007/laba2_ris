package ru.paskal.laba2.configuration

import com.fasterxml.jackson.core.json.JsonWriteFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import ru.paskal.laba2.dtos.LaureateDto
import ru.paskal.laba2.serializers.LaureateDeserializer
import ru.paskal.laba2.serializers.LocalDateSerializer
import java.time.LocalDate

@Configuration
@ComponentScan("ru.paskal.laba2")
class JacksonConfiguration {


    @Bean
    fun objectMapper(): ObjectMapper {
        val module = SimpleModule()
            .apply {
                addDeserializer(LaureateDto::class.java, LaureateDeserializer())
                addSerializer(LocalDate::class.java, LocalDateSerializer())
            }
        return ObjectMapper()
            .registerModule(module)
            .registerModule(JavaTimeModule())
            .apply {
                configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), false)
            }
    }

}