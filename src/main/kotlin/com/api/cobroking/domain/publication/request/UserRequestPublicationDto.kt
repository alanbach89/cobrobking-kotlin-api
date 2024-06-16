package com.api.cobroking.domain.publication.request

import com.api.cobroking.domain.publication.property.PublicationStatus
import com.api.cobroking.domain.utils.Currency
import com.api.cobroking.domain.utils.PropertyType
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.validation.constraints.NotBlank
import java.sql.Timestamp
import java.util.*

class UserRequestPublicationDto(
    val id: Long,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val text: String,

    @field:NotBlank
    val userId: UUID,

    var status: PublicationStatus? = PublicationStatus.INACTIVE,

    var priceFrom: Double? = null,

    var priceTo: Double? = null,

    var currency: Currency? = Currency.ARS,

    var country: String,

    var city: String,

    var neighborhoods: MutableList<String> = mutableListOf(),

    var roomQtys: MutableList<Int> = mutableListOf(),

    var requestType: RequestType,

    var propertyType: PropertyType,

    var creationTS: Timestamp? = null,

    var updateTS: Timestamp? = null,

    var priority: Long? = 0,

    var internalApproval: Boolean? = false
)