package com.api.cobroking.domain.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

data class CustomOAuth2UserDto(
    private val oauth2User: OAuth2User,
    val oauth2ClientName: String
) : OAuth2User {

    override fun getAttributes(): Map<String, Any> {
        return oauth2User.attributes
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return oauth2User.authorities
    }

    override fun getName(): String? {
        return oauth2User.getAttribute("name")
    }

    fun getEmail(): String? {
        return oauth2User.getAttribute("email")
    }
}