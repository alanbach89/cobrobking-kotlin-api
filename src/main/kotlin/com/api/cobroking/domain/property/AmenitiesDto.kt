package com.api.cobroking.domain.property

import jakarta.validation.constraints.NotBlank

data class AmenitiesDto(
    var id: Long? = null,

    @field:NotBlank
    var propertyId: Long? = null,

    @field:NotBlank
    var balcony: Boolean? = false,

    @field:NotBlank
    var pool: Boolean? = false,

    @field:NotBlank
    var laundry: Boolean? = false,

    @field:NotBlank
    var eventSpace: Boolean? = false,

    @field:NotBlank
    var gym: Boolean? = false,

    @field:NotBlank
    var openView: Boolean? = false,

    @field:NotBlank
    var garage: Boolean? = false,

    @field:NotBlank
    var airConditioning: Boolean? = false,

    @field:NotBlank
    var expenses: Boolean? = false,

    @field:NotBlank
    var propertyTax: Boolean? = false,

    @field:NotBlank
    var water: Boolean? = false,

    @field:NotBlank
    var cable: Boolean? = false,

    @field:NotBlank
    var internet: Boolean? = false,

    @field:NotBlank
    var gas: Boolean? = false,

    @field:NotBlank
    var bedLinens: Boolean? = false,

    @field:NotBlank
    var security: Boolean? = false
)