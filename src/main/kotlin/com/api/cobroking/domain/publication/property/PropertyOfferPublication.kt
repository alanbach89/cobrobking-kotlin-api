package com.api.cobroking.domain.publication.property;

import com.api.cobroking.domain.property.Property
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.utils.Currency
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import java.sql.Timestamp
import java.time.Instant


@Entity
@Table(name = "publication_property_offer")
class PropertyOfferPublication(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    var property: Property,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var propertyOfferType: PropertyOfferType = PropertyOfferType.RENT,

    @ManyToOne
    var user: User,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    @Min(value = 1, message = "Rating must be greater than or equal to one")
    var price: Double,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var currency: Currency = Currency.ARS,

    var maxOccupants: Int? = null,

    var mapLatitude: Float? = null,

    var mapLongitude: Float? = null,

    var mapRadius: Int? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: PublicationStatus = PublicationStatus.INACTIVE,

    @Min(value = 0, message = "Room Quantity must be greater than or equal to one")
    @Column(nullable = false)
    var priority: Long = 0,

    @Column(nullable = false)
    var internalApproval: Boolean = false,

    @Column(name = "creation_ts", nullable = false)
    var creationTS: Timestamp,

    @Column(name = "update_ts")
    var updateTS: Timestamp?,

    @Column(name = "admin_update_ts")
    var adminUpdateTS: Timestamp? = null
) {

    fun updateFromDto(propertyOfferPublicationDto: PropertyOfferPublicationDto): PropertyOfferPublication {
        this.title = title
        this.propertyOfferType = propertyOfferType
        this.price = price
        this.currency = currency
        this.maxOccupants = maxOccupants
        this.mapLatitude = mapLatitude
        this.mapLongitude = mapLongitude
        this.mapRadius = mapRadius
        this.updateTS = Timestamp.from(Instant.now())
        return this
    }

    fun adminUpdateFromDto(propertyOfferPublicationDto: PropertyOfferPublicationDto): PropertyOfferPublication {
        this.title = title
        this.propertyOfferType = propertyOfferType
        this.price = price
        this.currency = currency
        this.maxOccupants = maxOccupants
        this.mapLatitude = mapLatitude
        this.mapLongitude = mapLongitude
        this.mapRadius = mapRadius
        this.status = status
        this.creationTS = creationTS
        this.internalApproval = internalApproval
        this.priority = priority
        this.adminUpdateTS = Timestamp.from(Instant.now())
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
        status = status,
        creationTS = creationTS,
        updateTS = updateTS,
        internalApproval = internalApproval,
        priority = priority
    )
}