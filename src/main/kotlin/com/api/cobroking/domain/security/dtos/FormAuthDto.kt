package com.api.cobroking.domain.security.dtos

data class FormAuthDto(
    val provider: String = "LOCAL",
    val username: String,
    val email: String,
    val password: String
)