package com.api.cobroking.domain.publication.request

import com.api.cobroking.domain.publication.property.PublicationStatus
import jakarta.validation.constraints.NotBlank

class UserRequestPublicationDto (
    val id: Long,
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val text: String,
    @field:NotBlank
    val userId: Long,
    val status: PublicationStatus
)