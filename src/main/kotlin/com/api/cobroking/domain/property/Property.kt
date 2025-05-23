package com.api.cobroking.domain.property

import com.api.cobroking.domain.utils.PropertyType
import jakarta.persistence.*
import jakarta.validation.constraints.Min

@Entity
@Table(name = "property")
data class Property(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: PropertyType,

    @Column(nullable = false)
    var country: String,

    @Column(nullable = false)
    var city: String,

    @Column(nullable = false)
    var neighborhood: String,

    @Column(nullable = false)
    var location: String,

    @Column(nullable = false)
    @Min(value = 0, message = "Size must be greater than or equal to zero")
    var size: Double,

    @Column(nullable = false)
    var internalSize: Double,

    @Column(nullable = false)
    var sizeUnit: String,

    @Min(value = 0, message = "Bathroom Quantity must be greater than or equal to zero")
    @Column(name = "bathroom_qty", nullable = false)
    var bathroomQty: Int = 0,

    @Min(value = 1, message = "Room Quantity must be greater than or equal to one")
    @Column(name = "room_qty", nullable = false)
    var roomQty: Int = 1,

    @OneToOne
    var amenities: Amenities = Amenities(),

    @OneToMany
    var photos: MutableList<PropertyPhoto> = mutableListOf()

) {
    fun updateFromDto(propertyDto: PropertyDto): Property {
        this.description = description
        this.type = type
        this.country = country
        this.city = city
        this.neighborhood = neighborhood
        this.location = location
        this.size = size
        this.internalSize = internalSize
        this.sizeUnit = sizeUnit
        this.bathroomQty = bathroomQty
        this.roomQty = roomQty

        // amenities and photos are updated separately

        return this
    }

    fun toPropertyDto() = PropertyDto(
        id = id!!,
        description = description,
        type = type,
        country = country,
        city = city,
        neighborhood = neighborhood,
        location = location,
        size = size,
        internalSize = internalSize,
        sizeUnit = sizeUnit,
        bathroomQty = bathroomQty,
        roomQty = roomQty,
        amenities = amenities.toAmenitiesDto(),
        photos = (photos.map { return@map it.toPropertyPhotoDto() } as MutableList<PropertyPhotoDto>),
    )
}