package ru.paskal.laba2.security.user

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(
    private val userEntityService: UserEntityService
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: UserEntity =
            userEntityService.getByUsername(username) ?: throw UsernameNotFoundException("User not found with username: $username")
        return UserPrincipal(
            user.id!!,
            user.username!!,
            user.password!!,
            mutableListOf(SimpleGrantedAuthority(user.role))
        )

    }
}