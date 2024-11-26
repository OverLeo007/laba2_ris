package ru.paskal.laba2.dtos

import io.swagger.v3.oas.annotations.media.Schema


/**
 * DTO for {@link ru.paskal.laba2.entities.Affiliation}

 */
@Schema(description = "DTO для сущности Affiliation")
data class AffiliationDto(
    @Schema(description = "Идентификатор аффилиации", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    var id: Int? = null,

    @Schema(description = "Название аффилиации", example = "Harvard University")
    var name: String? = null,

    @Schema(description = "Город аффилиации", example = "Cambridge")
    var city: String? = null,

    @Schema(description = "Страна аффилиации", example = "USA")
    var country: String? = null
)