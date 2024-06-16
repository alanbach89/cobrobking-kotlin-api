package com.api.cobroking.domain.rating

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.util.*

data class RatingDto(
    val id: Long? = null,
    @field:NotBlank
    var publicationType: PublicationType?,
    @field:NotBlank
    var publicationId: Long,
    @field:NotBlank
    @field:Min(value = 0, message = "Rating must be greater than or equal to zero")
    @field:Max(value = 5, message = "Rating must be less than or equal to 5")
    var rating: Int,
    var opinion: String,
    @field:NotBlank
    var userId: UUID
)