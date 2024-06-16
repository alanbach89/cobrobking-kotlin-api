package com.api.cobroking.domain.security

import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.user.UserRepository
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.IOException
import java.util.*


@Component
@Transactional
class OAuthLoginSuccessHandler(val userRepository: UserRepository) : SavedRequestAwareAuthenticationSuccessHandler() {

    @Throws(ServletException::class, IOException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?, response: HttpServletResponse?,
        authentication: Authentication
    ) {
        if (response?.isCommitted!!) {
            return
        }

        val oidcUser: DefaultOidcUser = authentication.principal as DefaultOidcUser
        val provider: String = (authentication as OAuth2AuthenticationToken).authorizedClientRegistrationId
        val userInfoDto: CustomOAuth2UserInfoDto = parseUserInfo(oidcUser, provider)

        processOAuth2User(userInfoDto)

        super.onAuthenticationSuccess(request, response, authentication)
    }

    private fun parseUserInfo(oidcUser: DefaultOidcUser, provider: String): CustomOAuth2UserInfoDto {
        return CustomOAuth2UserInfoDto(
            firstname = oidcUser.attributes["given_name"] as String,
            lastname = oidcUser.attributes["family_name"] as String,
            provider = provider,
            providerId = oidcUser.attributes["sub"] as String,
            email = oidcUser.attributes["email"] as String,
            imgUrl = oidcUser.attributes["picture"] as String
        )
    }

    private fun processOAuth2User(userInfoDto: CustomOAuth2UserInfoDto) {
        val user = userRepository?.findByUsername(userInfoDto.email)
        if (user == null) {
            registerNewUser(userInfoDto)
        }

        userRepository.updateLastLogin(userInfoDto.email, userInfoDto.provider)
    }

    private fun registerNewUser(userInfoDto: CustomOAuth2UserInfoDto) {
        val user = User()
        user.provider = userInfoDto.provider
        user.providerId = userInfoDto.providerId
        user.username = userInfoDto.email
        user.email = userInfoDto.email
        user.imgUrl = userInfoDto.imgUrl
        user.firstname = userInfoDto.firstname
        user.lastname = userInfoDto.lastname
        user.id = UUID.randomUUID()
        userRepository?.save(user)
    }
}