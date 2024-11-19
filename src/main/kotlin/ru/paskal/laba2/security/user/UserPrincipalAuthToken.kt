package ru.paskal.laba2.security.user

import org.springframework.security.authentication.AbstractAuthenticationToken

class UserPrincipalAuthToken( private var principal: UserPrincipal) : AbstractAuthenticationToken(principal.authorities)  {
    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any {
        return principal
    }
}