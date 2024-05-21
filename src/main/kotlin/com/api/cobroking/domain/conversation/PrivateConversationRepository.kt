package com.api.cobroking.domain.conversation

import com.api.cobroking.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface PrivateConversationRepository : JpaRepository<PrivateConversation, Long> {

    fun findPrivateConversationsById(id: Long): PrivateConversation

    @Query("SELECT pc FROM PrivateConversation pc JOIN pc.participants m WHERE m.id = :participantId")
    fun findPrivateConversationsByParticipantId(@Param("participantId") participantId: Long): List<PrivateConversation>?

    @Query("SELECT pc FROM PrivateConversation pc WHERE SIZE(pc.participants) = :numUsers " +
            "AND :numUsers = (SELECT COUNT(u) FROM User u WHERE u IN :participants)")
    fun findByAllUsers(participants: List<User?>, numUsers: Int): Optional<List<PrivateConversation?>>

    @Query("SELECT pc FROM PrivateConversation pc JOIN pc.privateMessages m WHERE m.id = :messageId")
    fun findPrivateConversationsByMessageId(@Param("messageId") messageId: Long): PrivateConversation?
}