package com.api.cobroking.domain.security

import com.api.cobroking.domain.security.dtos.SignInDto
import com.api.cobroking.domain.security.dtos.SignUpDto
import com.api.cobroking.domain.user.UserService
import com.api.cobroking.domain.security.jwt.JwtService
import com.api.cobroking.domain.security.dtos.UserJwtDto
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service


@Service
class AuthService (
    private val jwtService: JwtService,
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager
    ) {

    fun signUp(signUpDto: SignUpDto): String {
        userService.createWithSignUp(signUpDto)
        val userJwtDto = UserJwtDto(signUpDto.email, signUpDto.firstname, signUpDto.lastname, signUpDto.username)
        return jwtService.createToken(userJwtDto)
    }

    fun signIn(signInDto: SignInDto): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(signInDto.username, signInDto.password)
        )
        val user = userService.getByEmailOrUsername(signInDto.username, signInDto.username)
        val userJwtDto = UserJwtDto(user.email, user.firstname, user.lastname, user.username)
        return jwtService.createToken(userJwtDto)
    }

    fun getRefreshToken(req: HttpServletRequest): String {
        return jwtService.resolveClaims(req).let {
            val userJwtDto = UserJwtDto(
                it!!["email"] as String, it!!["firstname"] as String, it!!["lastname"] as String,
            it!!.subject)
            return jwtService.createToken(userJwtDto)
        }
    }

    fun isAuthenticated(req: HttpServletRequest): Boolean {
        return jwtService.resolveClaims(req).let { jwtService.validateClaims(it!!) }
    }

    fun emailAlreadyExists(email: String): Boolean {
        return try {
            userService.getByEmail(email) != null
        } catch (e: Exception) {
            false
        }
    }

    fun usernameAlreadyExists(username: String): Boolean {
        return try {
            userService.getByUsername(username) != null
        } catch (e: Exception) {
            false
        }
    }
}