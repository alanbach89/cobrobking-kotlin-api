package com.api.cobroking.domain.user

import com.api.cobroking.base.BaseService
import com.api.cobroking.base.exception.UserExistsException
import com.api.cobroking.base.exception.UserNotFoundException
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class UserService(private val userRepository: UserRepository) : BaseService<UserDto, UUID> {

    override fun create(dto: UserDto): UserDto {
        if (userRepository.existsUserByUsername(dto.username)) {
            throw UserExistsException()
        }
        var newUser = User()
        var savedUser: User = userRepository.save(newUser.createFromDto(dto))
        return savedUser.toUserDto()
    }

    override fun getById(id: UUID): UserDto {
        try {
            return userRepository.getReferenceById(id).toUserDto()
        } catch (e: EntityNotFoundException) {
            throw UserNotFoundException()
        }
    }

    override fun update(id: UUID, dto: UserDto): UserDto {
        lateinit var dbUser: User
        try {
            dbUser = userRepository.getReferenceById(id)
        } catch (e: EntityNotFoundException) {
            throw UserNotFoundException()
        }

        var savedUser: User = userRepository.save(dbUser.updateFromDto(dto))
        return savedUser.toUserDto()
    }

    override fun getAll(): List<UserDto> {
        return userRepository.findAll().map { return@map it.toUserDto() }
    }

    override fun deleteById(id: UUID) {
        //("Not implemented")
    }
}