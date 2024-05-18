package com.api.cobroking.domain.rating;

import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.*
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
class Rating (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(nullable = false)
    var publicationType: PublicationType? = null,
    @Column(nullable = false)
    var publicationId: Long,
    @Column(nullable = false)
    @Min(value = 0, message = "Rating must be greater than or equal to zero")
    @Max(value = 5, message = "Rating must be less than or equal to 5")
    var rating: Int,
    var opinion: String?,

    @ManyToOne
    var user: User
) {

    fun updateFromDto(ratingDto: RatingDto): Rating {
        this.publicationType = publicationType
        this.publicationId = publicationId
        this.rating = rating
        this.opinion = opinion
        return this
    }

    fun toRatingDto() = RatingDto(
        id = id,
        publicationType = publicationType,
        publicationId = publicationId,
        rating = rating,
        opinion = opinion!!,
        userId = user.id!!
    )
}