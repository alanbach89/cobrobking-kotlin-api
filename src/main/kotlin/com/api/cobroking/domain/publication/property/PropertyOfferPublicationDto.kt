package com.api.cobroking.domain.publication.property

import com.api.cobroking.domain.utils.Currency
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

class PropertyOfferPublicationDto (
    val id: Long,
    @field:NotBlank
    var propertyId: Long,
    @field:NotBlank
    var propertyOfferType: PropertyOfferType,
    @field:NotBlank
    var userId: Long,
    @field:NotBlank
    var title: String,
    @field:NotBlank
    @field:Min(value = 1, message = "Rating must be greater than or equal to one")
    var price: Double,
    @field:NotBlank
    var currency: Currency,

    var maxOccupants: Int,

    var mapLatitude: Float,

    var mapLongitude: Float,

    var mapRadius: Int,
    @field:NotBlank
    var status: PublicationStatus = PublicationStatus.INACTIVE
)