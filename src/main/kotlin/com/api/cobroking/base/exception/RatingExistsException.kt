package com.api.cobroking.base.exception

class RatingExistsException(
    message: String? = "Rating with the same id already exists"
) : RuntimeException(message)