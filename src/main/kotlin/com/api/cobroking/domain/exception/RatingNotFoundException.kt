package com.api.cobroking.domain.exception

class RatingNotFoundException(
    message: String? = "Rating not found"
) : RuntimeException(message)