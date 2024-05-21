package com.api.cobroking.domain.security

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/login")
class LoginController {

    @GetMapping
    fun login(authentication: Authentication): Any? {
        return authentication.principal
    }
}