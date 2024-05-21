package com.api.cobroking.domain.property

import jakarta.persistence.*
import jakarta.validation.constraints.Min

@Entity
@Table(name = "property")
data class Property(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var description: String? = null,

    @Column(nullable = false)
    var location: String? = null,

    @Column(nullable = false)
    @Min(value = 0, message = "Size must be greater than or equal to zero")
    var size: Double? = null,

    @Column(nullable = false)
    var internalSize: Double? = null,

    @Column(nullable = false)
    var sizeUnit: String? = null,

    @Min(value = 0, message = "Bathroom Quantity must be greater than or equal to zero")
    @Column(name = "bathroom_qty")
    var bathroomQty: Int? = 0,

    @Min(value = 1, message = "Room Quantity must be greater than or equal to one")
    @Column(name = "room_qty")
    var roomQty: Int? = 1,

    @OneToOne
    var amenities: Amenities = Amenities(),

    @OneToMany
    var photos: MutableList<PropertyPhoto> = mutableListOf()
) {
    fun updateFromDto(propertyDto: PropertyDto): Property {
        this.description = description
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
        description = description!!,
        location = location!!,
        size = size!!,
        internalSize = internalSize!!,
        sizeUnit = sizeUnit!!,
        bathroomQty = bathroomQty!!,
        roomQty = roomQty!!,
        amenities = amenities?.toAmenitiesDto(),
        photos = (photos?.map { return@map it.toPropertyPhotoDto() } as MutableList<PropertyPhotoDto>?)
    )
}