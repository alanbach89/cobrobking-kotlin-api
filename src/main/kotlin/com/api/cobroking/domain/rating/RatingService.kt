package com.api.cobroking.domain.rating

import com.api.cobroking.domain.exception.RatingExistsException
import com.api.cobroking.domain.exception.RatingNotFoundException
import com.api.cobroking.domain.user.User
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class RatingService(private val ratingRepository: RatingRepository,
                    private val userRepository: UserRepository
) {

    fun create(newRatingDto: RatingDto): RatingDto {
        if (ratingRepository.existsRatingByTypeAndPublicationId(newRatingDto.type,
                newRatingDto.publicationId)) {
            throw RatingExistsException()
        }

        val user: User = userRepository.getByUsername(newRatingDto.username)!!
        var newRating = Rating(null, newRatingDto.type, newRatingDto.publicationId,
            newRatingDto.rating, newRatingDto.opinion, user)

        var savedRating: Rating = ratingRepository.save(newRating)
        return savedRating.toRatingDto()
    }

    fun getById(id: Long): RatingDto {
        try {
            return ratingRepository.getReferenceById(id).toRatingDto()
        } catch (e: EntityNotFoundException) {
            throw RatingNotFoundException()
        }
    }

    fun getAll(): List<RatingDto> {
        return ratingRepository.findAll().map { return@map it.toRatingDto() }
    }
}