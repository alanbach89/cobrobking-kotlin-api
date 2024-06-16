package com.api.cobroking.domain.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController( ) {

   @PostMapping("/sign_up")
    fun signUp(@RequestBody signUpDto: SignUpDto): ResponseEntity<String> {
        val userAndToken = authService.signUp(signUpDto)
        return ResponseEntity.ok(userAndToken)
    }

    @PostMapping("/sign_in")
    fun signIn(@RequestBody signInDto: SignInDto): ResponseEntity<String> {
        val userAndToken = authService.signIn(signInDto)
        return ResponseEntity.ok(userAndToken)
    }

    @GetMapping("refresh_token")
    fun refreshToken(request: HttpServletRequest): ResponseEntity<Map<String, String>> {
        val claims = request.getAttribute(Constant.CLAIMS) as Claims
        val token = authService.getRefreshToken(claims = claims)

        return ResponseEntity.ok(mapOf("token" to token))
    }
}