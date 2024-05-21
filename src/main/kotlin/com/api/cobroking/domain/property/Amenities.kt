package com.api.cobroking.domain.property

import jakarta.persistence.*


@Entity
@Table(name = "property_amenities")
data class Amenities(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @OneToOne
    var property: Property? = null,

    @Column(nullable = false)
    var balcony: Boolean? = false,

    @Column(nullable = false)
    var pool: Boolean? = false,

    @Column(nullable = false)
    var laundry: Boolean? = false,

    @Column(nullable = false)
    var eventSpace: Boolean? = false,

    @Column(nullable = false)
    var gym: Boolean? = false,

    @Column(nullable = false)
    var openView: Boolean? = false,

    @Column(nullable = false)
    var garage: Boolean? = false,

    @Column(nullable = false)
    var airConditioning: Boolean? = false,

    @Column(nullable = false)
    var expenses: Boolean? = false,

    @Column(nullable = false)
    var propertyTax: Boolean? = false,

    @Column(nullable = false)
    var water: Boolean? = false,

    @Column(nullable = false)
    var cable: Boolean? = false,

    @Column(nullable = false)
    var internet: Boolean? = false,

    @Column(nullable = false)
    var gas: Boolean? = false,

    @Column(nullable = false)
    var bedLinens: Boolean? = false,

    @Column(nullable = false)
    var security: Boolean? = false
) {
    fun updateFromDto(amenitiesDto: AmenitiesDto): Amenities {
        this.balcony = balcony
        this.pool = pool
        this.laundry = laundry
        this.eventSpace = eventSpace
        this.gym = gym
        this.openView = openView
        this.garage = garage
        this.airConditioning = airConditioning
        this.expenses = expenses
        this.propertyTax = propertyTax
        this.water = water
        this.cable = cable
        this.internet = internet
        this.gas = gas
        this.bedLinens = bedLinens
        this.security = security
        return this
    }

    fun toAmenitiesDto() = AmenitiesDto(
        id = id!!,
        propertyId = property?.id,
        balcony = balcony!!,
        pool = pool!!,
        laundry = laundry!!,
        eventSpace = eventSpace!!,
        gym = gym!!,
        openView = openView!!,
        garage = garage!!,
        airConditioning = airConditioning!!,
        expenses = expenses!!,
        propertyTax = propertyTax!!,
        water = water!!,
        cable = cable!!,
        internet = internet!!,
        gas = gas!!,
        bedLinens = bedLinens!!,
        security = security!!
    )
}