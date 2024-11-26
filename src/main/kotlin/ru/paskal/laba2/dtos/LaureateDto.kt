package ru.paskal.laba2.dtos

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import ru.paskal.laba2.entities.Affiliation
import ru.paskal.laba2.entities.Laureate
import ru.paskal.laba2.entities.Prize
import ru.paskal.laba2.serializers.LaureateDeserializer
import java.time.LocalDate

/**
 * DTO for {@link ru.paskal.laba2.entities.Laureate}
 */
@JsonDeserialize(using = LaureateDeserializer::class)
@Schema(description = "DTO для сущности Laureate")
data class LaureateDto(
    @Schema(description = "Идентификатор лауреата", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    var id: Int? = null,

    @Schema(description = "Оригинальный идентификатор лауреата", example = "1001")
    var originId: Int? = null,

    @Schema(description = "Имя лауреата", example = "John")
    var firstname: String? = null,

    @Schema(description = "Фамилия лауреата", example = "Doe")
    var surname: String? = null,

    @Schema(description = "Дата рождения лауреата", example = "1900-01-01")
    var born: String? = null,

    @Schema(description = "Дата смерти лауреата", example = "2000-01-01")
    var died: String? = null,

    @Schema(description = "Страна рождения лауреата", example = "USA")
    var bornCountry: String? = null,

    @Schema(description = "Код страны рождения лауреата", example = "US")
    var bornCountryCode: String? = null,

    @Schema(description = "Город рождения лауреата", example = "New York")
    var bornCity: String? = null,

    @Schema(description = "Страна смерти лауреата", example = "USA")
    var diedCountry: String? = null,

    @Schema(description = "Код страны смерти лауреата", example = "US")
    var diedCountryCode: String? = null,

    @Schema(description = "Город смерти лауреата", example = "Los Angeles")
    var diedCity: String? = null,

    @Schema(description = "Пол лауреата", example = "Male")
    var gender: String? = null,

    @ArraySchema(schema = Schema(description = "Список наград лауреата", implementation = PrizeDto::class))
    var prizes: List<PrizeDto> = listOf()
)


@Suppress("DuplicatedCode")
fun LaureateDto.mapToEntity(): Laureate {
    val laureateEnt = Laureate().apply {
        originId = this@mapToEntity.originId
        firstname = this@mapToEntity.firstname
        surname = this@mapToEntity.surname
        born = this@mapToEntity.born?.let { if (it != "null") LocalDate.parse(it) else null }
        died = this@mapToEntity.died?.let { if (it != "null") LocalDate.parse(it) else null }
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
                name = affiliationDto?.name
                city = affiliationDto?.city
                country = affiliationDto?.country
                prize = prizeEnt
            }
        }
        prizeEnt.affiliations.addAll(affiliations)
        prizeEnt
    }

    laureateEnt.prizes.addAll(prizes)
    return laureateEnt
}