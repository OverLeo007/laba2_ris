package ru.paskal.laba2.entities

import jakarta.persistence.*
import ru.paskal.laba2.dtos.LaureatesMetadataDto
import java.time.Instant

@Entity
@Table(name = "laureates_metadata")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
)
class LaureatesMetadata : BaseEntity() {
    @Column(name = "last_updated", nullable = false)
    var lastUpdated: Instant? = null

    @Column(name = "laureates_count", nullable = false)
    var laureatesCount: Int? = null
}

fun LaureatesMetadata.toDto() = LaureatesMetadataDto(lastUpdated, laureatesCount)