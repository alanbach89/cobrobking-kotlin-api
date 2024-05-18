package com.api.cobroking.domain.conversation;

import com.api.cobroking.annotation.NoArg
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.*


@Entity
@NoArg
data class PrivateConversation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(nullable = false)
    var title: String,

    @ManyToMany
    var participants: MutableList<User> = mutableListOf(),

    @OneToMany(mappedBy = "privateConversation", cascade = [CascadeType.ALL])
    val privateMessages: MutableList<PrivateMessage> = mutableListOf()
) {
    constructor() : this(null, "", mutableListOf<User>(),  mutableListOf<PrivateMessage>())

    fun updateFromDto(privateMessageDto: PrivateConversationDto): PrivateConversation {
        this.title = title
        return this
    }

    fun createFromDto(privateConversationDto: PrivateConversationDto,
                      userRepository: UserRepository): PrivateConversation {
        this.title = title
        this.participants = userRepository.findByUsernamesIn(privateConversationDto.participants)
        return this
    }

    fun addMessage(message: PrivateMessage) {
        privateMessages.add(message)
        message.privateConversation = this
    }

    fun toPrivateConversationDto() = PrivateConversationDto(
        id = id,
        title = title,
        participants = participants.map { return@map it.username }.toList(),
        privateMessages = privateMessages.map { return@map it.toPrivateMessageDto() }.toList(),
    )
}