package com.api.cobroking.domain.rating

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class RatingDto(
    val id: Long? = null,
    @field:NotBlank
    var type: RatingType,
    @field:NotBlank
    var publicationId: Long,
    @field:NotBlank
    @field:Min(value = 0, message = "Rating must be greater than or equal to zero")
    @field:Max(value = 5, message = "Rating must be less than or equal to 5")
    var rating: Int,
    var opinion: String,
    @field:NotBlank
    var username: String
)

fun Rating.toRatingDto() = RatingDto(
    id = id,
    type = type,
    publicationId = publicationId,
    rating = rating,
    opinion = opinion!!,
    username = user.username
)