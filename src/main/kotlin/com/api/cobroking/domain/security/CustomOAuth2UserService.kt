package com.api.cobroking.domain.security

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service


@Service
class CustomOAuth2UserService : DefaultOAuth2UserService() {

    override fun loadUser(oAuth2UserRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User: OAuth2User = super.loadUser(oAuth2UserRequest)
        return processOAuth2User(oAuth2UserRequest, oAuth2User)
    }

    private fun processOAuth2User(oAuth2UserRequest: OAuth2UserRequest, oAuth2User: OAuth2User): OAuth2User {
        val clientName: String = oAuth2UserRequest.clientRegistration.clientName
        return CustomOAuth2UserDto(oAuth2User, clientName)
    }
}