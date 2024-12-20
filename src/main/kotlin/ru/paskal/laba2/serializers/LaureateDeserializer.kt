package ru.paskal.laba2.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import ru.paskal.laba2.dtos.AffiliationDto
import ru.paskal.laba2.dtos.LaureateDto
import ru.paskal.laba2.dtos.PrizeDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LaureateDeserializer : JsonDeserializer<LaureateDto>() {

    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LaureateDto {
        val node: JsonNode = p.codec.readTree(p)

        val laureate = LaureateDto(
            originId = if (node["id"] == null) node["originId"]?.asInt() else node["id"].asInt(),
            firstname = wrapNullStr(node["firstname"]?.asText()),
            surname = wrapNullStr(node["surname"]?.asText()),
            born = parseBornDate(node["born"]?.asText()).toString(),
            died = parseDiedDate(node["died"]?.asText()).toString(),
            bornCountry = wrapNullStr(node["bornCountry"]?.asText()),
            bornCountryCode = wrapNullStr(node["bornCountryCode"]?.asText()),
            bornCity = wrapNullStr(node["bornCity"]?.asText()),
            diedCountry = wrapNullStr(node["diedCountry"]?.asText()),
            diedCountryCode = wrapNullStr(node["diedCountryCode"]?.asText()),
            diedCity = wrapNullStr(node["diedCity"]?.asText()),
            gender = wrapNullStr(node["gender"]?.asText()),
            prizes = parsePrizes(node["prizes"])
        )

        return laureate
    }

    private fun wrapNullStr(str: String?): String? {
        return if (str == null || str == "null") null else str
    }

    private fun parseBornDate(born: String?): LocalDate? {
        return when {
            born == null -> null
            born == "null" -> null
            born.endsWith("-00-00") -> LocalDate.parse("${born.substring(0, 4)}-01-01", dateFormatter)
            else -> born.let { LocalDate.parse(it, dateFormatter) }
        }
    }


    private fun parseDiedDate(died: String?): LocalDate? {
        return when (died) {
            null -> null
            "null" -> null
            "0000-00-00" -> null
            else -> died.let { LocalDate.parse(it, dateFormatter) }
        }
    }

    private fun parsePrizes(prizesNode: JsonNode?): List<PrizeDto> {
        val prizes = mutableListOf<PrizeDto>()
        prizesNode?.forEach { prizeNode ->
            prizes.add(parsePrize(prizeNode))
        }
        return prizes
    }

    private fun parsePrize(prizeNode: JsonNode): PrizeDto {
        val affiliationsNode = prizeNode["affiliations"]

        val affiliations = if (affiliationsNode.isArray && affiliationsNode.all { it.isArray && it.isEmpty }) {
            emptyList()
        } else {
            prizeNode["affiliations"]?.map { parseAffiliation(it) } ?: listOf()
        }

        return PrizeDto(
            id = null,
            year = prizeNode["year"]?.asInt(),
            category = prizeNode["category"]?.asText(),
            share = prizeNode["share"]?.asInt(),
            motivation = prizeNode["motivation"]?.asText(),
            affiliations = affiliations
        )
    }

    private fun parseAffiliation(affiliationNode: JsonNode): AffiliationDto {
        if (affiliationNode.isObject) {
            return AffiliationDto(
                id = null,
                name = affiliationNode["name"]?.asText(),
                city = affiliationNode["city"]?.asText(),
                country = affiliationNode["country"]?.asText()
            )
        }
        return AffiliationDto()
    }
}