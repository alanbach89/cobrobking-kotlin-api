package com.api.cobroking.domain.conversation

import com.api.cobroking.annotation.NoArg
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@NoArg
data class PrivateMessage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(nullable = false)
    var text: String,
    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val timestamp: Timestamp,
    @Column(nullable = false)
    var status: MessageStatus,

    @ManyToOne
    var user: User,

    @ManyToOne
    var privateConversation: PrivateConversation
) {

    fun updateFromDto(privateMessageDto: PrivateMessageDto): PrivateMessage {
        this.text = text
        this.status = MessageStatus.EDITED
        return this
    }

    fun setDeleteStatus(): PrivateMessage {
        this.status = MessageStatus.DELETED
        return this
    }

    fun toPrivateMessageDto() = PrivateMessageDto(
        id = id,
        text = if (status == MessageStatus.DELETED) "" else text,
        timestamp = timestamp,
        status = status,
        userId = user.id!!,
        privateConversationId = privateConversation.id!!,
    )
}