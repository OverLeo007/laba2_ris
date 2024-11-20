package ru.paskal.laba2.services

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.paskal.laba2.entities.Laureate
import ru.paskal.laba2.repositories.LaureateRepository

private val log = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class LaureateService(
    private val repository: LaureateRepository
) {

    @Transactional
    fun saveAllIfNotExists(laureates: List<Laureate>) {
        val existingIds = repository.findAllOriginIds()
        val newLaureates = laureates.filter { it.originId !in existingIds }
        if (newLaureates.isNotEmpty()) {
            log.info { "Saving ${newLaureates.size} new laureates" }
            repository.saveAll(newLaureates)
        }
    }

    @Transactional
    fun saveOne(laureate: Laureate): Laureate {
        return repository.save(laureate)
    }

    fun getAll(): List<Laureate> {
        return repository.findAll()
    }

    fun getAllByBornCountry(bornCountry: String): List<Laureate> {
        return repository.findAllByBornCountry(bornCountry)
    }

}