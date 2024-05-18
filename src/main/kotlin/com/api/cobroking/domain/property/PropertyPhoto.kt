package com.api.cobroking.domain.property

import jakarta.persistence.*

@Entity
@Table(name = "photo_property")

class PropertyPhoto (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    var property: Property? = null,

    var text: String? = null,

    @Column(nullable = false)
    var url: Boolean? = null,

    @Column(nullable = false)
    var isPrincipal: Boolean? = false
) {
    fun updateFromDto(propertyPhotoDto: PropertyPhotoDto): PropertyPhoto {
        this.text = text
        this.url = url
        this.isPrincipal = isPrincipal
        return this
    }

    fun toPropertyPhotoDto() = PropertyPhotoDto(
        id = id!!,
        propertyId = property?.id,
        text = text!!,
        url = url!!,
        isPrincipal = isPrincipal!!
    )
}