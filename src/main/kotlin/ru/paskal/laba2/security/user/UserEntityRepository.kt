package ru.paskal.laba2.security.user;

import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityRepository : JpaRepository<UserEntity, Int> {
    fun findByUsername(username: String?): UserEntity?

    fun existsByUsername(username: String?): Boolean
}