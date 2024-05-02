package com.api.cobroking.domain.conversation

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class PrivateConversationDto(

    val id: Long? = null,
    @field:NotBlank
    @field:Size(min = 4, max = 20, message = "Username must be have from 4 to 20 characters")
    var title: String,
    @field:NotEmpty
    val participants: List<String>,
    val privateMessages: List<PrivateMessageDto>? = listOf(),
)

fun PrivateConversation.toPrivateConversationDto() = PrivateConversationDto(
    id = id,
    title = title,
    participants = participants.map { return@map it.username }.toList(),
    privateMessages = privateMessages.map { return@map it.toPrivateMessageDto() }.toList(),
)