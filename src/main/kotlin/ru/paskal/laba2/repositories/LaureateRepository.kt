package ru.paskal.laba2.repositories

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.paskal.laba2.entities.Laureate


interface LaureateRepository : JpaRepository<Laureate, Int> {

    @Query("SELECT l.originId FROM Laureate l")
    fun findAllOriginIds(): List<Int>


    @EntityGraph(value = "laureate-prizes-affiliations")
    @Query("SELECT l FROM Laureate l")
    fun getFullGraph(): List<Laureate>


}