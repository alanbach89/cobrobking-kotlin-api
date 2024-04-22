package com.api.cobroking.domain.exception

class UserNotFoundException(
    message: String? = "User not found"
) : RuntimeException(message)