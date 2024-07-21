package com.api.cobroking.domain.security.dtos

class UserJwtDto(
    val email: String,
    val firstname: String,
    val lastname: String,
    val username: String?
) {
}