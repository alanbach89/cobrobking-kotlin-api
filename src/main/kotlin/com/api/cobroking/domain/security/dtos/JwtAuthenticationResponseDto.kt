package com.api.cobroking.domain.security.dtos

import java.io.Serializable


class JwtAuthenticationResponseDto(val token: String) : Serializable {
    companion object {
        private const val serialVersionUID = 1250166508152483573L
    }
}