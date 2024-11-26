package ru.paskal.laba2.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.paskal.laba2.entities.LaureatesMetadata

interface LaureatesMetadataRepository : JpaRepository<LaureatesMetadata, Int> {

    @Query("SELECT lm FROM LaureatesMetadata lm ORDER BY lm.lastUpdated DESC LIMIT 1")
    fun findLatest(): LaureatesMetadata?
}