package com.api.cobroking.domain.security

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/login")
class OauthLoginController {

    @GetMapping("/oauth2/code/google")
    fun login(authentication: Authentication?): Any? {
        return authentication?.principal
    }

    @GetMapping("/oauth2/success")
    fun success(authentication: Authentication?): ResponseEntity.BodyBuilder {
        return ResponseEntity.ok()
    }
}