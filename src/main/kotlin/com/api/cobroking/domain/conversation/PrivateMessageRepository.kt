package com.api.cobroking.domain.conversation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PrivateMessageRepository : JpaRepository<PrivateMessage, Long> {
}