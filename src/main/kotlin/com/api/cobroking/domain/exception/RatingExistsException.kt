package com.api.cobroking.domain.exception

class RatingExistsException(
    message: String? = "Rating with the same id already exists"
) : RuntimeException(message)