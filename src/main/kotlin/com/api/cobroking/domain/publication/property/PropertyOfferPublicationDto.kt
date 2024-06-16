package com.api.cobroking.domain.publication.property

import com.api.cobroking.domain.utils.Currency
import jakarta.persistence.Column
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.sql.Timestamp
import java.util.*

class PropertyOfferPublicationDto(
    val id: Long,

    @field:NotBlank
    var propertyId: Long,

    @field:NotBlank
    var propertyOfferType: PropertyOfferType,

    @field:NotBlank
    var userId: UUID,

    @field:NotBlank
    var title: String,

    @field:NotBlank
    @field:Min(value = 1, message = "Price must be greater than or equal to one")
    var price: Double,

    @field:NotBlank
    var currency: Currency,

    var maxOccupants: Int? = null,

    var mapLatitude: Float? = null,

    var mapLongitude: Float? = null,

    var mapRadius: Int? = null,

    var status: PublicationStatus? = PublicationStatus.INACTIVE,

    var creationTS: Timestamp? = null,

    var updateTS: Timestamp? = null,

    var priority: Long? = 0,

    var internalApproval: Boolean? = false
)