package com.api.cobroking.domain.security.dtos

data class CustomOAuth2UserInfoDto(
    val provider: String,
    val providerId: String,
    val email: String,
    val imgUrl: String,
    val firstname: String,
    val lastname: String
)