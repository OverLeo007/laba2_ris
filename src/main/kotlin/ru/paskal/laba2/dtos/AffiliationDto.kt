package ru.paskal.laba2.dtos

/**
 * DTO for {@link ru.paskal.laba2.entities.Affiliation}
 */
data class AffiliationDto(
    var id: Int? = null,
    var name: String? = null,
    var city: String? = null,
    var country: String? = null
)