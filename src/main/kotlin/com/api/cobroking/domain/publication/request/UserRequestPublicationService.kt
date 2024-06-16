package com.api.cobroking.domain.publication.request

import com.api.cobroking.base.BaseService
import com.api.cobroking.base.exception.UserRequestPublicationNotFoundException
import com.api.cobroking.domain.publication.property.PublicationStatus
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class UserRequestPublicationService(private val userRequestPublicationRepository: UserRequestPublicationRepository,
                                    private val userRepository: UserRepository
                                    ): BaseService<UserRequestPublicationDto, Long> {

    override fun create(dto: UserRequestPublicationDto): UserRequestPublicationDto {
        var newUserRequestPublication = UserRequestPublication(
            null,
            dto.title,
            dto.text,
            userRepository.getReferenceById(dto.userId),
            PublicationStatus.INACTIVE,
            dto.priceFrom,
            dto.priceTo,
            dto.currency,
            dto.country,
            dto.city,
            dto.neighborhoods,
            dto.roomQtys,
            dto.requestType,
            dto.propertyType,
            0,
            false,
            Timestamp.from(Instant.now()),
            null,
            null
        )
        var savedUserRequestPublication: UserRequestPublication =
            userRequestPublicationRepository.save(newUserRequestPublication)
        return savedUserRequestPublication.toUserRequestPublicationDto()
    }

    override fun update(id: Long, dto: UserRequestPublicationDto): UserRequestPublicationDto {
        lateinit var dbUserRequestPublication: UserRequestPublication
        try {
            dbUserRequestPublication = userRequestPublicationRepository.getReferenceById(id)
        } catch (e: EntityNotFoundException) {
            throw UserRequestPublicationNotFoundException()
        }

        var savedUserRequestPublication: UserRequestPublication =
            userRequestPublicationRepository.save(dbUserRequestPublication.updateFromDto(dto))
        return savedUserRequestPublication.toUserRequestPublicationDto()
    }

    override fun getById(id: Long): UserRequestPublicationDto {
        try {
            return this.userRequestPublicationRepository.getReferenceById(id).toUserRequestPublicationDto()
        } catch (e: EntityNotFoundException) {
            throw UserRequestPublicationNotFoundException()
        }
    }

    override fun deleteById(id: Long) {
        this.userRequestPublicationRepository.deleteById(id)
    }

    override fun getAll(): List<UserRequestPublicationDto> {
        return this.userRequestPublicationRepository.findAll().map { return@map it.toUserRequestPublicationDto() }
    }
}