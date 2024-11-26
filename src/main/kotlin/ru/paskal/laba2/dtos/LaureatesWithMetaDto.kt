package ru.paskal.laba2.dtos

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "DTO для лауреатов с метаданными")
class LaureatesWithMetaDto {
    @Schema(description = "Метаданные лауреатов")
    var metadata: LaureatesMetadataDto? = null
    @ArraySchema(schema = Schema(description = "Список лауреатов", implementation = LaureateDto::class))
    var laureates: List<LaureateDto>? = null

    companion object {
        fun fromLaureateAndMetadata(
            laureates: List<LaureateDto>,
            metadata: LaureatesMetadataDto?
        ): LaureatesWithMetaDto {
            val new = LaureatesWithMetaDto()
            new.metadata = metadata
            new.laureates = laureates
            return new
        }

    }
}



