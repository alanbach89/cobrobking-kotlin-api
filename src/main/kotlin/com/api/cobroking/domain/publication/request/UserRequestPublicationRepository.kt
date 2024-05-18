package com.api.cobroking.domain.publication.request

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRequestPublicationRepository : JpaRepository<UserRequestPublication, Long> {
}