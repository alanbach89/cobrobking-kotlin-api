package com.api.cobroking.base.exception

class UserNotFoundException(
    message: String? = "User not found"
) : RuntimeException(message)