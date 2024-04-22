package com.api.cobroking.domain.rating;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


@Entity
class Rating (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    var publicationType: PublicationType,
    @Column(nullable = false)
    var publicationId: Long,
    @Column(nullable = false)
    //Pasar estas validaciones al dto
    @Min(value = 0, message = "Rating must be greater than or equal to zero")
    @Max(value = 5, message = "Rating must be less than or equal to 5")
    var rating: Int,
    var opinion: String
)