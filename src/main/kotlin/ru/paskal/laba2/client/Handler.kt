package ru.paskal.laba2.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ru.paskal.laba2.dtos.LaureateDto
import ru.paskal.laba2.dtos.mapToEntity
import ru.paskal.laba2.services.LaureateService
import ru.paskal.laba2.utils.shortPSting
import kotlin.reflect.full.declaredMemberProperties

private val log = KotlinLogging.logger {}

@Component
class Handler(
    private val objectMapper: ObjectMapper,
    private val laureateService: LaureateService
) {

    fun handleResponse(response: String) {
        val jsonTree = objectMapper.readTree(response)

        println("Response: ${jsonTree.shortPSting()}")
        val laureates = jsonTree["laureates"].map { objectMapper.treeToValue<LaureateDto>(it) }.map { it.mapToEntity() }
        val nullables: HashMap<String, Boolean> = hashMapOf()
        laureates.forEach { item ->
            item::class.declaredMemberProperties.forEach { prop ->
                if (prop.getter.call(item) == null) {
                    nullables[prop.name] = true
                }
            }
        }
        if (nullables.isNotEmpty()) {
            log.info { "Nullables: $nullables" }
        }
        laureateService.saveAllIfNotExists(laureates)

    }
}