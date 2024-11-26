package ru.paskal.laba2.services

import io.github.oshai.kotlinlogging.KotlinLogging
import org.jooq.DSLContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.paskal.laba2.dtos.*
import ru.paskal.laba2.dtos.responses.UpdateFromApiResponse
import ru.paskal.laba2.entities.Laureate
import ru.paskal.laba2.entities.tables.Tables.*
import ru.paskal.laba2.entities.toDto
import ru.paskal.laba2.repositories.LaureateRepository
import ru.paskal.laba2.repositories.LaureatesMetadataRepository
import ru.paskal.laba2.serializers.LaureateDeserializer
import ru.paskal.laba2.utils.camelToSnake


private val log = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class LaureateService(
    private val repository: LaureateRepository,
    private val metaRepository: LaureatesMetadataRepository,
    private val dsl: DSLContext
) {

    @Transactional
    fun saveAllIfNotExists(laureates: List<Laureate>): UpdateFromApiResponse {
        val existingIds = repository.findAllOriginIds()
        val newLaureates = laureates.filter { it.originId !in existingIds }
        if (newLaureates.isNotEmpty()) {
            log.info { "Saving ${newLaureates.size} new laureates" }
            return UpdateFromApiResponse("Saved ${repository.saveAll(newLaureates).size} new laureates")
        } else {
            log.info { "All data is up to date" }
            return UpdateFromApiResponse("All data is up to date")
        }
    }

    @Transactional
    fun saveOne(laureate: Laureate): Laureate {
        log.info { "saving $laureate" }
        return repository.save(laureate)
    }

    fun getAllWithMeta(field: String? = null, value: String? = null): LaureatesWithMetaDto {

        return  LaureatesWithMetaDto.fromLaureateAndMetadata(
            getFullGraph(field, value),
            metaRepository.findLatest()?.toDto()
        )
    }

    fun getFullGraph(field: String? = null, value: String? = null): List<LaureateDto> {
        val deserializer = LaureateDeserializer()
        val query = dsl.select(
            LAUREATES.asterisk(),
            PRIZES.asterisk(),
            AFFILIATIONS.asterisk()
        )
            .from(LAUREATES)
            .leftJoin(PRIZES).on(LAUREATES.ID.eq(PRIZES.LAUREATE_ID))
            .leftJoin(AFFILIATIONS).on(PRIZES.ID.eq(AFFILIATIONS.PRIZE_ID))


        val availableFields = LAUREATES.fields().map { it.name }
        log.info { "Available fields: $availableFields" }
        log.info { "Requested field: ${field?.camelToSnake()}" }

        val fixedValue = if (value == "") null else value
        log.info { "Requested value $fixedValue" }
        if (field != null) {
            if (fixedValue == null) {
                query.where(LAUREATES.field(field.camelToSnake())?.isNull ?: error("Unknown field: $field"))
            } else {
                query.where(LAUREATES.field(field.camelToSnake(), String::class.java)?.eq(fixedValue) ?: error("Unknown field: $field"))
            }
        }

        return query.fetchGroups(LAUREATES.ID)
            .map { (_, records) ->
                val prizes = records.groupBy { it[PRIZES.ID] }.map { (_, prizeRecords) ->
                    if (prizeRecords.first()[PRIZES.ID] == null) {
                        null
                    } else {
                        PrizeDto(
                            id = prizeRecords.first()[PRIZES.ID],
                            year = prizeRecords.first()[PRIZES.YEAR],
                            category = prizeRecords.first()[PRIZES.CATEGORY],
                            share = prizeRecords.first()[PRIZES.SHARE],
                            motivation = prizeRecords.first()[PRIZES.MOTIVATION],
                            affiliations = prizeRecords.map {
                                if (it[AFFILIATIONS.ID] == null) {
                                    null
                                } else {
                                    AffiliationDto(
                                        id = it[AFFILIATIONS.ID],
                                        name = it[AFFILIATIONS.NAME],
                                        city = it[AFFILIATIONS.CITY],
                                        country = it[AFFILIATIONS.COUNTRY]
                                    )
                                }
                            }.filterNotNull()
                        )
                    }
                }.filterNotNull()

                LaureateDto(
                    id = records.first()[LAUREATES.ID],
                    originId = records.first()[LAUREATES.ORIGIN_ID],
                    firstname = records.first()[LAUREATES.FIRSTNAME],
                    surname = records.first()[LAUREATES.SURNAME],
                    born = records.first()[LAUREATES.BORN]?.let { it.format(deserializer.dateFormatter) },
                    died = records.first()[LAUREATES.DIED]?.let { it.format(deserializer.dateFormatter) },
                    bornCountry = records.first()[LAUREATES.BORN_COUNTRY],
                    bornCountryCode = records.first()[LAUREATES.BORN_COUNTRY_CODE],
                    bornCity = records.first()[LAUREATES.BORN_CITY],
                    diedCountry = records.first()[LAUREATES.DIED_COUNTRY],
                    diedCountryCode = records.first()[LAUREATES.DIED_COUNTRY_CODE],
                    diedCity = records.first()[LAUREATES.DIED_CITY],
                    gender = records.first()[LAUREATES.GENDER],
                    prizes = prizes
                )
            }
    }


    fun getAllByBornCountryCode(bornCountryCode: String): List<LaureateDto> {
        return getFullGraph("bornCountryCode", bornCountryCode)
    }

}
