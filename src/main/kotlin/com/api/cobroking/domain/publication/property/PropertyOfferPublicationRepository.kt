package com.api.cobroking.domain.publication.property

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PropertyOfferPublicationRepository : JpaRepository<PropertyOfferPublication, Long> {
}