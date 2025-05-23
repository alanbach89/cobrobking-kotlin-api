package com.api.cobroking.domain.rating

import com.api.cobroking.base.BaseService
import com.api.cobroking.base.exception.RatingExistsException
import com.api.cobroking.base.exception.RatingNotFoundException
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class RatingService(private val ratingRepository: RatingRepository,
                    private val userRepository: UserRepository
) : BaseService<RatingDto, Long> {

    override fun create(dto: RatingDto): RatingDto {
        if (ratingRepository.existsRatingByPublicationTypeAndPublicationId(dto.publicationType!!,
                dto.publicationId)) {
            throw RatingExistsException()
        }

        val user: User = userRepository.getReferenceById(dto.userId)
        var newRating = Rating(null, dto.publicationType, dto.publicationId,
            dto.rating, dto.opinion, user)

        var savedRating: Rating = ratingRepository.save(newRating)
        return savedRating.toRatingDto()
    }

    override fun update(id: Long, dto: RatingDto): RatingDto {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): RatingDto {
        try {
            return ratingRepository.getReferenceById(id).toRatingDto()
        } catch (e: EntityNotFoundException) {
            throw RatingNotFoundException()
        }
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<RatingDto> {
        return ratingRepository.findAll().map { return@map it.toRatingDto() }
    }

}