 package ru.paskal.laba2.dtos

 import java.time.Instant

 /**
 * DTO for {@link ru.paskal.laba2.entities.LaureatesMetadata}
 */
data class LaureatesMetadataDto(val lastUpdated: Instant? = null, val laureatesCount: Int? = null)

