package com.api.cobroking.domain.property

import com.api.cobroking.domain.utils.PropertyType
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PropertyDto(
    var id: Long? = null,

    @field:NotBlank
    var description: String,

    @field:NotBlank
    var type: PropertyType,

    @field:NotBlank
    var country: String,

    @field:NotBlank
    var city: String,

    @field:NotBlank
    var neighborhood: String,

    @field:NotBlank
    var location: String,

    @field:NotBlank
    @field:Min(value = 0, message = "Size must be greater than or equal to zero")
    var size: Double,

    @field:NotBlank
    var internalSize: Double,

    @field:NotBlank
    var sizeUnit: String,

    @field:NotBlank
    @field:Min(value = 0, message = "Bathroom Quantity must be greater than or equal to zero")
    var bathroomQty: Int,

    @field:NotBlank
    @field:Min(value = 1, message = "Room Quantity must be greater than or equal to one")
    var roomQty: Int,

    @field:NotBlank
    var amenities: AmenitiesDto,

    @field:Size(min = 1, message = "Add at least one photo")
    var photos: MutableList<PropertyPhotoDto>
)