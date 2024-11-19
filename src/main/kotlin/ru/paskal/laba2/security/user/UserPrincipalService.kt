package ru.paskal.laba2.security.user

import org.springframework.stereotype.Service

@Service
class UserPrincipalService(
    private val userEntityService: UserEntityService
) {
    fun getUserFromPrincipal(principal: UserPrincipal?): UserEntity {
        if (principal == null) {
            throw IllegalArgumentException("Unauthorized")
        }
        return userEntityService.getById(principal.id)
    }
}