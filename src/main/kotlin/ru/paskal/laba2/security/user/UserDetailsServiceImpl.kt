package ru.paskal.laba2.security.user

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import ru.paskal.laba2.exceptions.UserEntityNotFoundException

@Component
class UserDetailsServiceImpl(
    private val userEntityService: UserEntityService
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: UserEntity =
            userEntityService.getByUsername(username) ?: throw UserEntityNotFoundException("User with username: $username not found")
        return UserPrincipal(
            user.id!!,
            user.username!!,
            user.password!!,
            mutableListOf(SimpleGrantedAuthority(user.role))
        )

    }
}