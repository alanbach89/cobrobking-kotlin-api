package com.api.cobroking.domain.conversation

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.sql.Timestamp

class PrivateMessageDto(

    val id: Long? = null,
    @field:NotBlank
    @field:Size(min = 1, max = 140, message = "Username must be have from 1 to 140 characters")
    var text: String,
    val timestamp: Timestamp? = null,
    val status: MessageStatus = MessageStatus.CREATED,
    @field:NotBlank
    val user: String,
    @field:NotBlank
    val privateConversationId: Long,
)

fun PrivateMessage.toPrivateMessageDto() = PrivateMessageDto(
    id = id,
    text = if (status == MessageStatus.DELETED) "" else text,
    timestamp = timestamp,
    status = status,
    user = user.username,
    privateConversationId = privateConversation.id!!,
)