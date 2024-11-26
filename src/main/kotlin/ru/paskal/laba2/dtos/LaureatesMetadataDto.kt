package ru.paskal.laba2.dtos

import io.swagger.v3.oas.annotations.media.Schema

/**
 * DTO for {@link ru.paskal.laba2.entities.LaureatesMetadata}
 */
@Schema(description = "DTO для метаданных лауреатов")
data class LaureatesMetadataDto(
    @Schema(description = "Дата последнего обновления", example = "2023-10-01T12:00:00Z")
    val lastUpdated: String? = null,

    @Schema(description = "Количество лауреатов", example = "100")
    val laureatesCount: Int? = null
)



