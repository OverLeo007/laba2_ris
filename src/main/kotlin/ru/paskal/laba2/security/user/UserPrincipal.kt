package ru.paskal.laba2.security.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(
    val id: Int,
    private val username: String,
    private val password: String? = null,
    private val authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password.orEmpty()
    }

    override fun getUsername(): String {
        return username
    }


}