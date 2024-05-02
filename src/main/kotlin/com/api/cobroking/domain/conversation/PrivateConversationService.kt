package com.api.cobroking.domain.conversation

import com.api.cobroking.domain.exception.PrivateConversationExistsException
import com.api.cobroking.domain.exception.PrivateConversationNotFoundException
import com.api.cobroking.domain.exception.PrivateMessageNotFoundException
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.security.InvalidParameterException
import java.sql.Timestamp
import java.time.Instant


@Service
class PrivateConversationService(private val privateConversationRepository: PrivateConversationRepository,
                                 private val userRepository: UserRepository) {

    fun create(newPrivateConversationDto: PrivateConversationDto): PrivateConversationDto {
        val participants = getUsersByUsernames(newPrivateConversationDto.participants)
        if (privateConversationRepository.findByAllUsers(participants, participants.size).isPresent) {
            throw PrivateConversationExistsException()
        }
        var newPrivateConversation = PrivateConversation()
        var savedPrivateConversation: PrivateConversation = privateConversationRepository.save(
                newPrivateConversation.createFromDto(newPrivateConversationDto, userRepository))
        return savedPrivateConversation.toPrivateConversationDto()
    }

    fun getById(id: Long): PrivateConversationDto {
        try {
            return privateConversationRepository.getReferenceById(id).toPrivateConversationDto()
        } catch (e: EntityNotFoundException) {
            throw PrivateConversationNotFoundException()
        }
    }

    fun update(id: Long, updatedPrivateConversationDto: PrivateConversationDto): PrivateConversationDto {
        lateinit var dbPrivateConversation: PrivateConversation
        try {
            dbPrivateConversation = privateConversationRepository.getReferenceById(id)
        } catch (e: EntityNotFoundException) {
            throw PrivateConversationNotFoundException()
        }

        var savedPrivateConversation: PrivateConversation = privateConversationRepository.save(dbPrivateConversation.updateFromDto(updatedPrivateConversationDto))
        return savedPrivateConversation.toPrivateConversationDto()
    }

    fun getAll(): List<PrivateConversationDto> {
        return privateConversationRepository.findAll().map { return@map it.toPrivateConversationDto() }
    }

    fun sendMessage(privateMessageDto: PrivateMessageDto): PrivateMessageDto {
        lateinit var dbPrivateConversation: PrivateConversation
        lateinit var dbPrivateMessage: PrivateMessage
        try {
            dbPrivateConversation = privateConversationRepository.findPrivateConversationsById(
                privateMessageDto.privateConversationId)
            if(dbPrivateConversation.participants.find { user -> user.username == privateMessageDto.user } == null) {
                throw EntityNotFoundException()
            } else if (privateMessageDto.user != userlocalSession) {
                throw InvalidParameterException("Usuario invalido")
            }
            dbPrivateMessage = PrivateMessage(
                null,
                privateMessageDto.text,
                Timestamp.from(Instant.now()),
                MessageStatus.CREATED,
                userRepository.getByUsername(privateMessageDto.user)!!,
                dbPrivateConversation
            )
            dbPrivateConversation.addMessage(dbPrivateMessage)
        } catch (e: EntityNotFoundException) {
            throw PrivateConversationNotFoundException()
        }

        return privateMessageDto
    }

    fun editMessage(privateMessageDto: PrivateMessageDto): PrivateMessageDto {
        lateinit var dbPrivateConversation: PrivateConversation
        lateinit var dbPrivateMessage: PrivateMessage
        try {
            dbPrivateConversation = privateConversationRepository.findPrivateConversationsById(
                privateMessageDto.privateConversationId)
            //Aca tiene que ser el usuario de la sesion
            if(dbPrivateConversation.participants.find { user -> user.username == userlocalSession } == null) {
                throw EntityNotFoundException()
            }
        } catch (e: EntityNotFoundException) {
            throw PrivateConversationNotFoundException()
        }

        try {
            dbPrivateMessage = dbPrivateConversation.privateMessages.find { msg -> msg.id == privateMessageDto.id }!!
            if (dbPrivateMessage == null) {
                throw EntityNotFoundException()
            } else if (validateEditionMessageTimestamp(dbPrivateMessage.timestamp)) {
                throw InvalidParameterException("El mensaje no se puede editar")
            } else if (dbPrivateMessage.user.username != userlocalSession) {
                throw InvalidParameterException("El mensaje no se puede eliminar")
            }
            dbPrivateMessage.updateFromDto(privateMessageDto)
        } catch (e: EntityNotFoundException) {
            throw PrivateMessageNotFoundException()
        }

        return dbPrivateMessage.toPrivateMessageDto()
    }

    fun deleteMessage(privateConversationId: Long, privateMessageId: Long): PrivateMessageDto {
        lateinit var dbPrivateConversation: PrivateConversation
        lateinit var dbPrivateMessage: PrivateMessage
        try {
            dbPrivateConversation = privateConversationRepository.findPrivateConversationsById(
                privateConversationId)
            //Aca tiene que ser el usuario de la sesion
            if(dbPrivateConversation.participants.find { user -> user.username == userlocalSession } == null) {
                throw EntityNotFoundException()
            }
        } catch (e: EntityNotFoundException) {
            throw PrivateConversationNotFoundException()
        }

        try {
            dbPrivateMessage = dbPrivateConversation.privateMessages.find { msg -> msg.id == privateMessageId }!!
            if (dbPrivateMessage == null) {
                throw EntityNotFoundException()
            } else if (validateEditionMessageTimestamp(dbPrivateMessage.timestamp)) {
                throw InvalidParameterException("El mensaje no se puede eliminar")
            } else if (dbPrivateMessage.user.username != userlocalSession) {
                throw InvalidParameterException("El mensaje no se puede eliminar")
            }
            dbPrivateMessage.setDeleteStatus()
        } catch (e: EntityNotFoundException) {
            throw PrivateMessageNotFoundException()
        }

        return dbPrivateMessage.toPrivateMessageDto()
    }

    private fun getUsersByUsernames(usernames: List<String>): List<User> {
        val users = mutableListOf<User>()
        for (username in usernames) {
            val userOptional = userRepository.findByUsername(username)
            userOptional.ifPresent { users.add(it) }
        }
        return users
    }

    private fun validateEditionMessageTimestamp(ts: Timestamp): Boolean {
        // 600.000 ms = 10 min
        return Instant.ofEpochMilli(ts.time + 600000) < Instant.now()
    }

}