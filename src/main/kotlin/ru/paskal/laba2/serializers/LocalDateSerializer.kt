package ru.paskal.laba2.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateSerializer : JsonSerializer<LocalDate>() {
    override fun serialize(value: LocalDate?, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value?.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }
}