package com.api.cobroking.domain.publication.request;

import com.api.cobroking.domain.publication.property.PublicationStatus
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.utils.Currency
import com.api.cobroking.domain.utils.PropertyType
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import java.sql.Timestamp
import java.time.Instant


@Entity
@Table(name = "publication_user_request")
class UserRequestPublication (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var text: String,

    @ManyToOne
    var user: User,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: PublicationStatus = PublicationStatus.INACTIVE,

    var priceFrom: Double? = null,

    var priceTo: Double? = null,

    var currency: Currency? = Currency.ARS,

    @Column(nullable = false)
    var country: String,

    @Column(nullable = false)
    var city: String,

    @ElementCollection
    @Column(nullable = false)
    var neighborhoods: MutableList<String> = mutableListOf(),

    // listado de cantidad de ambientes posibles(1, 2, 3...)
    @ElementCollection
    var roomQtys: MutableList<Int> = mutableListOf(),

    @Column(nullable = false)
    var requestType: RequestType,

    @Column(nullable = false)
    var propertyType: PropertyType,

    @Min(value = 0, message = "Room Quantity must be greater than or equal to one")
    @Column(nullable = false)
    var priority: Long = 0,

    @Column(nullable = false)
    var internalApproval: Boolean = false,

    @Column(name = "creation_ts", nullable = false)
    var creationTS: Timestamp,

    @Column(name = "update_ts")
    var updateTS: Timestamp? = null,

    @Column(name = "admin_update_ts")
    var adminUpdateTS: Timestamp? = null
) {
    fun updateFromDto(userRequestPublicationDto: UserRequestPublicationDto): UserRequestPublication {
        this.title = title
        this.text = text
        this.priceFrom = priceFrom
        this.priceTo = priceTo
        this.currency = currency
        this.country = country
        this.city = city
        this.neighborhoods = neighborhoods
        this.roomQtys = roomQtys
        this.requestType = requestType
        this.propertyType = propertyType
        this.updateTS = Timestamp.from(Instant.now())
        return this
    }
    fun adminUpdateFromDto(userRequestPublicationDto: UserRequestPublicationDto): UserRequestPublication {
        this.title = title
        this.text = text
        this.status = status
        this.priceFrom = priceFrom
        this.priceTo = priceTo
        this.currency = currency
        this.country = country
        this.city = city
        this.neighborhoods = neighborhoods
        this.roomQtys = roomQtys
        this.requestType = requestType
        this.propertyType = propertyType
        this.internalApproval = internalApproval
        this.priority = priority
        this.adminUpdateTS = Timestamp.from(Instant.now())
        return this
    }

    fun toUserRequestPublicationDto() = UserRequestPublicationDto(
        id = id!!,
        title = title,
        text = text,
        userId = user.id!!,
        status = status,
        priceFrom = priceFrom,
        priceTo = priceTo,
        currency = currency,
        country = country,
        city = city,
        neighborhoods = neighborhoods,
        roomQtys = roomQtys,
        requestType = requestType,
        propertyType = propertyType,
        creationTS = creationTS,
        updateTS = updateTS,
        priority = priority,
        internalApproval = internalApproval,
    )
}