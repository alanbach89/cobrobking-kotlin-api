package com.api.cobroking.domain.property

import jakarta.persistence.*

@Entity
class PropertyPhoto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    var property: Property? = null,

    var text: String? = null,

    @Column(nullable = false)
    var url: String,

    @Column(nullable = false)
    var isPrincipal: Boolean = false,

    @Column(nullable = false)
    var assignedOrder: Int = 0
) {
    constructor() : this(null, null, null, "", false, 0)

    fun updateFromDto(propertyPhotoDto: PropertyPhotoDto): PropertyPhoto {
        this.text = text
        this.url = url
        this.isPrincipal = isPrincipal
        this.assignedOrder = assignedOrder
        return this
    }

    fun toPropertyPhotoDto() = PropertyPhotoDto(
        id = id!!,
        propertyId = property?.id,
        text = text!!,
        url = url,
        isPrincipal = isPrincipal,
        assignedOrder = assignedOrder
    )
}