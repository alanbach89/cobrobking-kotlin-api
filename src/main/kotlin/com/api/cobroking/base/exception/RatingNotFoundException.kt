package com.api.cobroking.base.exception

class RatingNotFoundException(
    message: String? = "Rating not found"
) : RuntimeException(message)