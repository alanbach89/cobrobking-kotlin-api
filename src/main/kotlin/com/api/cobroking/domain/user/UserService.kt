package com.api.cobroking.domain.user

import com.api.cobroking.base.BaseService
import com.api.cobroking.base.exception.UserExistsException
import com.api.cobroking.base.exception.UserNotFoundException
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) : BaseService<UserDto> {

    override fun create(newUserDto: UserDto): UserDto {
        if (userRepository.existsUserByUsername(newUserDto.username)) {
            throw UserExistsException()
        }
        var newUser = User()
        var savedUser: User = userRepository.save(newUser.createFromDto(newUserDto))
        return savedUser.toUserDto()
    }

    override fun getById(id: Long): UserDto {
        try {
            return userRepository.getReferenceById(id).toUserDto()
        } catch (e: EntityNotFoundException) {
            throw UserNotFoundException()
        }
    }

    override fun update(id: Long, updatedUserDto: UserDto): UserDto {
        lateinit var dbUser: User
        try {
            dbUser = userRepository.getReferenceById(id)
        } catch (e: EntityNotFoundException) {
            throw UserNotFoundException()
        }

        var savedUser: User = userRepository.save(dbUser.updateFromDto(updatedUserDto))
        return savedUser.toUserDto()
    }

    override fun getAll(): List<UserDto> {
        return userRepository.findAll().map { return@map it.toUserDto() }
    }

    override fun deleteById(id: Long) {
        //("Not implemented")
    }
}