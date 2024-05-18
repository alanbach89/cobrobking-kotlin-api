package com.api.cobroking.domain.property

import jakarta.validation.constraints.NotBlank

data class PropertyPhotoDto (
    var id: Long? = null,

    @field:NotBlank
    var propertyId: Long? = null,

    var text: String? = null,

    @field:NotBlank
    var url: Boolean? = null,

    @field:NotBlank
    var isPrincipal: Boolean? = false
)