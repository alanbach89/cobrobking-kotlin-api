package com.api.cobroking.domain.publication.property;

import com.api.cobroking.domain.property.Property
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.utils.Currency
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull


@Entity
class PropertyOfferPublication(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    @ManyToOne
    var property: Property,

    @Column(nullable = false)
    @Enumerated
    var propertyOfferType: PropertyOfferType = PropertyOfferType.RENT,

    @Column(nullable = false)
    @ManyToOne
    var user: User,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    @Min(value = 1, message = "Rating must be greater than or equal to one")
    var price: Double,

    @Column(nullable = false)
    @Enumerated
    var currency: Currency = Currency.ARS,

    var maxOccupants: Int? = null,

    var mapLatitude: Float? = null,

    var mapLongitude: Float? = null,

    var mapRadius: Int? = null,

    @Column(nullable = false)
    @Enumerated
    var status: PublicationStatus = PublicationStatus.INACTIVE
) {

    fun updateFromDto(propertyOfferPublicationDto: PropertyOfferPublicationDto): PropertyOfferPublication {
        this.title = title
        this.price = price
        this.currency = currency
        this.maxOccupants = maxOccupants
        this.mapLatitude = mapLatitude
        this.mapLongitude = mapLongitude
        this.mapRadius = mapRadius
        this.status = status
        return this
    }

    fun toPropertyOfferPublicationDto() = PropertyOfferPublicationDto(
        id = id!!,
        propertyId = property.id!!,
        propertyOfferType = propertyOfferType,
        userId = user.id!!,
        title = title,
        price = price,
        currency = currency,
        maxOccupants = maxOccupants!!,
        mapLatitude = mapLatitude!!,
        mapLongitude = mapLongitude!!,
        mapRadius = mapRadius!!,
        status = status
    )
}