package com.api.cobroking.domain.user

import com.api.cobroking.base.BaseService
import com.api.cobroking.base.exception.UserExistsException
import com.api.cobroking.base.exception.UserNotFoundException
import com.api.cobroking.domain.security.dtos.SignUpDto
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID
import org.springframework.security.core.userdetails.UsernameNotFoundException

import org.springframework.security.core.userdetails.UserDetailsService





@Service
class UserService(private val userRepository: UserRepository) : BaseService<UserDto, UUID> {
    fun userDetailsService(): UserDetailsService{
        return UserDetailsService { username ->
            userRepository.findByEmail(username).orElseThrow {
                UsernameNotFoundException("User not found")
            }
        }
    }

    override fun create(dto: UserDto): UserDto {
        if (userRepository.existsUserByUsername(dto.username)) {
            throw UserExistsException()
        }
        var newUser = User()
        var savedUser: User = userRepository.save(newUser.createFromDto(dto))
        return savedUser.toUserDto()
    }

    fun createWithSignUp(dto: SignUpDto): UserDto {
        if (userRepository.existsUserByUsername(dto.username)) {
            throw UserExistsException()
        }
        var newUser = User()
        var savedUser: User = userRepository.save(newUser.createFromSignUp(dto))
        return savedUser.toUserDto()
    }

    fun getByEmail(email: String): UserDto {
        val user = userRepository.findByEmail(email).orElseThrow { UserNotFoundException() }
        return user.toUserDto()
    }

    fun getByUsername(username: String): UserDto {
        val user = userRepository.getByUsername(username)
        return user?.toUserDto() ?: throw UserNotFoundException()
    }

    fun getByEmailOrUsername(email: String, username: String): UserDto {
        val userByEmail = userRepository.findByEmail(email).orElse(null)
        val userByUsername = runCatching { userRepository.getByUsername(username) }.getOrNull()
        val user = userByEmail ?: userByUsername
        return user?.toUserDto() ?: throw UserNotFoundException()
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