package ru.paskal.laba2.dtos

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema

/**
 * DTO for {@link ru.paskal.laba2.entities.Prize}
 */
@Schema(description = "DTO для сущности Prize")
data class PrizeDto(
    @Schema(description = "Идентификатор приза", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    var id: Int? = null,

    @Schema(description = "Год получения приза", example = "2023")
    var year: Int? = null,

    @Schema(description = "Категория приза", example = "Physics")
    var category: String? = null,

    @Schema(description = "Доля приза", example = "1")
    var share: Int? = null,

    @Schema(description = "Мотивация получения приза", example = "For groundbreaking research in quantum mechanics")
    var motivation: String? = null,

    @ArraySchema(schema = Schema(description = "Список аффилиаций", implementation = AffiliationDto::class))
    var affiliations: List<AffiliationDto?> = listOf()
)