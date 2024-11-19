package ru.paskal.laba2.dtos

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ru.paskal.laba2.entities.Affiliation
import ru.paskal.laba2.entities.Laureate
import ru.paskal.laba2.entities.Prize
import ru.paskal.laba2.serializers.LaureateDeserializer
import java.time.LocalDate

/**
 * DTO for {@link ru.paskal.laba2.entities.Laureate}
 */
@JsonDeserialize(using = LaureateDeserializer::class)
data class LaureateDto(
    var id: Int? = null,
    var originId: Int? = null,
    var firstname: String? = null,
    var surname: String? = null,
    var born: LocalDate? = null,
    var died: LocalDate? = null,
    var bornCountry: String? = null,
    var bornCountryCode: String? = null,
    var bornCity: String? = null,
    var diedCountry: String? = null,
    var diedCountryCode: String? = null,
    var diedCity: String? = null,
    var gender: String? = null,
    var prizes: List<PrizeDto> = listOf()
)


@Suppress("DuplicatedCode")
fun LaureateDto.mapToEntity(): Laureate {
    val laureateEnt = Laureate().apply {
        originId = this@mapToEntity.originId
        firstname = this@mapToEntity.firstname
        surname = this@mapToEntity.surname
        born = this@mapToEntity.born
        died = this@mapToEntity.died
        bornCountry = this@mapToEntity.bornCountry
        bornCountryCode = this@mapToEntity.bornCountryCode
        bornCity = this@mapToEntity.bornCity
        diedCountry = this@mapToEntity.diedCountry
        diedCountryCode = this@mapToEntity.diedCountryCode
        diedCity = this@mapToEntity.diedCity
        gender = this@mapToEntity.gender
    }

    val prizes = this@mapToEntity.prizes.map { prizeDto ->
        val prizeEnt = Prize().apply {
            year = prizeDto.year
            category = prizeDto.category
            share = prizeDto.share
            motivation = prizeDto.motivation
            laureate = laureateEnt
        }

        val affiliations = prizeDto.affiliations.map { affiliationDto ->
            Affiliation().apply {
                name = affiliationDto.name
                city = affiliationDto.city
                country = affiliationDto.country
                prize = prizeEnt
            }
        }
        prizeEnt.affiliations.addAll(affiliations)
        prizeEnt
    }

    laureateEnt.prizes.addAll(prizes)
    return laureateEnt
}