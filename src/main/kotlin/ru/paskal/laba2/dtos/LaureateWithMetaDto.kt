package ru.paskal.laba2.dtos


class LaureateWithMetaDto {
    var laureate: LaureateDto? = null
    var metadata: LaureatesMetadataDto? = null
}


fun LaureateWithMetaDto.fromLaureateAndMetadata(
    laureate: LaureateDto,
    metadata: LaureatesMetadataDto
): LaureateWithMetaDto {
    this.laureate = laureate
    this.metadata = metadata
    return this
}