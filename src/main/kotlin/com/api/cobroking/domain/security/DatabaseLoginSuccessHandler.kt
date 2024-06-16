package com.api.cobroking.domain.security

import com.api.cobroking.domain.user.UserRepository
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class DatabaseLoginSuccessHandler(val userRepository: UserRepository) : SavedRequestAwareAuthenticationSuccessHandler() {

    @Throws(ServletException::class, IOException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?, response: HttpServletResponse?,
        authentication: Authentication
    ) {
        val formAuthDto: FormAuthDto = authentication.principal as FormAuthDto
        userRepository.updateLastLogin(formAuthDto.username, formAuthDto.provider)
        super.onAuthenticationSuccess(request, response, authentication)
    }
}