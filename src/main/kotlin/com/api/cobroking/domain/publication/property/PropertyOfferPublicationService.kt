package com.api.cobroking.domain.publication.property

import com.api.cobroking.base.BaseService
import com.api.cobroking.base.exception.PropertyOfferPublicationNotFoundException
import com.api.cobroking.domain.property.PropertyRepository
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class PropertyOfferPublicationService(private val propertyOfferPublicationRepository: PropertyOfferPublicationRepository,
                                      private val propertyRepository: PropertyRepository,
                                      private val userRepository: UserRepository
): BaseService<PropertyOfferPublicationDto> {

    override fun create(dto: PropertyOfferPublicationDto): PropertyOfferPublicationDto {
        var newPropertyOfferPublication = PropertyOfferPublication(
            null,
            propertyRepository.getReferenceById(dto.propertyId),
            dto.propertyOfferType,
            userRepository.getReferenceById(dto.userId),
            dto.title,
            dto.price,
            dto.currency,
            dto.maxOccupants,
            dto.mapLatitude,
            dto.mapLongitude,
            dto.mapRadius
        )
        var savedPropertyOfferPublication: PropertyOfferPublication =
            propertyOfferPublicationRepository.save(newPropertyOfferPublication)
        return savedPropertyOfferPublication.toPropertyOfferPublicationDto()
    }

    override fun update(id: Long, dto: PropertyOfferPublicationDto): PropertyOfferPublicationDto {
        lateinit var dbPropertyOfferPublication: PropertyOfferPublication
        try {
            dbPropertyOfferPublication = propertyOfferPublicationRepository.getReferenceById(id)
        } catch (e: EntityNotFoundException) {
            throw PropertyOfferPublicationNotFoundException()
        }

        var savedPropertyOfferPublication: PropertyOfferPublication =
            propertyOfferPublicationRepository.save(dbPropertyOfferPublication.updateFromDto(dto))
        return savedPropertyOfferPublication.toPropertyOfferPublicationDto()
    }

    override fun getById(id: Long): PropertyOfferPublicationDto {
        try {
            return this.propertyOfferPublicationRepository.getReferenceById(id).toPropertyOfferPublicationDto()
        } catch (e: EntityNotFoundException) {
            throw PropertyOfferPublicationNotFoundException()
        }
    }

    override fun deleteById(id: Long) {
        this.propertyOfferPublicationRepository.deleteById(id)
    }

    override fun getAll(): List<PropertyOfferPublicationDto> {
        return this.propertyOfferPublicationRepository.findAll().map { return@map it.toPropertyOfferPublicationDto() }
    }
}