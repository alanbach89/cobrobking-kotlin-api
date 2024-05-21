package com.api.cobroking.domain.property

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class PropertyDto(
    var id: Long? = null,

    @field:NotBlank
    var description: String? = null,

    @field:NotBlank
    var location: String? = null,

    @field:NotBlank
    @field:Min(value = 0, message = "Size must be greater than or equal to zero")
    var size: Double? = null,

    @field:NotBlank
    var internalSize: Double? = null,

    @field:NotBlank
    var sizeUnit: String? = null,

    @field:NotBlank
    @field:Min(value = 0, message = "Bathroom Quantity must be greater than or equal to zero")
    var bathroomQty: Int? = 0,

    @field:NotBlank
    @field:Min(value = 1, message = "Room Quantity must be greater than or equal to one")
    var roomQty: Int? = 1,

    @field:NotBlank
    var amenities: AmenitiesDto? = null,

    var photos: MutableList<PropertyPhotoDto>? = null
)