package ru.paskal.laba2.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.paskal.laba2.entities.LaureatesMetadata
import ru.paskal.laba2.repositories.LaureatesMetadataRepository

@Service
@Transactional(readOnly = true)
class MetadataService(
    private val repository: LaureatesMetadataRepository
) {
    fun getLastUpdate(): LaureatesMetadata {
        return repository.findLatest() ?: LaureatesMetadata()
    }

}