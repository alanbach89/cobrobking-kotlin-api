package com.api.cobroking.domain.security

import com.api.cobroking.domain.security.dtos.SignInDto
import com.api.cobroking.domain.security.dtos.SignUpDto
import com.api.cobroking.domain.security.dtos.JwtAuthenticationResponseDto
import com.api.cobroking.domain.security.jwt.JwtService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController(
    val jwtService: JwtService,
    val authService: AuthService
) {


    /**
     * Register new user and login
     */
   @PostMapping("/sign-up")
    fun signUp( @Valid @RequestBody signUpDto: SignUpDto): ResponseEntity<JwtAuthenticationResponseDto> {
        val token = authService.signUp(signUpDto)
        return ResponseEntity.ok(JwtAuthenticationResponseDto(token))
    }


    /**
     * Login new user
     */
    @PostMapping("/sign-in")
    fun signIn( @Valid @RequestBody signInDto: SignInDto): ResponseEntity<JwtAuthenticationResponseDto> {
        val token = authService.signIn(signInDto)
        return ResponseEntity.ok(JwtAuthenticationResponseDto(token))
    }

    @GetMapping("refresh-token")
    fun refreshToken(request: HttpServletRequest): ResponseEntity<Any> {
        return if(authService.isAuthenticated(request)) {
            val token = authService.getRefreshToken(request)
            ResponseEntity.ok(JwtAuthenticationResponseDto(token))
        } else {
            ResponseEntity.badRequest().body("User is not Authenticated")
        }

    }

    /**
     * Checks is a given email is in use or not.
     */
    @GetMapping("/check-email-in-use")
    fun checkEmailInUse(@Param(value = "Email id to check against") @RequestParam("email") email: String):
            ResponseEntity<String> {
        val emailExists: Boolean = authService.emailAlreadyExists(email)
        return ResponseEntity.ok(emailExists.toString())
    }

    /**
     * Checks is a given username is in use or not.
     */
    @GetMapping("/check-username-in-use")
    fun checkUsernameInUse(@Param(value = "Username to check against") @RequestParam("username") username: String):
            ResponseEntity<String>? {
        val usernameExists: Boolean = authService.usernameAlreadyExists(username)
        return ResponseEntity.ok(usernameExists.toString())
    }
}