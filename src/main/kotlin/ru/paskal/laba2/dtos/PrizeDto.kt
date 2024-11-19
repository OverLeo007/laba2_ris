package ru.paskal.laba2.dtos

/**
 * DTO for {@link ru.paskal.laba2.entities.Prize}
 */
data class PrizeDto(
    var id: Int? = null,
    var year: Int? = null,
    var category: String? = null,
    var share: Int? = null,
    var motivation: String? = null,
    var affiliations: List<AffiliationDto> = listOf()
)