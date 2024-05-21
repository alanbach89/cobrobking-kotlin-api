package com.api.cobroking.domain.rating

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<Rating, Long> {

    fun existsRatingByPublicationTypeAndPublicationId(publicationType: PublicationType, publicationId: Long): Boolean


}