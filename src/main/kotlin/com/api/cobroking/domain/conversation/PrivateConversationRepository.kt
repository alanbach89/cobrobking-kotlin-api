package com.api.cobroking.domain.conversation

import com.api.cobroking.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional


@Repository
interface PrivateConversationRepository : JpaRepository<PrivateConversation, Long> {

    fun findPrivateConversationsById(id: Long): PrivateConversation

    fun findPrivateConversationsByUserId(userId: Long): List<PrivateConversation>

    @Query("SELECT pc FROM PrivateConversation pc WHERE SIZE(pc.users) = :numUsers AND :numUsers = (SELECT COUNT(u) FROM User u WHERE u IN :users)")
    fun findByAllUsers(users: List<User?>, numUsers: Int): Optional<List<PrivateConversation?>>

    fun findPrivateConversationsByMessageId(id: Long): PrivateConversation
}