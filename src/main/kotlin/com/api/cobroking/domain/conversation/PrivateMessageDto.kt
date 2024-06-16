package com.api.cobroking.domain.conversation

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.sql.Timestamp
import java.util.*

class PrivateMessageDto(

    val id: Long? = null,
    @field:NotBlank
    @field:Size(min = 1, max = 140, message = "Username must be have from 1 to 140 characters")
    var text: String,
    val timestamp: Timestamp? = null,
    val status: MessageStatus = MessageStatus.CREATED,
    @field:NotBlank
    val userId: UUID,
    @field:NotBlank
    val privateConversationId: Long,
)